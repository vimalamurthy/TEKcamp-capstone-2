package com.hangman1;

import java.util.Random;

abstract class Game {
    // public abstract boolean checkResult();
    public String chosenWord(){
        Random random = new Random();
        String[] mysteryWords ={"India","United States of America", "Japan", "United Kingdom","Srilanka","Mexico","Thailand", "Switzerland"};
        String chosen = mysteryWords[random.nextInt(mysteryWords.length)];
        return chosen;
    }
}
