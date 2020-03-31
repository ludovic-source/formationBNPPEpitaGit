package homeshop2.web;

import homeshop2.*;
import homeshop2.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillServlet extends HttpServlet {

    List<Product> products = new ArrayList<Product>();

    @Override
    public void init() throws ServletException {

        // C'est la méthode qui va être appelée par Jetty quand il va instancier un objet de type  HttpServlet.
        // Cette méthode est utilisée une fois au début. Elle permet d'initialiser le contexte.
        // Pour générer le formulaire, il faut la liste des produits (elle est affichée avant le formulaire)

        products = new ProductDAO().getAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       // pour indiquer qu'on va utiliser du HTML pour notre réponse
        if (req.getQueryString() == null) {     // req.getQueryString() permet de récupérer les paramètres contenus dans l'URL d'appel GET
            displayForm(resp);                  // pas de paramètres, on affiche le formulaire à remplir
        }
        else {
            displayBill(req, resp);         // pour afficher la facture correspondant au formulaire rempli
        }
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

    private void displayBill(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, String> params = splitParameters(req.getQueryString());

        Customer customer = new Customer(params.get("fullname"), params.get("address"));

        Delivery delivery = null;

        switch (params.get("deliveryMode")) {
            case "direct" :
                delivery = new DirectDelivery();
                break;
            case "express" :
                delivery = new ExpressDelivery(params.get("deliveryInfo"));
                break;
            case "relay" :
                delivery = new RelayDelivery(Integer.parseInt(params.get("deliveryInfo")));
                break;
            case "takeAway" :
                delivery = new TakeAwayDelivery();
                break;
        }

        Bill bill = new Bill(customer, delivery);

        String produitsCommandes = params.get("products");
        String[] tableauProduitsCommandes = produitsCommandes.split("%0D%0A");
        Map<Integer, Integer> mapProduitsCommandes = new HashMap<>();
        int numeroProduit;
        int quantity;
        for (String ligneCommande : tableauProduitsCommandes) {
            String[] tableauProduitQuantite = ligneCommande.split("%3A");
            numeroProduit = Integer.parseInt(tableauProduitQuantite[0]);
            quantity = Integer.parseInt(tableauProduitQuantite[1]);
            bill.addProduct(products.get(numeroProduit), quantity);
        }

        // générer la facture
        // bill.generateBill();
        ArrayList<String> lignesAEcrire = bill.generateBillDematerialisee();
        StringBuilder facture = new StringBuilder();
        for (String chaqueLigne : lignesAEcrire) {
            facture.append("<br/>").append(chaqueLigne);
        }
        resp.getWriter().println(facture);

    }

    public Map<String, String> splitParameters(String queryString) {

        int compteurParametre = 0;

        String[] tableauParametre = queryString.split("[?&=]");
        Map<String, String> mapParametre = new HashMap<>();

        for (String parametre : tableauParametre) {

            if (parametre.equals("fullname") || parametre.equals("address") || parametre.equals("deliveryMode") || parametre.equals("deliveryInfo") || parametre.equals("products")) {
                mapParametre.put(parametre, tableauParametre[compteurParametre+1]);
            }
            compteurParametre += 1;

        }


        return mapParametre;

       // http://localhost:8080/bill?fullname=Juste+Leblanc&address=19+rue+Germain+Pilon&deliveryMode=express
       //         &deliveryInfo=Paris&products=1%3A2%0D%0A2%3A1
        // 0 http://localhost:8080/bill
        // 1 fullname
        // 2 Juste+Leblanc
        // 3 address
        // 4 19+rue+Germain+Pilon
        // 5 deliveryMode
        // 6 express
       //  7 deliveryInfo=
        // 8 Paris
        // 9 products
        // 10 1%3A2%0D%0A2%3A1   %0D%0A = retour à ligne et %3A = :   ==> 1:2 + 2:1

    }

}