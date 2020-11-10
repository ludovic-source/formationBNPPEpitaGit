package com.ocr.ludovic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {

    public static int askSomething(String category, String[] responses) {

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Choix " + category);
        for (int i = 0; i < responses.length; i++) {
            System.out.println(i+1 + " - " + responses[i]);
        }
        System.out.println("Que souhaitez-vous comme " + category + " ?");

        int choixClient;
        try {
            choixClient = sc.nextInt();

            while (choixClient <= 0 || choixClient > responses.length) {
                System.out.println("Mauvais choix, recommencez");
                choixClient = sc.nextInt();
            }

        } catch (InputMismatchException ex) {
            System.out.println("il faut saisir un nombre !!!, recommencez : ");
            choixClient = sc.nextInt();
        }

        System.out.println("Vous avez choisi comme " + category + " : " + responses[choixClient-1]);

        return choixClient;
    }

}
