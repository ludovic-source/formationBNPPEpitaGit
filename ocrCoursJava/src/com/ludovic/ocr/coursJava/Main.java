package com.ludovic.ocr.coursJava;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {

        // classe Anonyme
        Dialoguer d = new Dialoguer() {
            public void parler(String question) {
                System.out.println("Tu as dis : " + question);
            }
        };
        d.parler("Bonjour");

        // lambda
        Dialoguer d2 = (s) -> System.out.println("d2 a dit : " + s);
        d2.parler("Bonjour");

    }
}
