package com.example.portailci.exposition.utilisateur;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Utilisateur REFOG", description = "Utilisateur importé depuis le REFOG afin d'assurer la complétion automatique du formulaire de création d'un nouvel utilisateur")
// Annotation Spring permettant la validation du model
@Validated
public class UtilisateurRefogDTO {

    private String uid;
    private String nom;
    private String prenom;
    private String uoAffectation;
    private String siteExercice;
    private String fonction;

    public UtilisateurRefogDTO() {
    }

    public UtilisateurRefogDTO(String uid, String nom, String prenom, String uoAffectation, String siteExercice, String fonction) {
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.uoAffectation = uoAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getUoAffectation() {
        return uoAffectation;
    }

    public void setUoAffectation(String uoAffectation) {
        this.uoAffectation = uoAffectation;
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
}
