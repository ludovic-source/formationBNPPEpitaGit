package com.ludovic.bourse.web;

import com.ludovic.bourse.Action;
import com.ludovic.bourse.Client;
import com.ludovic.bourse.dao.ActionDAO;
import com.ludovic.bourse.dao.ClientDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListeDesCoursServlet extends HttpServlet {

    SimpleDateFormat format = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss aaa");
    Date date = new Date();

    private int identifiant;
    private String nomClient;

    ArrayList<Action> listeActions = new ArrayList<Action>();
    String lignesTableau = "<table>" +
                                "<caption>Liste des cours du CAC 40</caption>" +
                                "<tr>" +
                                    "<th>Libellé</th>" +
                                    "<th>Cours actuel</th>" +
                                "</tr>";

    @Override
    public void init() throws ServletException {

        // C'est la méthode qui va être appelée par Jetty quand il va instancier un objet de type  HttpServlet.
        // Cette méthode est utilisée une fois au début. Elle permet d'initialiser le contexte.
        // On récupère la liste des actions à afficher dans le displayForm
        listeActions = new ActionDAO().getAllActions();

        for (Action chaqueAction : listeActions) {

            lignesTableau += "<tr>" +
                                "<td>" + chaqueAction.getLibelle() + "</td>" +
                                "<td>" + chaqueAction.getValeurCourante() + "</td>" +
                            "</tr>";

        }
        lignesTableau += "</table>";

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       // pour indiquer qu'on va utiliser du HTML pour notre réponse

        // récupérer les infos de session dans le HttpSession
        HttpSession session = req.getSession(false);
        identifiant = (int) session.getAttribute("identifiant");
        nomClient = session.getAttribute("prenom") + " " + session.getAttribute("nom");

        if (req.getQueryString() == null) {     // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET
            displayForm(resp);                  // pas de paramètres, on affiche le juste le tableau
        }
        else {
            displayConnexion(req, resp);         //
        }
    }

    private void displayForm(HttpServletResponse resp) throws IOException {

        String form = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\" />" +
                "<link rel=\"stylesheet\" href=\"styleBourse.css\">" +
                "<title>La Bourse de Saint-Malo - accueil</title>" +
                "</head>" +
                "<body>" +

                "<header>" +
                "<div id=\"bloc_logo\">" +
                "<img src=\"images/logo.jpg\" alt=\"logo\" title=\"image du logo\" id=\"logo\">" +
                "<h1>Bourse de Saint Malo</h1>" +
                "</div>" +

                "<div id=\"reference_client\">" +
                "<p id=\"date\">" + format.format(date) + "</p>"+
                "<p id=\"nom\"><strong>" + nomClient + "</strong></p>"+
                "</div>"+
                "</header>"+
                "<!-- affichage du bandeau permanent -->"+
                "<div id=\"bandeau\">"+
                "<div id=\"bandeau_description\">"+
                "Vue panoramique de Saint-Malo"+
                "</div>"+
                "</div>"+

                "<nav>"+
                "<div class=\"menu\"><a href=\"index.html\">ACCUEIL</a></div>"+
                "<div class=\"menu\"><a href=\"pageListeDesCours.html\">LISTE DES COURS</a></div>"+
                "<div class=\"menu\"><a href=\"pageOrdre.html\">ORDRE DE BOURSE</a></div>"+
                "</nav>"+

                "<section>"+
                    lignesTableau +
                "</section>"+

                "<footer id=\"footer\">"+
                "<div class=\"bloc_footer\">ELEMENT 1</div>"+
                "<div class=\"bloc_footer\">ELEMENT 2</div>"+
                "<div class=\"bloc_footer\">ELEMENT 3</div>"+
                "</footer>"+

                "</body>"+

                "</html>";

        resp.getWriter().println(form);
    }

    private void displayConnexion(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("paramètres : " +req.getQueryString());

        // récupérer les infos de session dans le HttpSession
        HttpSession session = req.getSession(false);
        identifiant = (int) session.getAttribute("identifiant");
        nomClient = session.getAttribute("prenom") + " " + session.getAttribute("nom");

        String form = "erreur - pas de formulaire sur cette page normalement";

        resp.getWriter().println(form);

    }

}
