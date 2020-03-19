package com.cursan.miam;

public class Four {

    int puissance;
    int capacite;

    public Four(int puissance, int capacite) {
        this.puissance = puissance;
        this.capacite = capacite;
    }

    public void cuire() {

        System.out.println("je suis un aliment");
        System.out.println("avec ma capacité de " + capacite + " litres");
        System.out.println("et ma puissance de " + puissance + " degrés.");

    }

}
