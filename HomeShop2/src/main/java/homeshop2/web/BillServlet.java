package homeshop2.web;

import homeshop2.Fridge;
import homeshop2.Product;
import homeshop2.Television;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {

    List<Product> products = new ArrayList<Product>();

    @Override
    public void init() throws ServletException {

        // C'est la méthode qui va être appelée par Jetty quand il va instancier un objet de type  HttpServlet.
        // Cette méthode est utilisée une fois au début. Elle permet d'initialiser le contexte.
        // Pour générer le formulaire, il faut la liste des produits (elle est affichée avant le formulaire)

        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599.00, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189.00, 130, false);
        products.add(cafe);
        products.add(tv);
        products.add(fridge);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");   // pour indiquer qu'on va utiliser du HTML pour notre réponse
        if (req.getQueryString() == null)   // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET
            displayForm(resp);              // pas de paramètres, on affiche le formulaire à remplir
        else
            displayBill(req, resp);         // pour afficher la facture correspondant au formulaire rempli
    }

    private void displayBill(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void displayForm(HttpServletResponse resp) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            resp.getWriter().println(
                    "<b>" + i + " - " + product.getName() + "</b> : " + product.getPrice() + "<br/>" +
                            product.getDescription() + "<br/><br/>");
        }
        String form = "<form action=\"bill\">" +
                "<b>nom complet :</b> <input name=\"fullname\"/><br/>" +
                "<b>adresse :</b> <input name=\"address\"/><br/><br/>" +
                "<b>livraison :</b> <br/>" +
                "à domicile : <input type=\"radio\" name=\"deliveryMode\" value=\"direct\"/><br/>" +
                "express : <input type=\"radio\" name=\"deliveryMode\" value=\"express\"/><br/>" +
                "point relais : <input type=\"radio\" name=\"deliveryMode\" value=\"relay\"/><br/>" +
                "à retirer : <input type=\"radio\" name=\"deliveryMode\" value=\"takeAway\"/><br/>" +
                "<b>Informations livraison</b> (relay et express) : <input name=\"deliveryInfo\"/><br/><br/>" +
                "<b>liste produits </b> (produit:quantité, un produit par ligne) : <br/>" +
                "<textarea name=\"products\"></textarea><br/>" +
                "<input type=\"submit\"/>" +
                "</form>";
        resp.getWriter().println(form);
    }

}
