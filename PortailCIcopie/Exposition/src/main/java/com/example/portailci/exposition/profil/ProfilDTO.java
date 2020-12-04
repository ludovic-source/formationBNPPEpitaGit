package com.example.portailci.exposition.profil;

import com.example.portailci.exposition.droit.DroitDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

//Annotation Swagger permettant de personnaliser l'affichage de l'entité
@ApiModel(value = "Profil", description = "Profil utilisateur intégrant une collection de droits")
// Annotation Spring permettant la validation du model
@Validated
public class ProfilDTO {

    @ApiModelProperty(notes = "Id autogénéré")
    private Long id;
    @ApiModelProperty(notes = "Nom du profil")
    private String nom;
    @ApiModelProperty(notes = "Description du profil")
    private String description;
    @ApiModelProperty(notes = "Liste des droits rattachés au profil")
    private Set<DroitDTO> droits;

    public ProfilDTO() {}

    public ProfilDTO(String nom, String description, Set<DroitDTO> droits) {
        this.nom = nom;
        this.description = description;
        this.droits = droits;
    }

    public ProfilDTO(Long id, String nom, String description, Set<DroitDTO> droits) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.droits = droits;
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

    public Set<DroitDTO> getDroits() {
        return droits;
    }

    public void setDroits(Set<DroitDTO> droits) {
        this.droits = droits;
    }
}
