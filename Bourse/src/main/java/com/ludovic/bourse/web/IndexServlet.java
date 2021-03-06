package com.ludovic.bourse.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IndexServlet extends HttpServlet {

    SimpleDateFormat format = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss aaa");
    Date date = new Date();

    @Override
    public void init() throws ServletException {

        // C'est la méthode qui va être appelée par Jetty quand il va instancier un objet de type  HttpServlet.
        // Cette méthode est utilisée une fois au début. Elle permet d'initialiser le contexte.
        // Pour générer la page d'index, nous n'avons pas besoin d'informations préalable

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       // pour indiquer qu'on va utiliser du HTML pour notre réponse
        if (req.getQueryString() == null) {     // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET
            displayIndex(resp);                  // pas de paramètres, on affiche le formulaire à remplir
        }
    }

    private void displayIndex(HttpServletResponse resp) throws IOException {

        String pageHTML = "<!DOCTYPE html>" +
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
                                    "<p id=\"nom\"><strong><a href=\"pageConnexion\">Se connecter</a></strong></p>"+
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
                                "<article>"+
                                    "<h1>Une ville d’hommes libres</h1>"+
                                        "<p>Saint Malo est né à Alet, un siècle av. J.-C. Le port gallo-romain laisse la vedette à une cité fondée sur un îlot au 12e siècle. Au 16e siècle, <strong>Jacques Cartier</strong> part découvrir le Canada et des armadas de pêche regagnent Terre-Neuve. Ecumant les routes maritimes, les armateurs assurent la fortune de la ville. Ils prospèrent à l’abri des remparts, agrandis par les disciples de Vauban. Au 18e siècle, les corsaires <strong>Duguay-Trouin et Surcouf</strong> confirment le prestige de Saint-Malo, dont l’étendard flotte au-dessus du drapeau français.</p>"+

                                    "<h1>De très accueillants remparts</h1>"+
                                        "<p>Le tour des « murs » mène de bastion en tour. D’un côté, les rues étroites de la ville. De l’autre, <strong>de magnifiques panoramas sur les plages</strong>, le port et les forts. De la porte Saint-Vincent, à l’entrée de la cité, se déploient les bassins portuaires et Saint-Servan. Entre les bastions Saint-Louis et Saint-Philippe, la vue s’ouvre sur l’estuaire et <strong>Dinard</strong>. Jusqu’à la tour Bidouane, les plages s’allongent au pied des remparts. A marée basse, le sable s’assèche jusqu’aux îles du Grand Bé et du Petit Bé. En rejoignant le château, c’est la <strong>grande plage du Sillon</strong> qui s’étire.</p>"+
                                    "<h1>Encore plus forts !</h1>"+
                                        "<p>Accessibles à pied, à marée basse, le fort du Petit Bé et le fort national occupent des situations exceptionnelles. De ces îlots, une vue inoubliable s’étend sur 360°. Sur le Grand Bé, vous pourrez rendre une visite posthume au célèbre écrivain malouin <strong>Chateaubriand</strong>.</p>"+
                                "</article>"+

                                "<aside id=\"Portefeuille_actions\">"+
                                    "<h1>Portefeuille actions</h1>"+
                                        "<ul>"+
                                            "<li>Renault - 29 euros - +5%</li>"+
                                            "<li>Total   - 32 euros - -3%</li>"+
                                        "</ul>"+

                                "</aside>"+
                            "</section>"+

                            "<footer id=\"footer\">"+
                                "<div class=\"bloc_footer\">ELEMENT 1</div>"+
                                "<div class=\"bloc_footer\">ELEMENT 2</div>"+
                                "<div class=\"bloc_footer\">ELEMENT 3</div>"+
                            "</footer>"+

                        "</body>"+

                    "</html>";

        resp.getWriter().println(pageHTML);
    }
}
