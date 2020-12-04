package com.ludovic;


import java.util.Scanner;

public class App {

    public static void main( String[] args ) {

        // Exercice 2
        int a = 3;
        int b = 5;
        System.out.println("avant permutation :");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        int c = a;
        a = b;
        b = c;
        System.out.println("après permutation :");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Quel est votre personnage préféré ?");
        //String str = sc.next();
        //System.out.println("Votre personnage préféré est " + str);

        String[][] tableau = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 8; j >= 0; j--) {
                tableau[i][j] = String.valueOf(j);
            }
        }
        for (int i = 0; i <9; i++) {
            System.out.println("");
            System.out.print("ligne n° : " + i + " --->");
            for (int j = 0; j < 9; j++) {
                System.out.print(" - " + tableau[i][j]);
            }
        }


    }
}
