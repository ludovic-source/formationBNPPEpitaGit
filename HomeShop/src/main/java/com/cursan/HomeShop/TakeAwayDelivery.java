package com.cursan.HomeShop;

public class TakeAwayDelivery implements Delivery {

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getInfo() {
        return "Récupération de la commande en magasin";
    }
}
