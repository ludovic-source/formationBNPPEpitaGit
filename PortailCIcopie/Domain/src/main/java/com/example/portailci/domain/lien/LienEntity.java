package com.example.portailci.domain.lien;

import com.example.portailci.domain.thematique.ThematiqueEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Lien", description = "Lien")
// Annotation Spring permettant la validation du model
@Validated
@Entity
@Table(name = "liens")
public class LienEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id du lien")
    Integer id;

    @NotNull
    @ApiModelProperty(notes = "URL du lien")
    String url;

    @NotNull
    @ApiModelProperty(notes = "Nom du lien")
    String nom;

    @NotNull
    @ApiModelProperty(notes = "Description du lien")
    String description;

    @NotNull
    @ApiModelProperty(notes = "Statut du lien (Valeurs: 'publié restreint' (Publication du lien, mais non visible pour les utilisateurs standards), 'publié' (Visible par tous les utilisateurs), 'dépublié' (Non visible)")
    String statut;          // valeurs possibles : "publié restreint" / "publié" / "dépublié"

    @NotNull
    @ApiModelProperty(notes = "Type d'ouverture du lien (True = Ouverture dans un nouvel onglet, False = Ouverture dans une nouvelle fenêtre")
    Boolean mode_affichage; // true = nouvel onglet ; false = nouvelle fenêtre

    @NotNull
    @ApiModelProperty(notes = "Date à laquelle le lien a été mis en publication restreinte")
    LocalDateTime date_publication_restreinte;

    @ApiModelProperty(notes = "Date à laquelle le lien a été mis en publication complète")
    LocalDateTime date_publication;

    @ApiModelProperty(notes = "Date à laquelle le lien a été dépublié")
    LocalDateTime date_depublication;

    @ManyToOne
    @ApiModelProperty(notes = "Thématique à laquelle est rattaché le lien")
    ThematiqueEntity thematique;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Boolean getMode_affichage() {
        return mode_affichage;
    }

    public void setMode_affichage(Boolean mode_affichage) {
        this.mode_affichage = mode_affichage;
    }

    public LocalDateTime getDate_publication_restreinte() {
        return date_publication_restreinte;
    }

    public void setDate_publication_restreinte(LocalDateTime date_publication_restreinte) {
        this.date_publication_restreinte = date_publication_restreinte;
    }

    public LocalDateTime getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(LocalDateTime date_publication) {
        this.date_publication = date_publication;
    }

    public LocalDateTime getDate_depublication() {
        return date_depublication;
    }

    public void setDate_depublication(LocalDateTime date_depublication) {
        this.date_depublication = date_depublication;
    }

    public ThematiqueEntity getThematique() {
        return thematique;
    }

    public void setThematique(ThematiqueEntity thematique) {
        this.thematique = thematique;
    }
}
