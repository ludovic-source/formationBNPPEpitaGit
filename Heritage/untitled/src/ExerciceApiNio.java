import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciceApiNio {

    public static void main(String[] args) throws IOException {

        ArrayList<String> petitPapaNoel = new ArrayList<>();
        petitPapaNoel.add("Petit papa Noël, ");
        petitPapaNoel.add("Quand tu descendras du ciel, ");
        petitPapaNoel.add("Avec des jouets par milliers,");
        petitPapaNoel.add("N'oublie pas mon petit soulier.");
        petitPapaNoel.add("Mais avant de partir, ");
        petitPapaNoel.add("Il faudra bien te couvrir, ");
        petitPapaNoel.add("Dehors tu dois avoir si froid, ");
        petitPapaNoel.add("C'est un peu à  cause de moi.");

        Path path = Paths.get("C:/Users/stagiaire/IdeaProjects/Heritage/untitled/src/PetitPapaNoel.txt");
        System.out.println(path.toAbsolutePath());

        // Pour vérifier si le fichier existe
        boolean fichierExists = Files.exists(path);
        if (fichierExists != true) {
            System.out.println("le fichier n'existe pas");
            Files.createFile(path);
            Files.write(path, petitPapaNoel, StandardOpenOption.APPEND);
        }
        else {
            System.out.println("le fichier existe");
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();  // pour charger en mémoire une seule ligne du fichier
                while (line != null) {
                    System.out.println(line);
                   // bufferedReader = Files.newBufferedReader(path);
                    line = bufferedReader.readLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("fin de lecture du fichier");
            }
        }

        // Autre méthode pour lire un fichier - ici tout le fichier est chargé en mémoire
        List<String> lecturePetitPapaNoel = Files.readAllLines(path);
        System.out.println(lecturePetitPapaNoel); // pour lire toutes les lignes en même temps
        System.out.println("fin de lecture du fichier");
        System.out.println(lecturePetitPapaNoel.get(0)); // pour lire juste une ligne - ici la 1ère

        // compter le nombre d'occurences de chaque mot et l'afficher
        Map<String, Integer> mapNbreOccurencesChaqueMot = new HashMap<>();
        mapNbreOccurencesChaqueMot = compterOccurenceChaqueMot(lecturePetitPapaNoel);

        System.out.println(mapNbreOccurencesChaqueMot);

        // pour lire le répertoire courant - sert notamment à trouver le lieu où lire le fichier
        //System.out.println(System.getProperty("user.dir"));

        // écrire le dictionnaire map dans un fichier occurence.txt
        Path pathOccurence = Paths.get("C:/Users/stagiaire/IdeaProjects/Heritage/untitled/src/occurence.txt");
        boolean fichierOccurenceExists = Files.exists(pathOccurence);
        if (fichierOccurenceExists != true) {
            System.out.println("le fichier occurence.txt n'existe pas");
            Files.createFile(pathOccurence);
            System.out.println(mapNbreOccurencesChaqueMot.keySet());
            ArrayList<String> ligneAEcrire = new ArrayList<>();

            for (String setMot : mapNbreOccurencesChaqueMot.keySet()) {
                ligneAEcrire.add(setMot+": "+mapNbreOccurencesChaqueMot.get(setMot));
            }
            Files.write(pathOccurence, ligneAEcrire, StandardOpenOption.APPEND);
        }

        // écrire le nombre de mots différents en fin de fichier
        int nbreMots = mapNbreOccurencesChaqueMot.size();
        ArrayList<String> ligneFinDeFichier = new ArrayList<>();
        ligneFinDeFichier.add("nombre de mots différents = " + nbreMots);
        Files.write(pathOccurence, ligneFinDeFichier, StandardOpenOption.APPEND);

        // récuperer et afficher la liste des mots les + présents dans le texte
        System.out.println("les mots les plus présents dans le texte : ");
        List<String> motsLesPlusPresents = getMotsLesPlusPresents(mapNbreOccurencesChaqueMot);
        for (String chaqueMot : motsLesPlusPresents) {
            System.out.println(chaqueMot);
        }

    }

    private static Map<String, Integer> compterOccurenceChaqueMot(List<String> texte) {

        Map<String, Integer> mapNbreOccurencesChaqueMot = new HashMap<>(); // clé = mot ; valeur = nbre occurences du mot

        int nbreDeLignes = texte.size();
        System.out.println("le texte a "+ nbreDeLignes + " lignes.");
        List<String> listeMotsPetitPapaNoel = new ArrayList<>();

        for (int chaqueLigne = 0; chaqueLigne < nbreDeLignes; chaqueLigne++) {
            String[] tableauMotsPetitPapaNoel = texte.get(chaqueLigne).split("[, .']+");
            for (String mot : tableauMotsPetitPapaNoel) {
                // listeMotsPetitPapaNoel.add(mot);
                mot = mot.toLowerCase();
                if (mapNbreOccurencesChaqueMot.containsKey(mot) == true) {
                    mapNbreOccurencesChaqueMot.put(mot, mapNbreOccurencesChaqueMot.get(mot)+1);
                }
                else {
                    mapNbreOccurencesChaqueMot.put(mot, 1);
                    System.out.println(mot);
                }
            }
        }

        return mapNbreOccurencesChaqueMot;
    }

    public static List<String> getMotsLesPlusPresents(Map<String, Integer> mapNbreOccurencesChaqueMot) {

        int nbreOccurenceLaPlusGrande = 0;
        List<String> listeMotsOccurenceLaPlusGrande = new ArrayList<>();

        for (String setMot : mapNbreOccurencesChaqueMot.keySet()) {
            if (mapNbreOccurencesChaqueMot.get(setMot) > nbreOccurenceLaPlusGrande) {
                listeMotsOccurenceLaPlusGrande.clear();
                listeMotsOccurenceLaPlusGrande.add(setMot);
                nbreOccurenceLaPlusGrande = mapNbreOccurencesChaqueMot.get(setMot);
            } else {
                if (mapNbreOccurencesChaqueMot.get(setMot) == nbreOccurenceLaPlusGrande) {
                    listeMotsOccurenceLaPlusGrande.add(setMot);
                }
            }
        }

        // System.out.println("mots les + grands : " + listeMotsOccurenceLaPlusGrande);
        return listeMotsOccurenceLaPlusGrande;
    }


}

