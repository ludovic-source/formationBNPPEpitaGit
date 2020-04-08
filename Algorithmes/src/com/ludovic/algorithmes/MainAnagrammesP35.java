package com.ludovic.algorithmes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class MainAnagrammesP35 {

    private static boolean premierPassage = true;
    private static int nombreSignatures = 0;

    public static void main(String[] args) {

        String ensembleDeMots = "le chien marche vers sa niche et trouve une limace de chine nue pleine de malice qui lui fait du charme";

        ArrayList<ArrayList<String>> anagrammes = new ArrayList<>();
        String signature = null;
        Boolean premierPassage = true;

        // séparer la phrase en un tableau de mots
        String[] tableauMots = creerTableauMots(ensembleDeMots);

        // calculer la signature de chaque mot
        for (String chaqueMot : tableauMots) {
            signature = calculerSignature(chaqueMot);

        // déposer la signature et le mots associés dans une liste de listes
            anagrammes = ajouterSignaturesEtMotsAssocies(chaqueMot, signature, anagrammes);

        }

        System.out.println("anagrammes trouvés : ");
        afficherAnagrammes(anagrammes);

    }

    private static ArrayList<ArrayList<String>> ajouterSignaturesEtMotsAssocies(String chaqueMot, String signature, ArrayList<ArrayList<String>> anagrammes) {

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
        return anagrammes;
    }

    private static void afficherAnagrammes(ArrayList<ArrayList<String>> anagrammes) {
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
