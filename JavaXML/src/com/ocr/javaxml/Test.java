package com.ocr.javaxml;

public class Test {

    private String nom="coucou";

    public Test(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Test{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
