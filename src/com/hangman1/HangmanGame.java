package com.hangman1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

abstract class HangmanGame extends JFrame implements IGame
{

    JPanel panel = new JPanel();
    JPanel textPanel = new JPanel();
    JLabel imgLabel = new JLabel();
    JLabel lifeLabel = new JLabel();
    JLabel mysteryWord = new JLabel("Word to find : ");
    String chosenWord1 = chosenWord();
    Logger logFile;

    Hangman h1 = new Hangman(chosenWord1, 6);

    // Constructor
    HangmanGame() {
        super("Hangman");

        setSize(900,600);
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMenuBar();
        addImagePanel();
        addTextPanel();
        logFile = Logger.getInstance();
    }

    // Overriding the method from the Interface to display instructions for game.

    @Override
    public void instructions() {
        JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "There are two game options to choose from - \n" +
                " 1. Play with computer, \n" +
                " 2. Play with another player. ");
    }

    // Abstract methods that will be implemented in the child class for accepting the input word.

    abstract String chosenWord();

    //Abstract methods implemented by the child class for setting up textPanel.

    abstract void addTextPanel();

    // Menubar for different game options

    public void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu options = new JMenu("Game");

        JMenuItem optionGame1 = new JMenuItem("Play with Computer");
        JMenuItem optionGame2 = new JMenuItem("Play with another player");

        options.add(optionGame1);
        options.add(optionGame2);

        // Action listener with LAMBDA function for selecting the game to play with computer.

        optionGame1.addActionListener(ae-> {
            try {

                this.setVisible(false);
                this.dispose();

                HangmanGame frame = new HangmanSinglePlayer();
                frame.setVisible(true);
            } catch (Exception e) {
                logFile.log("Invalid action"+e);
                JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "Invalid action " +e);
            }
        });

        // Action listener with LAMBDA function for selecting the game to play with another player.

        optionGame2.addActionListener(ae-> {
            try {
                this.setVisible(false);
                this.dispose();
                System.out.println("Before creating the object");
                HangmanGame frame = new HangmanTwoPlayers();
                System.out.println("After creating the object");
                frame.setVisible(true);
            } catch (Exception e) {
                //logFile.log("Invalid action"+e.printStackTrace(););
                System.out.println(e);
                JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "From option2 - Invalid action " +e);
            }
        });

        menuBar.add(options);

        JMenu help = new JMenu("Help");
        JMenuItem instructions = new JMenuItem("Game Instructions ");

        help.add(instructions);
        menuBar.add(help);

        // Action listener to display the instructions for game.

        instructions.addActionListener(ae->{
            try{
                this.instructions();

            } catch (Exception e) {

            }
        });

        JMenu quit = new JMenu("Quit");
        JMenuItem exit = new JMenuItem("Exit game ");

        // Action listener to quit the game.

        exit.addActionListener(ae-> {
            try {
                dispose();
            } catch (Exception e) {
                logFile.log("Invalid action"+e);
                JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "Invalid action " +e);
            }
        });

        quit.add(exit);

        menuBar.add(quit);

        setJMenuBar(menuBar);
    }

    //Setting up the panel to display the image of the hangman

    public void addImagePanel(){

        panel.setPreferredSize(new Dimension(300, 350));
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        panel.setPreferredSize(new Dimension(300, 600));

        textPanel.setLayout(new GridLayout(10,1));
        textPanel.setPreferredSize(new Dimension(300, 350));
        textPanel.setBorder(new EmptyBorder(2, 3, 2, 3));

        imgLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 6.png"));

        JLabel labelText = new JLabel("Start game... ");
        labelText.setFont(new Font("Times New Roman", Font.ITALIC, 20));

        panel.add(labelText);
        panel.add(imgLabel);

        add(panel);
        pack();
    }

    // Method that changes the hangman image for each wrong try

    public void changeImage(){
        switch (h1.getLife()){
            case 0:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 0.png"));
                imgLabel.repaint();
                logFile.log("Sorry you lose");
                JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "Sorry you lose ");
                break;
            case 1:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 1.png"));
                imgLabel.repaint();
                break;
            case 2:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 2.png"));
                imgLabel.repaint();
                lifeLabel.setForeground(Color.red);
                break;
            case 3:

                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 3.png"));
                imgLabel.repaint();
                break;
            case 4:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 4.png"));
                imgLabel.repaint();
                break;
            case 5:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 5.png"));
                imgLabel.repaint();
                break;
            case 6:
                imgLabel.setIcon(new ImageIcon("C:\\Vimala\\hangman 6.png"));
                imgLabel.repaint();
                lifeLabel.setForeground(Color.green);
                break;
            default:
                break;
        }
    }
}
