package com.ludovic.ocr.coursJava;

import java.awt.*;

public class Point {

    private Color couleur;
    private int taille = 10;
    private int posX = -10;
    private int posY = -10;
    private String type = "ROND";

    public Point() {

    }

    public Point(int posX, int posY, int taille, Color couleur, String type) {
        this.couleur = couleur;
        this.taille = taille;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
