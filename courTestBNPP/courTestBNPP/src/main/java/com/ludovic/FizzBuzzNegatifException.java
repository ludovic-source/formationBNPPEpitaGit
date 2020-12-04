package com.ludovic;

public class FizzBuzzNegatifException extends Exception {

    public FizzBuzzNegatifException() {
        super("No negative numbers allowed");
    }
}
