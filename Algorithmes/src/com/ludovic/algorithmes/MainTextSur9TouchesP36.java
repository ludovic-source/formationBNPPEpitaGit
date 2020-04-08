package com.ludovic.algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainTextSur9TouchesP36 {

    // initialisation du dictionnaire : la clé est un mot, et la valeur est le poids d'importance du mot
    // 1 est le poids le plus fort -- 10 est le poids le plus faible
    private static Dictionnaire dictionnaire = new Dictionnaire();

    // initialisation table Correspondance ; clé = lettre alphabet et valeur = chiffres
    private static HashMap<String, ArrayList<String>> mapCorrespondances = new HashMap<>();

    public static void main(String[] args) {

        dictionnaire.initialiserDictionnaire();

        // entrée
        String sequenceChiffres = "2428";
        // sortie
        String motIdentifie;

        // rechercher le mot correspondant de poids le plus fort
        motIdentifie = rechercherMot(sequenceChiffres);

        System.out.println("Le mot le plus fort est : " + motIdentifie);

    }

    private static String rechercherMot(String sequenceChiffres) {

        initialiserTableCorrespondance();

        String motLePlusProbable = "";
        int poidsDuMotLePlusProbable = 11;         // car 10 est le poids le plus faible dans le dictionnaire
        Set<String> setDictionnaire = dictionnaire.recupererSetDictionnaire();
        ArrayList<String> lettres = new ArrayList<>();

        for (String chaqueMot : setDictionnaire) {
            int nombreDeLettresEnCommun = 0;
            for (int i = 0; i < sequenceChiffres.length() && i < chaqueMot.length(); i++) {
                String chiffre = String.valueOf(sequenceChiffres.charAt(i));
                lettres = getLettres(chiffre);
                for (int j = 0; j < lettres.size(); j++) {
                    if (i == chaqueMot.length() - 1) {
                        if (lettres.get(j).equals(chaqueMot.substring(i))) {
                            nombreDeLettresEnCommun++;
                        }
                    }
                    else {
                        if (lettres.get(j).equals(chaqueMot.substring(i, i+1))) {
                            nombreDeLettresEnCommun++;
                        }
                    }
                }
            }
            if (nombreDeLettresEnCommun == sequenceChiffres.length()) {
                if (poidsDuMotLePlusProbable > dictionnaire.getPoids(chaqueMot)) {
                    motLePlusProbable = chaqueMot;
                    poidsDuMotLePlusProbable = dictionnaire.getPoids(chaqueMot);
                }
            }
        }

        return motLePlusProbable;
    }

    private static void initialiserTableCorrespondance() {

        ArrayList<String> listeLettres2 = new ArrayList<>();
        listeLettres2.add("a") ; listeLettres2.add("b"); listeLettres2.add("c");
        mapCorrespondances.put("2", listeLettres2);
        ArrayList<String> listeLettres3 = new ArrayList<>();
        listeLettres3.add("d") ; listeLettres3.add("e"); listeLettres3.add("f");
        mapCorrespondances.put("3", listeLettres3);
        ArrayList<String> listeLettres4 = new ArrayList<>();
        listeLettres4.add("g") ; listeLettres4.add("h"); listeLettres4.add("i");
        mapCorrespondances.put("4", listeLettres4);
        ArrayList<String> listeLettres5 = new ArrayList<>();
        listeLettres5.add("j") ; listeLettres5.add("k"); listeLettres5.add("l");
        mapCorrespondances.put("5", listeLettres5);
        ArrayList<String> listeLettres6 = new ArrayList<>();
        listeLettres6.add("m") ; listeLettres6.add("n"); listeLettres6.add("o");
        mapCorrespondances.put("6", listeLettres6);
        ArrayList<String> listeLettres7 = new ArrayList<>();
        listeLettres7.add("p") ; listeLettres7.add("q");  listeLettres7.add("r"); listeLettres7.add("s");
        mapCorrespondances.put("7", listeLettres7);
        ArrayList<String> listeLettres8 = new ArrayList<>();
        listeLettres8.add("t") ; listeLettres8.add("u");  listeLettres8.add("v");
        mapCorrespondances.put("8", listeLettres8);
        ArrayList<String> listeLettres9 = new ArrayList<>();
        listeLettres9.add("w") ; listeLettres9.add("x");  listeLettres9.add("y"); listeLettres9.add("z");
        mapCorrespondances.put("9", listeLettres9);

    }

    private static ArrayList<String> getLettres(String chiffre) {
        return mapCorrespondances.get(chiffre);
    }

}
