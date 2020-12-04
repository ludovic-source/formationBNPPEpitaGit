package com.example.portailci.infrastructure.utilisateur;

public class UtilisateurRefogVO {

    private String UID;
    private String nom;
    private String prenom;
    private String UOAffectation;
    private String siteExercice;
    private String fonction;

    public UtilisateurRefogVO() {
    }

    public UtilisateurRefogVO(String UID, String nom, String prenom, String UOAffectation, String siteExercice, String fonction) {
        this.UID = UID;
        this.nom = nom;
        this.prenom = prenom;
        this.UOAffectation = UOAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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

    public String getUOAffectation() {
        return UOAffectation;
    }

    public void setUOAffectation(String UOAffectation) {
        this.UOAffectation = UOAffectation;
    }

    public String getSiteExercice() {
        return siteExercice;
    }

    public void setSiteExercice(String siteExercice) {
        this.siteExercice = siteExercice;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "UtilisateurRefogVO = {" +
                " UID : '" + UID + '\'' +
                ", Nom : '" + nom + '\'' +
                ", Prénom : '" + prenom + '\'' +
                ", UO Affectation : '" + UOAffectation + '\'' +
                ", Site d'exercice : '" + siteExercice + '\'' +
                ", Fonction : '" + fonction + '\'' +
                '}';
    }
}
