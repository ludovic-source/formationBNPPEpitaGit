package com.cursan.HomeShop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bill {

    private Customer customers;
    private Map<Product, Integer> products = new HashMap<>();
    private Delivery delivery;

    public Bill(Customer customers, Delivery delivery) {
        this.customers = customers;
        this.delivery = delivery;
    }

    public Customer getCustomers() {
        return customers;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product, int quantity) {
            this.products.put(product, quantity);
    }

    public void generateBill() throws IOException {

        // formatage des lignes fixes de la facture
        String ligneEntete1 = "HomeShop compagnie";
        String ligneEntete2 = "1 Place Charles de Gaulle, 75008 Paris";
        String ligneEntete3 = "Facture à l'attention de :";
        String ligneTitreProduits = "Produits: ";
        String ligneEspace = "-----------------------------------------------------";

        ArrayList<String> lignesAEcrire = new ArrayList<>();
        Path path = Paths.get("C:/Users/stagiaire/IdeaProjects/HomeShop/src/com/cursan/HomeShop/facture.txt");

        try {
            boolean fichierExists = Files.exists(path);
            if (fichierExists != true) {
                System.out.println("le fichier n'existe pas");
                    Files.createFile(path);
            }
            else {
                System.out.println("le fichier existe déjà");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("fin de lecture du fichier");
        }

        lignesAEcrire.add(ligneEntete1);
        lignesAEcrire.add(ligneEntete2);
        lignesAEcrire.add("");
        lignesAEcrire.add(ligneEntete3);
        lignesAEcrire.add(this.customers.getFullname());
        lignesAEcrire.add(this.customers.getAdress());
        lignesAEcrire.add("");
        lignesAEcrire.add("Mode de livraison : " + this.delivery.getInfo() + " " + this.delivery.getPrice() + " €");
        lignesAEcrire.add("");
        lignesAEcrire.add(ligneTitreProduits);
        lignesAEcrire.add(ligneEspace);

        Map<Product, Integer> products = getProducts();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            lignesAEcrire.add(product.getName() + " - " + product.getPrice() + " - " + quantity + " unité(s)");
            lignesAEcrire.add(product.getDescription());
            lignesAEcrire.add(ligneEspace);
        }

        lignesAEcrire.add("Livraison : " + this.delivery.getPrice());
        lignesAEcrire.add(ligneEspace);
        lignesAEcrire.add("Total : " + getTotal());

        Files.write(path, lignesAEcrire, StandardOpenOption.APPEND);

    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

}







