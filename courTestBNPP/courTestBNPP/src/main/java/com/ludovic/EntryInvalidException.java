package com.ludovic;

public class EntryInvalidException extends RuntimeException {

    private char caractereErrone;
    private int positionErronee;
    private String messageSpecifique;

    public EntryInvalidException(char caractereErrone, int positionErronee) {
        super("Number expected but ‘" + caractereErrone +"’ found at position " + positionErronee);
    }

    public EntryInvalidException(String messageSpecifique) {
        super(messageSpecifique);
    }
}
