package com.hangman1;

public class Main {

    static int gameOption;
    public static void main(String[] args) {

        // Create the object to display and play game.
        HangmanGame frame = new HangmanSinglePlayer();
        frame.setVisible(true);

    }
}