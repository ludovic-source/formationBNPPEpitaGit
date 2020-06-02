package com.ludovic.algorithmes;

import java.util.Scanner;

public class MainLevenshteinP52 {

    public static void main(String[] args) {

        // Entrée de la chaine d'origine
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir la chaine à transformer :");
        String chaineOrigine = sc.nextLine();

        // Entrée de la chaine finale/cible (resultat de la transformation)
        System.out.println("Veuillez saisir la chaine cible :");
        String chaineCible = sc.nextLine();

        // Calcul de la distance d'édition de Levenshtein
        System.out.println("Plus petit nbre de transformation nécessaire = " + EditionLevenshtein(chaineOrigine, chaineCible));

    }

    public static int EditionLevenshtein(String chaineOrigine, String chaineCible) {

        int distanceLevenshtein = 0;

        // MISE EN PLACE DE LA GRILLE DES DISTANCES : ordonné = chaineCible / abscisse = chaineOrigine
        int maxLignes = chaineCible.length() + 1;
        int maxColonnes = chaineOrigine.length() + 1;
        int grilleDistances[][] = new int[maxLignes][maxColonnes];
        for (int indexLigne = 0; indexLigne < maxLignes; indexLigne++) {
            grilleDistances[indexLigne][0] = indexLigne;
        }
        for (int indexColonne = 0; indexColonne < maxColonnes; indexColonne++) {
            grilleDistances[0][indexColonne] = indexColonne;
        }

        // REMPLISSAGE DE LA GRILLE EN VERIFIANT LE COUT DE CHAQUE OPERATION DE TRANSFORMATION
        // --- soit une suppression +1 (déplacement horizontal vers la droite)
        // --- soit une insertion +1 (déplacement vertical vers le bas)
        // --- soit une substitution + 1 (déplacement diagonal vers le coin bas/droit)
        // --- soit on conserve la lettre + 0 (déplacement diagonal vers le coin bas/droit)
        for (int indexLigne = 0; indexLigne < maxLignes - 1; indexLigne++) {
            for (int indexColonne = 0; indexColonne < maxColonnes - 1; indexColonne++) {
                if (chaineOrigine.charAt(indexColonne) == chaineCible.charAt(indexLigne)) {
                    grilleDistances[indexLigne + 1][indexColonne + 1] = grilleDistances[indexLigne][indexColonne];
                } else {
                    if (grilleDistances[indexLigne][indexColonne] > grilleDistances[indexLigne+1][indexColonne]
                            || grilleDistances[indexLigne][indexColonne] > grilleDistances[indexLigne][indexColonne+1]) {
                        grilleDistances[indexLigne + 1][indexColonne + 1] = grilleDistances[indexLigne][indexColonne];
                    } else {
                        grilleDistances[indexLigne + 1][indexColonne + 1] = grilleDistances[indexLigne][indexColonne] + 1;
                    }
                }
            }
        }

        /*
        // AFFICHAGE DE LA GRILLE DES DISTANCES CALCULEES
        System.out.print("    ");
        for (int i = 0; i < chaineOrigine.length(); i++) {
            System.out.print(" " + chaineOrigine.charAt(i) + "  ");
        }
        for (int indexLigne = 0; indexLigne < maxLignes; indexLigne++) {
            System.out.println("");
            for (int indexColonne = 0; indexColonne < maxColonnes; indexColonne++) {
                System.out.print(" - " + grilleDistances[indexLigne][indexColonne]);
            }
            System.out.println("");
            if (indexLigne < chaineCible.length()) {
                System.out.print(chaineCible.charAt(indexLigne));
            }
        }

         */
        distanceLevenshtein = grilleDistances[maxLignes-1][maxColonnes-1];

        return distanceLevenshtein;

    }



}
