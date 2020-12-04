package com.example.portailci.domain.thematique;

public enum ExtensionsImage {
    gif ("gif"),
    jpeg ("jpeg"),
    jpg ("jpg"),
    png ("png");

    private String extension;

    // Constructeur
    ExtensionsImage (String extension) {
        this.extension = extension;
    }

    public String toString() {
        return extension;
    }
}
