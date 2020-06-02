package com.ludovic.algorithmes;

import java.util.ArrayList;

public class MainArbreLexicographique {

    private static ArrayList<String> listeMots = new ArrayList<>();
    private static ArrayList<Branche> arbre = new ArrayList<>();

    public static void main(String[] args) {

        initialisationListeMots();
        creationArbreLexicographique();
        affichageArbreLexicographique();

    }

    private static void affichageArbreLexicographique() {

        int numeroBranche = 0;
        for (Branche chaqueBranche : arbre) {
            System.out.println("Nouvelle branche n° : " + numeroBranche);
            ArrayList<Noeud> listeNoeuds = chaqueBranche.getListeNoeuds();
            for (Noeud chaqueNoeud : listeNoeuds) {
                System.out.println("Lettre courante : " + chaqueNoeud.getLettreCourante()
                                + " - lettre precedente : " + chaqueNoeud.getLettrePrecedente()
                                + " - index lettre precedente : " + chaqueNoeud.getIndexPrecedent());
            }
            numeroBranche++;
        }

    }

    public static void creationArbreLexicographique() {

        for (String chaqueMot : listeMots) {
            int indexPrecedent = 0;
            for (int i = 0; i < chaqueMot.length(); i++) {
                if (i < arbre.size()) {
                    Branche branche = arbre.get(i);
                    ArrayList<Noeud> listeNoeuds = branche.getListeNoeuds();
                    int indexNoeud = 0;
                    boolean noeudTrouve = false;
                    for (Noeud chaqueNoeud : listeNoeuds) {
                        if (chaqueNoeud.getLettreCourante() == chaqueMot.charAt(i)
                                && indexPrecedent == chaqueNoeud.getIndexPrecedent()) {
                            indexPrecedent = indexNoeud;
                            noeudTrouve = true;
                        }
                        indexNoeud++;
                    }

                    if (!noeudTrouve) {
                        char lettreCourante = chaqueMot.charAt(i);
                        char lettrePrecedente = ' ';
                        if (i > 0) {
                            lettrePrecedente = chaqueMot.charAt(i - 1);
                        }
                        Noeud noeud = new Noeud(lettreCourante, lettrePrecedente, indexPrecedent);
                        branche.ajouterNoeud(noeud);
                        indexPrecedent = branche.getIndexNoeud(noeud);
                        arbre.set(i, branche);
                    }

                } else {
                    Branche branche = new Branche();
                    char lettreCourante = chaqueMot.charAt(i);
                    char lettrePrecedente = ' ';
                    if (i > 0) {
                        lettrePrecedente = chaqueMot.charAt(i-1);
                    }
                    Noeud noeud = new Noeud(lettreCourante, lettrePrecedente, indexPrecedent);
                    branche.ajouterNoeud(noeud);
                    indexPrecedent = branche.getIndexNoeud(noeud);
                    arbre.add(i, branche);
                }
            }
            if (chaqueMot.length() < arbre.size()) {
                Branche branche = arbre.get(chaqueMot.length());
                char lettreCourante = '/';
                char lettrePrecedente = chaqueMot.charAt(chaqueMot.length() - 1);
                Noeud noeud = new Noeud(lettreCourante, lettrePrecedente, indexPrecedent);
                branche.ajouterNoeud(noeud);
                arbre.set(chaqueMot.length(), branche);
            } else {
                Branche branche = new Branche();
                char lettreCourante = '/';
                char lettrePrecedente = chaqueMot.charAt(chaqueMot.length() - 1);
                Noeud noeud = new Noeud(lettreCourante, lettrePrecedente, indexPrecedent);
                branche.ajouterNoeud(noeud);
                arbre.add(chaqueMot.length(), branche);
            }
        }

    }

    public static void initialisationListeMots() {
        listeMots.add("AI");
        listeMots.add("AIL");
        listeMots.add("AILE");
        listeMots.add("AINE");
        listeMots.add("ALE");
        listeMots.add("BAR");
        listeMots.add("BAS");
    }

}


