package com.cursan.HomeShop;

public class Fridge extends Product {

    private int liter;
    private boolean freezer;

    public Fridge(String name, String description, Double price, int liter, boolean freezer) {
        super(name, description, price);
        this.liter = liter;
        this.freezer = freezer;
    }

    public int getLiter() {
        return liter;
    }

    public boolean isFreezer() {
        return freezer;
    }
}
