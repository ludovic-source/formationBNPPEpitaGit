package com.ludovic.algorithmes;

public class MotCorrige {

    private String mot;
    private int distanceEditionLevenshtein;

    public MotCorrige(String mot, int distanceEditionLevenshtein) {
        this.mot = mot;
        this.distanceEditionLevenshtein = distanceEditionLevenshtein;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public int getDistanceEditionLevenshtein() {
        return distanceEditionLevenshtein;
    }

    public void setDistanceEditionLevenshtein(int distanceEditionLevenshtein) {
        this.distanceEditionLevenshtein = distanceEditionLevenshtein;
    }
}
