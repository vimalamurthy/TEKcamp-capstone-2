package com.hangman1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class HangmanSinglePlayer extends HangmanGame implements IGame{

    Logger logFile;
    // constructor
    HangmanSinglePlayer() {
        super();

        setSize(900,600);
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logFile = Logger.getInstance();
    }

    @Override
    public void instructions() {
        super.instructions();
        JOptionPane.showMessageDialog(HangmanSinglePlayer.super.getContentPane(), "1. Play with computer: \n" +
                " \n" +
                " When playing with the computer, the computer displays a word, which has to be guessed by the player.\n" +
                " \n" +
                " The player enters one character at a time to find the word. \n" +
                " \n" +
                " If the guess is correct, the matching character is displayed appropriately, else the number of tries \n" +
                " reduces by one.\n" +
                " \n" +
                " If the player gets the word correct within 6 tries, the player wins. Otherwise the player looses the game. ");
    }

    // Generate the mystery word.
    public String chosenWord(){
        Random random = new Random();
        String[] mysteryWords ={"India","America", "Japan", "United Kingdom","Srilanka","Mexico","Thailand", "Switzerland","Cambodia", "Singapore", "Malaysia"};
        String chosen = mysteryWords[random.nextInt(mysteryWords.length)];
        return chosen;
    }

    //Calling the parent to display the menu bar.
    public void addMenuBar() {
        super.addMenuBar();
    }

    //Calling the parent to display the image panel details.
    public void addImagePanel(){
        super.addImagePanel();
    }

    // Adding the text panel to display the word to find and the accepting user input with error checking.
    public void addTextPanel() {

        JPanel textPanel = new JPanel();
//        logFile.log("Mystery word: " +chosenWord1);
        //       System.out.println("Mystery word: " +chosenWord1);

        textPanel.setLayout(new GridLayout(10,1));
        textPanel.setPreferredSize(new Dimension(300, 350));
        textPanel.setBorder(new EmptyBorder(2, 3, 2, 3));

        mysteryWord.setFont(new Font("Arial Black", Font.BOLD, 22));

        JLabel enter = new JLabel(" Enter alphabet of your choice : ");
        enter.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JLabel word = new JLabel(String.valueOf(h1.guess()));

        word.setFont(new Font("Arial Black", Font.BOLD, 22));

        JTextField userText = new JTextField(1);
        userText.setMaximumSize(new Dimension(50,20));
        userText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userText.setColumns(2);


        JButton okButton = new JButton("Confirm");
        okButton.setMaximumSize(new Dimension(30,15));
        okButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        okButton.setForeground(Color.BLUE);

        JLabel display = new JLabel("Tried Alphabets.. ");
        display.setFont(new Font("Times New Roman", Font.BOLD, 20));
        display.setBounds(30,30, 15,15);

        JLabel triedAlphabets = new JLabel("");
        triedAlphabets.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        lifeLabel = new JLabel("Life Remaining :  "+h1.getLife());
        lifeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        //Action listener to accept the user input.
        // Exception & Error handling with appropriate message display.

        okButton.addActionListener(e ->{
            try{
                char checkAplhabet = userText.getText().charAt(0);

                if (userText.getText().length()>1){
                    logFile.log("More than one character entered. First character will be accepted.");
                    JOptionPane.showMessageDialog(HangmanSinglePlayer.super.getContentPane(), "More than one character entered. First character will be accepted.");
                }
                if ((checkAplhabet >= 'a' && checkAplhabet <= 'z') || (checkAplhabet >= 'A' && checkAplhabet <= 'Z')) {
                    triedAlphabets.setText(triedAlphabets.getText() + " " + checkAplhabet);
                    userText.setText("");
                    char[] retVal = h1.playGame(checkAplhabet);
                    word.setText(String.valueOf(retVal));
                    lifeLabel.setText(String.valueOf("Life remaining is : " + h1.getLife()));
                    if (h1.checkResult()) {
                        logFile.log("Congratulations.. You win");
                        JOptionPane.showMessageDialog(HangmanSinglePlayer.super.getContentPane(), "Congratulations.. You win");
                    }
                    changeImage();
                }
                else {
                    logFile.log(" Invalid input. Please enter an alphabet.");
                    JOptionPane.showMessageDialog(HangmanSinglePlayer.super.getContentPane(), " Invalid input. Please enter an alphabet. ");
                    userText.setText("");
                }
            }
            catch (StringIndexOutOfBoundsException strIndexException){
                logFile.log(" No input. Please enter an alphabet.");
                JOptionPane.showMessageDialog(HangmanSinglePlayer.super.getContentPane(), " No input. Please enter an alphabet. " +strIndexException);
            }
        });


        textPanel.add(mysteryWord);
        textPanel.add(word);
        textPanel.add(enter);
        textPanel.add(userText);
        textPanel.add(okButton);
        textPanel.add(display);
        textPanel.add(triedAlphabets);
        textPanel.add(lifeLabel);

        add(textPanel);
        pack();

    }

    // Calling the parent method that changes the hangman image for each wrong try

    public void changeImage(){
        super.changeImage();
    }
}
