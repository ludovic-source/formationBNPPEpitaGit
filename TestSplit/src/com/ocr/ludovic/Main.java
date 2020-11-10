package com.ocr.ludovic;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String produitsCommandes = "1%3A2%0D%0A2%3A1";
        String[] tableauProduitsCommandes = produitsCommandes.split("%0D%0A");

        int numeroProduit;
        int quantity;
        for (String ligneCommande : tableauProduitsCommandes) {
            String[] tableauProduitQuantite = ligneCommande.split("%3A");

            numeroProduit = Integer.parseInt(tableauProduitQuantite[0]);
            quantity = Integer.parseInt(tableauProduitQuantite[1]);
            System.out.println("numero produit = " + numeroProduit);
            System.out.println("quantité = " + quantity);

        }



    }
}
