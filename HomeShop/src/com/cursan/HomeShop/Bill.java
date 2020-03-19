package com.cursan.HomeShop;

import java.util.HashMap;
import java.util.Map;

public class Bill {

    private Customer customers;
    private Map<String, Integer> products = new HashMap<>();

    public Bill(Customer customers, Map<String, Integer> products) {
        this.customers = customers;
        this.products = products;
    }

    public Customer getCustomers() {
        return customers;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product produit, Integer quantity) {


    }
}
