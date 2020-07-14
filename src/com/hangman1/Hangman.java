package com.hangman1;

import java.util.ArrayList;

public class Hangman{

    private String word;
    private static int life = 6;
    char[] filler;
    Logger logFile;

    public Hangman(String word, int life) {
        this.word = word.toUpperCase();
        this.life = life;
        logFile = Logger.getInstance();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    // Method to fill in the word to find with filler text

    public char[] guess() {
        filler = new char[this.word.length()];
        int i = 0;
        while (i < this.word.length()) {
            filler[i] = '-';
            if (this.word.charAt(i) == ' ') {
                filler[i] = ' ';
            }
            i++;
        }
        return filler;
    }

    // Method that checks for the alphabet entries by player.

    public char[] playGame(char getValue){
        ArrayList<Character> aList = new ArrayList<Character>();
        while (this.getLife() > 0) {
            try {
                logFile.log("life "+this.getLife());
                char temp = getValue;
                char x = Character.toUpperCase(temp);
                logFile.log("Alphabet from textfield is : " + x + " and contains x is " + aList.contains(x));

                if (aList.contains(x)) {
                    logFile.log("Alphabet already tried.. ");
                }
                aList.add(x);
                if (word.contains(x + "")) {
                    for (int y = 0; y < word.length(); y++) {
                        if (word.charAt(y) == x) {
                            filler[y] = x;
                        }
                    }
                }
                else {
                    this.setLife(--this.life);
                }
            } catch (Exception e){
                logFile.log("Invalid input");
            }
            break;
        }

        if (this.getLife() == 0) {
            logFile.log("You Lose");
        }
        logFile.log("Life before return is " +this.getLife());
        return filler;
    }

    // Method that checks if the result matches the guess word and prints output.

    public boolean checkResult(){
        boolean win = false;
        if (word.equals(String.valueOf(filler))) {
            logFile.log(String.valueOf(filler));
            logFile.log("Congratulations - YOU WIN");
            win = true;
        }
        logFile.log(String.valueOf(filler));
        logFile.log("You can try " + life + " times");
        return win;
    }
}