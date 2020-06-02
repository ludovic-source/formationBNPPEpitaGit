package com.ludovic.algorithmes;

public class Voiture implements Cloneable {

    // exercice de clonage

    private String marque = "ferrari";
    private String categorie = "sport";

    public String getMarque() {
        return this.marque;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Voiture voiture = (Voiture) super.clone();
        return super.clone();
    }
}
