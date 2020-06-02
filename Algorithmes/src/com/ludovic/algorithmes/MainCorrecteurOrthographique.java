package com.ludovic.algorithmes;

import javax.jnlp.SingleInstanceListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainCorrecteurOrthographique {

    private static List<String> motsDictionnaire = new ArrayList<>();

    // la liste des motsCorriges est classées de la plus petite Edition à la plus grande
    // index 0 = plus petite édition & index 4 = plus grande édition
    private static ArrayList<MotCorrige> listeMotsCorriges = new ArrayList<>();

    public static void main(String[] args) {

        initialiserDictionnaire();
        initialiserListeMotsProposes();
        String motEcrit = recupererMotEcrit();
        for (String chaqueMot : motsDictionnaire) {
            rechercherMotsProposes(motEcrit, chaqueMot);
        }
        System.out.println("liste des mots proposés : ");
        for (MotCorrige motCorrige : listeMotsCorriges) {
            System.out.println("mot = " + motCorrige.getMot() + " --- distance = " + motCorrige.getDistanceEditionLevenshtein()
            + " --- position dans la liste : " + listeMotsCorriges.indexOf(motCorrige));
        }

    }

    private static String recupererMotEcrit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le mot à corriger :");
        String motEcrit = sc.nextLine();
        return motEcrit;
    }

    private static void initialiserListeMotsProposes() {
        MotCorrige motCorrige = new MotCorrige(" ", 50);
        for (int i = 0;i < 5; i++) {
            listeMotsCorriges.add(i, motCorrige);
        }
    }

    private static void rechercherMotsProposes(String motEcrit, String chaqueMot) {
        int distanceEditionRecherche = MainLevenshteinP52.EditionLevenshtein(motEcrit, chaqueMot);
        int indexTrouve = 5;
        for (int i = 4; i >= 0; i--) {
            MotCorrige motCorrige = listeMotsCorriges.get(i);
            if (distanceEditionRecherche < motCorrige.getDistanceEditionLevenshtein()
                || motCorrige.getDistanceEditionLevenshtein() == 50 ) {
                indexTrouve = i;
            }
        }
        if (indexTrouve < 5) {
            listeMotsCorriges.remove(4);
            MotCorrige newMotCorrige = new MotCorrige(chaqueMot, distanceEditionRecherche);
            listeMotsCorriges.add(indexTrouve, newMotCorrige);
        }
    }

    public static void initialiserDictionnaire() {
        lireFichier();
    }

    public static void lireFichier() {

        Path path = Paths.get("C:/Users/stagiaire/IdeaProjects/Algorithmes/dictionnaire.txt");
        System.out.println(path.toAbsolutePath());

        // Pour vérifier si le fichier existe
        boolean fichierExists = Files.exists(path);
        if (fichierExists != true) {
            System.out.println("le fichier à importer n'existe pas");
        }
        else {
            BufferedReader bufferedReader = null;
            try {
                System.out.println("début de lecture du fichier");
                bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();  // pour charger en mémoire une seule ligne du fichier
                while (line != null) {
                    // System.out.println(line);
                    ajouterMotDictionnaire(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("fin de lecture du fichier");
            }
        }

    }

    public static void ajouterMotDictionnaire(String line) {
        motsDictionnaire.add(line);
    }

}
