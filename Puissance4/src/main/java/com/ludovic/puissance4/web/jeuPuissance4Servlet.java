package com.ludovic.puissance4.web;

import com.ludovic.puissance4.Plateau;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class jeuPuissance4Servlet extends HttpServlet {

    private String nomJoueurJaune;
    private String nomJoueurRouge;
    private String couleurJoueur;
    private int numeroColonneJouee;
    private String[][] plateauJeu;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       // pour indiquer qu'on va utiliser du HTML pour notre réponse

        if (req.getQueryString() == null) {     // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET

            displayIndex(resp);                 // pas de paramètres, on affiche juste la page d'erreur
        }
        else {
            displayJeu(req, resp);              // on affiche le plateau de jeu, le nom des joueurs, et la saisie de la colonne
        }
    }

    private void displayIndex(HttpServletResponse resp) throws IOException {

        String form = "si on passe là, c'est une erreur car il doit manquer les paramètres";

        resp.getWriter().println(form);
    }

    private void displayJeu(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("paramètres : " +req.getQueryString());

        // récupérer les infos de session dans le HttpSession
        HttpSession session = req.getSession(false);
        nomJoueurJaune = (String) session.getAttribute("joueurJaune");
        nomJoueurRouge = (String) session.getAttribute("joueurRouge");
        couleurJoueur = (String) session.getAttribute("couleurJoueur");
        plateauJeu = (String[][]) session.getAttribute("plateauJeu");

        // lecture des paramètres : juste le numero de colonne jouée par le joueur
        numeroColonneJouee = splitParameters(req.getQueryString());

        // positionner le prochain joueur
        String joueur;
        if (session.getAttribute("couleurJoueur").equals("jaune")) {
            // le prochain joueur est rouge
            session.setAttribute("couleurJoueur", "rouge");
            joueur = "rouge" + " " + nomJoueurRouge;
        }
        else {
            // le prochain joueur est jaune
            session.setAttribute("couleurJoueur", "jaune");
            joueur = "jaune" + " " + nomJoueurJaune;
        }

        // mettre la pièce jouée dans la bonne colonne du plateau de jeu
        Plateau plateau = new Plateau();
        plateauJeu = plateau.ajouterJetonPlateau(plateauJeu, numeroColonneJouee, couleurJoueur);
        session.setAttribute("plateauJeu", plateauJeu);

        // préparation du plateau de jeu HTML
        String plateauHTML = plateau.construirePlateauJeuHTMLVide(plateauJeu);

        // préparation de la page HTML à afficher
        String form = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\" />" +
                "<link rel=\"stylesheet\" href=\"stylePuissance4.css\">"+
                "<title>Puissance 4 - accueil</title>"+
                "</head>"+

                "<body>"+

                "<header>"+
                "<div id=\"titreJeu\">"+
                "<h1>Puissance 4</h1>"+
                "</div>"+
                "<div id=\"message\">"+
                "<p>Bienvenue pour jouer à ce méga jeu<br /><br /></p>"+
                "</div>"+
                "</header>"+

                "<section>"+

                "<div id=\"reference_joueur\">"+
                "<form method=\"get\" action=\"jeuPuissance4\">"+
                "<fieldset>"+
                "<legend>Choisissez votre colonne</legend>"+
                "<p>Le joueur "+ joueur + " joue : </p>"+
                "<label for=\"numero_colonne\">numéro colonne</label>"+
                "<select name=\"numero_colonne\" id=\"numero_colonne\">"+
                "<option value=\"0\">0</option>"+
                "<option value=\"1\">1</option>"+
                "<option value=\"2\">2</option>"+
                "<option value=\"3\">3</option>"+
                "<option value=\"4\">4</option>"+
                "<option value=\"5\">5</option>"+
                "<option value=\"6\">6</option>"+
                "</select>"+
                "<input type=\"submit\" value=\"valider\">"+
                "</fieldset><br />"+
                "</form>"+
                "</div>"+

                plateauHTML +

                "</section>"+

                "</body>"+

                "</html>";

        resp.getWriter().println(form);

    }

    public int splitParameters(String queryString) {

        int compteurParametre = 0;
        int numeroColonne = 0;

        String[] tableauParametre = queryString.split("[?&=]");

        for (String parametre : tableauParametre) {
            if (parametre.equals("numero_colonne")) {
                numeroColonne = Integer.parseInt(tableauParametre[compteurParametre + 1]);
            }
            compteurParametre += 1;
        }
        return numeroColonne;
    }

}
