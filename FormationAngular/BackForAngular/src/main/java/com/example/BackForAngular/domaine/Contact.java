package com.example.BackForAngular.domaine;

import java.time.LocalDateTime;

public class Contact {

    private String nom;
    private String prenom;
    private String expression;
    private String telephone;
    private String prime;
    private String bateau;
    private Boolean favori;   // true = contact favori
    private LocalDateTime dateAjout;

    public Contact(String nom, String prenom, String expression, String telephone, String prime, String bateau, Boolean favori) {
        this.nom = nom;
        this.prenom = prenom;
        this.expression = expression;
        this.telephone = telephone;
        this.prime = prime;
        this.bateau = bateau;
        this.favori = favori;
        this.dateAjout = LocalDateTime.now();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    public String getBateau() {
        return bateau;
    }

    public void setBateau(String bateau) {
        this.bateau = bateau;
    }

    public Boolean getFavori() {
        return favori;
    }

    public void setFavori(Boolean favori) {
        this.favori = favori;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
}
