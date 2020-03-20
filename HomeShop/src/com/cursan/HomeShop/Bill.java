package com.cursan.HomeShop;

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
}
