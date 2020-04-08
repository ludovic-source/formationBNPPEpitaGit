package com.ludovic.algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainTextSur9TouchesP36Test {

    // IL S'AGIT d'UNE AUTRE SOLUTION

    // initialisation du dictionnaire : la clé est un mot, et la valeur est le poids d'importance du mot
    // 1 est le poids le plus fort -- 10 est le poids le plus faible
    private static Dictionnaire dictionnaire = new Dictionnaire();

    // initialisation table Correspondance ; clé = lettre alphabet et valeur = chiffres
    private static HashMap<String, String> mapCorrespondances = new HashMap<>();

    public static void main(String[] args) {

        dictionnaire.initialiserDictionnaire();

        // entrée
        String sequenceChiffres = "266";
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
        String chiffreAComparer;

        for (String chaqueMot : setDictionnaire) {
            int nombreDeLettresEnCommun = 0;
            for (int i = 0; i < sequenceChiffres.length() && i < chaqueMot.length(); i++) {
                if (i == chaqueMot.length() - 1) {
                    chiffreAComparer = mapCorrespondances.get(chaqueMot.substring(i));
                }
                else {
                    chiffreAComparer = mapCorrespondances.get(chaqueMot.substring(i, i+1));
                }
                if (i == sequenceChiffres.length() - 1) {
                    if (chiffreAComparer.equals(sequenceChiffres.substring(i))) {
                        nombreDeLettresEnCommun++;
                    }
                }
                else {
                    if (chiffreAComparer.equals(sequenceChiffres.substring(i, i+1))) {
                        nombreDeLettresEnCommun++;
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

        mapCorrespondances.put("a", "2");
        mapCorrespondances.put("b", "2");
        mapCorrespondances.put("c", "2");
        mapCorrespondances.put("d", "3");
        mapCorrespondances.put("e", "3");
        mapCorrespondances.put("f", "3");
        mapCorrespondances.put("g", "4");
        mapCorrespondances.put("h", "4");
        mapCorrespondances.put("i", "4");
        mapCorrespondances.put("j", "5");
        mapCorrespondances.put("k", "5");
        mapCorrespondances.put("l", "5");
        mapCorrespondances.put("m", "6");
        mapCorrespondances.put("n", "6");
        mapCorrespondances.put("o", "6");
        mapCorrespondances.put("p", "7");
        mapCorrespondances.put("q", "7");
        mapCorrespondances.put("r", "7");
        mapCorrespondances.put("s", "7");
        mapCorrespondances.put("t", "8");
        mapCorrespondances.put("u", "8");
        mapCorrespondances.put("v", "8");
        mapCorrespondances.put("w", "9");
        mapCorrespondances.put("x", "9");
        mapCorrespondances.put("y", "9");
        mapCorrespondances.put("z", "9");

    }

}
