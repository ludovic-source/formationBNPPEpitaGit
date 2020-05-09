package com.ludovic.ocr.coursJava;

public class Paire {

    private String valeur1;
    private String valeur2;

    public void Paire() {
        this.valeur1 = null;
        this.valeur2 = null;
        System.out.println("Instanciation !");
    }

    public Paire(String valeur1, String valeur2) {
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        System.out.println("Instanciation avec des paramètres !");
    }

    @Override
    public String toString() {
        return "Je suis un objet Paire{" +
                "valeur1='" + valeur1 + '\'' +
                ", valeur2='" + valeur2 + '\'' +
                '}';
    }

    public String getValeur1() {
        return valeur1;
    }

    public void setValeur1(String valeur1) {
        this.valeur1 = valeur1;
    }

    public String getValeur2() {
        return valeur2;
    }

    public void setValeur2(String valeur2) {
        this.valeur2 = valeur2;
    }
}
