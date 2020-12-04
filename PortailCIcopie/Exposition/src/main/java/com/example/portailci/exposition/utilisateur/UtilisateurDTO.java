package com.example.portailci.exposition.utilisateur;

import com.example.portailci.exposition.profil.ProfilDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Utilisateur", description = "Utilisateur data")
// Annotation Spring permettant la validation du model
@Validated
public class UtilisateurDTO {

    @ApiModelProperty(notes = "Id autogénéré")
    private Long id;
    @ApiModelProperty(notes = "UID de l'utilisateur")
    private String uid;
    @ApiModelProperty(notes = "Nom de l'utilisateur")
    private String nom;
    @ApiModelProperty(notes = "Prénom de l'utilisateur")
    private String prenom;
//    @ApiModelProperty(notes = "Mot de passe de l'utilisateur")
//    private String motDePasse;
    @ApiModelProperty(notes = "Unité Opérationnelle à laquelle est affecté l'utilisateur")
    private String uoAffectation;
    @ApiModelProperty(notes = "Site d'exercice de l'utilisateur")
    private String siteExercice;
    @ApiModelProperty(notes = "Fonction de l'utilisateur")
    private String fonction;
    @ApiModelProperty(notes = "Profil de l'utilisateur")
    private ProfilDTO profil;

    public UtilisateurDTO() {
    }

//    public UtilisateurDTO(Long id, String uid, String nom, String prenom, String motDePasse, String uoAffectation, String siteExercice, String fonction, ProfilDTO profil) {
//        this.id = id;
//        this.uid = uid;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.motDePasse = motDePasse;
//        this.uoAffectation = uoAffectation;
//        this.siteExercice = siteExercice;
//        this.fonction = fonction;
//        this.profil = profil;
//    }


    public UtilisateurDTO(Long id, String uid, String nom, String prenom, String uoAffectation, String siteExercice, String fonction, ProfilDTO profil) {
        this.id = id;
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.uoAffectation = uoAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
        this.profil = profil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public String getMotDePasse() {
//        return motDePasse;
//    }
//
//    public void setMotDePasse(String motDePasse) {
//        this.motDePasse = motDePasse;
//    }

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

    public ProfilDTO getProfil() {
        return profil;
    }

    public void setProfil(ProfilDTO profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return "UtilisateurDTO = {" +
                "id : '" + id + '\'' +
                ", UID : '" + uid + '\'' +
                ", Nom : '" + nom + '\'' +
                ", Prénom : '" + prenom + '\'' +
                ", Profil : '" + profil.getNom() + '\'' +
//                ", MotDePasse : '"  + motDePasse + '\'' +
                ", UO Affectation : '" + uoAffectation + '\'' +
                ", Site d'exercice : '" + siteExercice + '\'' +
                ", Fonction : '" + fonction + '\'' +
                '}';
    }
}
