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

public class indexPuissance4Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        // C'est la méthode qui va être appelée par Jetty quand il va instancier un objet de type  HttpServlet.
        // Cette méthode est utilisée une fois au début. Elle permet d'initialiser le contexte.

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       // pour indiquer qu'on va utiliser du HTML pour notre réponse

        if (req.getQueryString() == null) {     // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET

            displayIndex(resp);                 // pas de paramètres, on affiche le juste la page pour saisir les noms des joueurs
        }
        else {
            displayJeu(req, resp);              // on affiche le plateau de jeu, le nom des joueurs, et la saisie de la colonne
        }
    }

    private void displayIndex(HttpServletResponse resp) throws IOException {

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
                                    "<form method=\"get\" action=\"indexPuissance4\">"+
                                        "<fieldset>"+
                                            "<legend>Indiquez les noms des 2 joueurs</legend>"+
                                            "<label for=\"joueurJaune\">Joueur jaune </label>"+
                                            "<input type=\"text\" name=\"joueurJaune\" id=\"joueurJaune\" />"+
                                            "<label for=\"joueurRouge\">Joueur rouge </label>"+
                                            "<input type=\"text\" name=\"joueurRouge\" id=\"joueurRouge\" />"+
                                            "<input type=\"submit\" value=\"GO !!!!!\">"+
                                        "</fieldset><br />"+
                                    "</form>"+
                                "</div>"+
                            "</section>"+

                        "</body>"+

                      "</html>";

        resp.getWriter().println(form);
    }

    private void displayJeu(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("paramètres : " +req.getQueryString());

        // lecture des paramètres
        Map<String, String> mapParametre = new HashMap<>();
        mapParametre = splitParameters(req.getQueryString());

        // Initialiser plateau de jeu
        String[][] plateauJeu = new String[6][7];

        // sauvegarder les noms des joueurs
        HttpSession session = req.getSession();
        session.setAttribute("joueurJaune", mapParametre.get("joueurJaune"));
        session.setAttribute("joueurRouge", mapParametre.get("joueurRouge"));
        session.setAttribute("couleurJoueur", "jaune");
        session.setAttribute("plateauJeu", plateauJeu);

        // préparation du plateau de jeu HTML
        Plateau plateau = new Plateau();
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
                                        "<p>Le joueur "+ session.getAttribute("couleurJoueur") + " joue : </p>"+
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

    public Map<String, String> splitParameters(String queryString) {

        int compteurParametre = 0;

        String[] tableauParametre = queryString.split("[?&=]");
        Map<String, String> mapParametre = new HashMap<>();

        for (String parametre : tableauParametre) {
            if (parametre.equals("joueurJaune")) {
                mapParametre.put(parametre, tableauParametre[compteurParametre + 1]);
            }
            if (parametre.equals("joueurRouge")) {
                mapParametre.put(parametre, tableauParametre[compteurParametre + 1]);
            }
            compteurParametre += 1;
        }
        return mapParametre;
    }

}
