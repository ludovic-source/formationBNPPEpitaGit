package com.ludovic.bourse;

import com.ludovic.bourse.dao.ActionDAO;
import com.ludovic.bourse.dao.ClientDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //String identifiantSaisiString = "7";
        //int identifiantSaisi = Integer.parseInt(identifiantSaisiString);
        //Client client = new Client();
        //client = new ClientDAO().getClient(identifiantSaisi);

        //System.out.println("identifiant : " + client.getIdentifiant());
        //System.out.println("nom : " + client.getNom());
        //System.out.println("prénom : " + client.getPrenom());

        Path path = Paths.get("C:/Users/stagiaire/IdeaProjects/Bourse/src/main/java/com/ludovic/bourse/fichierDesCours.csv");
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
                boolean premiereLigne = true;
                while (line != null) {
                    System.out.println(line);
                    if (!premiereLigne) {
                        chargerAction(line);
                    }
                    premiereLigne = false;
                    line = bufferedReader.readLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("fin de lecture du fichier");
            }
        }

    }

    private static void chargerAction(String ligneAction) {

        // format fichier = "Valeur","Dernier","Var. %","+Haut","+Bas","Volumes","31 Dec."
        String[] tableauAction = ligneAction.split("[,]");

        Action action = new Action();

        action.setLibelle(tableauAction[0]);
        action.setValeurCourante(Float.parseFloat(tableauAction[1]));

        ActionDAO actionDAO = new ActionDAO();
        actionDAO.setAction(action);

    }

}
