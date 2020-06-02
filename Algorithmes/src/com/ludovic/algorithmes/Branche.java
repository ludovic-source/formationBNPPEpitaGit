package com.ludovic.algorithmes;

import java.util.ArrayList;

public class Branche {

    private ArrayList<Noeud> listeNoeuds = new ArrayList<>();

    public void ajouterNoeud(Noeud noeud) {
        listeNoeuds.add(noeud);
    }

    public int getIndexNoeud(Noeud noeud) {
        return listeNoeuds.indexOf(noeud);
    }

    public ArrayList<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }

}
