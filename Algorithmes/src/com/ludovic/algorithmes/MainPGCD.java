package com.ludovic.algorithmes;

public class MainPGCD {

    public static void main(String[] args) {

        int a = 9;    // a = p*x
        int b = 3;     // b = p*y
        int p = 1;      // p est le PGCD de a et b

        // calculer le plus grand diviseur de a
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                p = i;
            }
        }
        System.out.println("PGCD de " + a + " et " + b + " est : " + p);


        p = 1;
        // methode du livre
        p = pgcdRecursive(a, b);
        System.out.println("PGCD de " + a + " et " + b + " est : " + p);

    }

    public static int pgcdRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return pgcdRecursive(b, a%b);
        }
    }

}
