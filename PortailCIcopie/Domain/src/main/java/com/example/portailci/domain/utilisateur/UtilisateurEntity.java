package com.example.portailci.domain.utilisateur;

import com.example.portailci.domain.profil.ProfilEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "UTILISATEUR")
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Contrainte de longueur a exactement 6 caracteres pour respecter le format UID
    // @Size(min = 6,max = 6)
    @NotNull
    @Column(unique = true)
    private String uid;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    //@NotNull
    private String motDePasse;

    //Reference UO sur 8 caracteres (alphanumerique)
    @NotNull
    private String uoAffectation;

    @NotNull
    private String siteExercice;

    @NotNull
    private String fonction;

    @ManyToOne()
    @JoinColumn(name = "PROFIL_ID")
    //@NotNull
    private ProfilEntity profil;


    /**
     * Constructeur par defaut necessaire a Hibernate
     */
    public UtilisateurEntity() {

    }

    public UtilisateurEntity(@NotNull String uid, @NotNull String nom, @NotNull String prenom, String motDePasse, @NotNull String uoAffectation, @NotNull String siteExercice, @NotNull String fonction, ProfilEntity profil) {
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.uoAffectation = uoAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
        this.profil = profil;
    }

    public UtilisateurEntity(long id, @NotNull String uid, @NotNull String nom, @NotNull String prenom, String motDePasse, @NotNull String uoAffectation, @NotNull String siteExercice, @NotNull String fonction, ProfilEntity profil) {
        this.id = id;
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.uoAffectation = uoAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
        this.profil = profil;
    }

    public UtilisateurEntity(@NotNull String uid, @NotNull String nom, @NotNull String prenom, @NotNull String uoAffectation, @NotNull String siteExercice, @NotNull String fonction) {
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.uoAffectation = uoAffectation;
        this.siteExercice = siteExercice;
        this.fonction = fonction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public ProfilEntity getProfil() {
        return profil;
    }

    public void setProfil(ProfilEntity profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("UtilisateurEntity = {");
        if(this.id != 0L) builder.append("id : '" + String.valueOf(id) + '\'');
        if(this.uid != null) builder.append(" UID : '" + uid + '\'');
        if(this.nom != null) builder.append(" Nom : '" + nom + '\'');
        if(this.prenom != null) builder.append(" Prénom : '" + prenom + '\'');
        if(this.profil != null) builder.append(" Profil : '" + profil.getNom() + '\'');
        if(this.motDePasse != null) builder.append(" Mot de Passe : '" + motDePasse + '\'');
        if(this.uoAffectation != null) builder.append(" UO Affectation : '" + uoAffectation + '\'');
        if(this.siteExercice != null) builder.append(" Site d'exercice : '" + siteExercice + '\'');
        if(this.fonction != null) builder.append(" Fonction : '" + fonction + '\'');

        builder.append('}');
        return builder.toString();

    }
}
