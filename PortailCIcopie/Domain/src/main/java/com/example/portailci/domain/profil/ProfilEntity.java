package com.example.portailci.domain.profil;

import com.example.portailci.domain.droit.DroitEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PROFIL")
public class ProfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    private Set<DroitEntity> droits;

    public ProfilEntity(){}

    public ProfilEntity(Long id) {
        this.id = id;
    }

    public ProfilEntity(Long id, String nom, String description, Set<DroitEntity> droits) {
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

    public Set<DroitEntity> getDroits() {
        return droits;
    }

    public void setDroits(Set<DroitEntity> droits) {
        this.droits = droits;
    }
}
