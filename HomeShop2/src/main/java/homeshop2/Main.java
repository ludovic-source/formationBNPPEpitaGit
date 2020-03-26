package homeshop2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49", 599.00, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189.00, 130, false);
        Customer customer = new Customer("Juste Leblanc", "19 rue Germain Pilon, Paris");
        Delivery lowCostRelayDelivery = new RelayDelivery(27);

        Bill facture = new Bill(customer, lowCostRelayDelivery);

        facture.addProduct(cafe, 2);
        facture.addProduct(tv, 1);
        facture.addProduct(fridge, 1);

        facture.generateBill();

    }

}
