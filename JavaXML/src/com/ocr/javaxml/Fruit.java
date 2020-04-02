package com.ocr.javaxml;

public class Fruit {

    private String nom = null;
    private String couleur = null;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String toString() {
        return "       Fruit [nom=" + nom + ", couleur=" + couleur + "]";
    }

}
