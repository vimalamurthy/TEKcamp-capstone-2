package com.hangman1;

public class Logger {

    private static Logger instance;

    private Logger(){

    }
    public static Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }

    public void log(String logStr){
        System.out.println(logStr);
    }
}
