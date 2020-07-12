package com.hangman1;

public class Main {

    static int gameOption;
    public static void main(String[] args) {

        // Create the object to display and play game.


        //HangmanDisplay frame = new HangmanSinglePlayer();
        HangmanDisplay frame = new HangmanTwoPlayers();
        frame.setVisible(true);

    }
}
/*
Abstract Parent class - inherited by 2 child class (1) - DisplayHangman & (2) - HangmanTwoPlayers.

1. Open for extension and closed for modification - without modifying the existing code we can extend for multiple players.
2. SRP - Single responsiblity for each class.
3. Interface Segregation - using Interface */