package com.example.portailci.exposition.thematique;

import org.springframework.web.multipart.MultipartFile;

public class ThematiqueDTO {
    private Long id;
    private String nom;
    private String description;
    private Long idParent;
    private MultipartFile file;

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
