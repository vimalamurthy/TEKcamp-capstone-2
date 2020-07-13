package com.hangman1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class HangmanTwoPlayers extends HangmanGame implements IGame{

    JLabel word;
    JPasswordField acceptWord;
    String chosenWord1;
    Hangman h1;
    Logger logFile;

    // constructor
    HangmanTwoPlayers() {
        super();

        setSize(900,600);
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logFile = Logger.getInstance();
    }

    // Implementing the parent method to display random word, its empty as user input is accepted here.
    public String chosenWord(){
        return " ";
    }


    @Override
    public void instructions() {
        super.instructions();
        JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), "2. Play with another player:\n" +
                " \n" +
                " Player1 enters the word and Player2 guesses the word.\n" +
                " \n" +
                " The Player2 enters one character at a time to find the word. \n" +
                " \n" +
                " If the guess is correct, the matching character is displayed appropriately, else the number of tries \n" +
                " reduces by one.\n" +
                " \n" +
                " If the player2 gets the word correct within 6 tries, player2 wins. Otherwise player1 looses the game.");
    }

    // Calling the parent method to add the menubar with appropriate event handling

    public void addMenuBar() {
        super.addMenuBar();
    }

    //Calling the parent method to add the panel to display hangman image

    public void addImagePanel(){
        super.addImagePanel();
    }

    // Adding the text panel to accept the word to find from one player and accepting user input from another player
    // Error and exception handling for inappropriate entries from user.

    public void addTextPanel() {

        JPanel textPanel = new JPanel();
        //  logFile.log("Mystery word: " +chosenWord1);
        System.out.println("Chosenword1: Two Players: " +chosenWord1);

        textPanel.setLayout(new GridLayout(10,1));
        textPanel.setPreferredSize(new Dimension(300, 350));
        textPanel.setBorder(new EmptyBorder(2, 3, 2, 3));

        mysteryWord.setFont(new Font("Arial Black", Font.BOLD, 22));

        System.out.println("Mystery word : Two Players: " +mysteryWord);

        word = new JLabel("");
        word.setFont(new Font("Arial Black", Font.BOLD, 22));
        acceptWord =  new JPasswordField();
        acceptWord.setEchoChar('*');

        JButton getWordBtn = new JButton("Confirm");
        chosenWord1 = String.valueOf(acceptWord.getPassword());
        h1 = new Hangman(chosenWord1, 6);

        // Action Listener to accept the word to find from the player and checking for proper input.
        getWordBtn.addActionListener(ae ->{
            try {
                JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), "Continue " );
                acceptWord.setVisible(false);
                getWordBtn.setVisible(false);
                chosenWord1 = String.valueOf(acceptWord.getPassword());
                h1 = new Hangman(chosenWord1, 6);
                logFile.log("h1.guess "+String.valueOf(h1.guess()) );
                //System.out.println("h1.guess "+String.valueOf(h1.guess()) );
                word.setText(String.valueOf(h1.guess()));
                word.setVisible(true);
                //System.out.println("Inside Button Action.." +chosenWord1);
                logFile.log("Inside Button Action.." +chosenWord1);

            } catch (Exception e){
                //System.out.println("Exception handling.. Invalid input");
                logFile.log("Exception handling.. Invalid input");
            }
        });

        JLabel enter = new JLabel(" Enter alphabet of your choice : ");
        enter.setFont(new Font("Times New Roman", Font.BOLD, 20));

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

        JLabel triedAlphabets = new JLabel("");
        triedAlphabets.setFont(new Font("Times New Roman", Font.BOLD, 20));
        triedAlphabets.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        lifeLabel = new JLabel("Life Remaining :  "+h1.getLife());
        lifeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));


        // Action listener to accept input from the second player to check if the word to find matches the player's guess.
        okButton.addActionListener(e ->{
            try{
                char checkAplhabet = userText.getText().charAt(0);
                if (userText.getText().length()>1){
                    logFile.log("More than one character entered. First character will be accepted.");
                    JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), "More than one character entered. First character will be accepted.");
                }
                if ((checkAplhabet >= 'a' && checkAplhabet <= 'z') || (checkAplhabet >= 'A' && checkAplhabet <= 'Z')) {
                    triedAlphabets.setText(triedAlphabets.getText() + " " + checkAplhabet);
                    userText.setText("");
                    char[] retVal = h1.playGame(checkAplhabet);
                    word.setText(String.valueOf(retVal));
                    lifeLabel.setText(String.valueOf("Life remaining is : " + h1.getLife()));
                    if (h1.checkResult()) {
                        logFile.log("Congratulations.. You win");
                        JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), "Congratulations.. You win");
                    }
                    changeImage();
                }
                else {
                    logFile.log(" Invalid input. Please enter an alphabet. ");
                    JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), " Invalid input. Please enter an alphabet. ");
                    userText.setText("");
                }
            }
            catch (StringIndexOutOfBoundsException strIndexException){
                logFile.log(" No input. Please enter an alphabet. " +strIndexException);
                JOptionPane.showMessageDialog(HangmanTwoPlayers.super.getContentPane(), " No input. Please enter an alphabet. " +strIndexException);
            }

        });

        textPanel.add(mysteryWord);
        textPanel.add(word);
        textPanel.add(acceptWord);
        textPanel.add(getWordBtn);
        textPanel.add(enter);
        textPanel.add(userText);
        textPanel.add(okButton);
        textPanel.add(display);
        textPanel.add(triedAlphabets);
        textPanel.add(lifeLabel);

        add(textPanel);
        pack();

    }

    // Calling method from the parent that changes the hangman image for each wrong try

    public void changeImage(){
        super.changeImage();
    }
}

