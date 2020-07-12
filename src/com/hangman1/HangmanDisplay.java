package com.hangman1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

abstract class HangmanDisplay extends JFrame {

    JPanel panel = new JPanel();
    JPanel textPanel = new JPanel();
    JLabel imgLabel = new JLabel();
    JLabel lifeLabel = new JLabel();
    JLabel mysteryWord = new JLabel("Word to find : ");
    String chosenWord1 = chosenWord();

    Hangman h1 = new Hangman(chosenWord1, 6);

    // Constructor
    HangmanDisplay() {
        super("Hangman");

        setSize(900,600);
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMenuBar();
        addImagePanel();
        addTextPanel();
    }

    // Abstract methods to implement the input word and setting up textPanel for user input

    abstract String chosenWord();
    abstract void addTextPanel();

    // Menubar for different game options

    public void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu options = new JMenu("Game");

        JMenuItem optionGame1 = new JMenuItem("Play with Computer");
        JMenuItem optionGame2 = new JMenuItem("Play with another player");

        options.add(optionGame1);
        options.add(optionGame2);

        optionGame1.addActionListener(ae-> {
            try {
                this.setVisible(false);
                this.dispose();

                HangmanDisplay frame = new HangmanSinglePlayer();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(HangmanDisplay.super.getContentPane(), "Invalid action " +e);
            }
        });

        optionGame2.addActionListener(ae-> {
            try {
                this.setVisible(false);
                this.dispose();

                HangmanDisplay frame = new HangmanTwoPlayers();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(HangmanDisplay.super.getContentPane(), "Invalid action " +e);
            }
        });

        menuBar.add(options);

        JMenu help = new JMenu("Help");
        JMenuItem instructions = new JMenuItem("Game Instructions ");

        help.add(instructions);
        menuBar.add(help);

        instructions.addActionListener(ae->{
            try{
                this.setVisible(false);
                this.dispose();
                JPanel instructionsPanel = new JPanel();
                JTextArea gamedetails = new JTextArea("TextArea display",20,25);

                instructionsPanel.add(gamedetails);
                add(instructionsPanel);
                setVisible(true);

            } catch (Exception e) {

            }
        });

        JMenu quit = new JMenu("Quit");
        JMenuItem exit = new JMenuItem("Exit game ");

        exit.addActionListener(ae-> {
            try {
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(HangmanDisplay.super.getContentPane(), "Invalid action " +e);
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
                JOptionPane.showMessageDialog(HangmanDisplay.super.getContentPane(), "Sorry you lose ");
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
