package com.ludovic.algorithmes;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        // exercice de clonage

        Voiture sport1 = new Voiture();
        sport1.setCategorie("sport");
        sport1.setMarque("maserati");
        System.out.println("marque : " + sport1.getMarque());
        System.out.println("categorie : " + sport1.getCategorie());
        System.out.println(sport1);

        Voiture sport2;
        sport2 = (Voiture) sport1.clone();    // après j'ai bien 2 adresses mémoires différentes, donc 2 objets différents.
        System.out.println((sport2));
        System.out.println("marque : " + sport2.getMarque());
        System.out.println("categorie : " + sport2.getCategorie());

    }
}
