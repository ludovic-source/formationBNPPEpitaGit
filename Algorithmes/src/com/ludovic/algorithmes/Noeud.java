package com.ludovic.algorithmes;

import java.util.HashMap;
import java.util.TreeMap;

public class Noeud {

    private char lettreCourante;
    private char lettrePrecedente;
    private int indexPrecedent;

    public Noeud(char lettreCourante, char lettrePrecedente, int indexPrecedent) {
        this.lettreCourante = lettreCourante;
        this.lettrePrecedente = lettrePrecedente;
        this.indexPrecedent = indexPrecedent;
    }

    public char getLettreCourante() {
        return this.lettreCourante;
    }

    public int getIndexPrecedent() {
        return this.indexPrecedent;
    }

    public char getLettrePrecedente() {
        return this.lettrePrecedente;
    }
}
