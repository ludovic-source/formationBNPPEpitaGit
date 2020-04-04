package com.ludovic.algorithmes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class MainAnagrammesP35 {

    public static void main(String[] args) {

        String ensembleDeMots = "le chien marche vers sa niche et trouve une limace de chine nue pleine de malice qui lui fait du charme";

        ArrayList<ArrayList<String>> anagrammes = new ArrayList<>();
        String signature = null;
        Boolean premierPassage = true;
        int nombreSignatures = 0;

        // séparer la phrase en un tableau de mots
        String[] tableauMots = creerTableauMots(ensembleDeMots);

        // calculer la signature de chaque mot et la déposer dans une double liste
        for (String chaqueMot : tableauMots) {
            signature = calculerSignature(chaqueMot);
            boolean signatureTrouvee = false;

            if (premierPassage) {
                ArrayList<String> listeTampon = new ArrayList<>();
                listeTampon.add(signature);
                premierPassage = false;
                anagrammes.add(listeTampon);
                anagrammes.get(0).add(chaqueMot);
                nombreSignatures = anagrammes.size();
            }
            else {
                for (int i = 0; i < nombreSignatures - 1 && !signatureTrouvee; i++) {
                    if (anagrammes.get(i).get(0).equals(signature)) {
                        if (!anagrammes.get(i).contains(chaqueMot)) {
                            anagrammes.get(i).add(chaqueMot);
                        }
                        signatureTrouvee = true;
                    }
                }
                if (!signatureTrouvee) {
                    ArrayList<String> listeTampon = new ArrayList<>();
                    listeTampon.add(signature);
                    listeTampon.add(chaqueMot);
                    anagrammes.add(nombreSignatures, listeTampon);
                    nombreSignatures++;
                }
            }
        }

        System.out.println("anagrammes trouvés : ");
        for (int i = 0; i < anagrammes.size(); i++) {
            if (anagrammes.get(i).size() > 2) {
                System.out.print("Les anagrammes sont : ");
                for (int j = 1; j < anagrammes.get(i).size(); j++) {
                    System.out.print(anagrammes.get(i).get(j) + " ");
                }
                System.out.println("");
            }
        }

    }

    public static String[] creerTableauMots(String ensembleDeMots) {
       String[] tableauMots = ensembleDeMots.split(" ");
       return tableauMots;
    }

    public static String calculerSignature(String mot) {
        ArrayList<Character> listeLettres = new ArrayList<>();
        String signature = "";

        for (int i = 0; i < mot.length(); i++ ) {
            listeLettres.add(mot.charAt(i));
        }

        // trier les lettres par ordre alphabétique
        Collections.sort(listeLettres);

        // constituer la signature
        for (int i = 0; i < mot.length(); i++) {
            signature += listeLettres.get(i);
        }
        return signature;
    }

}
