package com.hangman1;

import java.util.ArrayList;

public class Hangman{

    private String word;
    private static int life = 6;
    char[] filler;

    public Hangman(String word, int life) {
        this.word = word.toUpperCase();
        this.life = life;
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
                System.out.println("life " + this.getLife());
                char temp = getValue;
                char x = Character.toUpperCase(temp);
                System.out.println("Alphabet from textfield is : " + x + " and contains x is " + aList.contains(x));

                if (aList.contains(x)) {
                    System.out.println("Alphabet already tried.. ");
                }
                aList.add(x);
                if (word.contains(x + "")) {
                    System.out.println("Inside if loop.. with value of x - " +x);
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
                System.out.println("Invalid input");
            }
            break;
        }

        if (this.getLife() == 0) {
            System.out.println("You lose");
        }
        System.out.println("Life before return is " +this.getLife());
        return filler;
    }

    // Method that checks the result

    public boolean checkResult(){
        boolean win = false;
        if (word.equals(String.valueOf(filler))) {
            System.out.println(filler);
            System.out.println("Congratulations - YOU WIN");
            win = true;
        }
        System.out.println(filler);
        System.out.println("You can try " + life + " times");
        return win;
    }
}