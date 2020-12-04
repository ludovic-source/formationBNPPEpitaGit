package com.example.portailci.exposition.droit;

import com.example.portailci.domain.droit.DroitEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Droit", description = "Droit permettant la gestion des accès/autorisations")
// Annotation Spring permettant la validation du model
@Validated
public class DroitDTO {

    @ApiModelProperty(notes = "Id du droit")
    private Long id;

    @ApiModelProperty(notes = "Nom du droit")
    private String nom;

    @ApiModelProperty(notes = "Description du droit")
    private String description;

    public DroitDTO() {}

    public DroitDTO(Long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
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
}
