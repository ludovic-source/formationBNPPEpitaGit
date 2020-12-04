package com.example.portailci.domain.thematique;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Thématique")
// Annotation Spring permettant la validation du model
@Validated
@Entity
@Table(name = "THEMATIQUE")
public class ThematiqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id autogénéré de la ThématiqueEntity")
    private Long id;

    @ApiModelProperty(notes = "Nom de la thématique")
    private String nom;

    @ApiModelProperty(notes = "Description de la thématique")
    private String description;

    @ApiModelProperty(notes = "Niveau de la thématique dans l'arborescence")
    private int niveau;

    @ApiModelProperty(notes = "Id de la thématique à laquelle la thématique est rattachée")
    private Long idParent;

    @ApiModelProperty(notes = "Chemin de l'image de la thématique")
    private String imagePath;

    // Constructeur par défaut
    public ThematiqueEntity(){}

    public ThematiqueEntity(Long id, String nom, String description, int niveau, Long idParent, String imagePath) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
        this.idParent = idParent;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
