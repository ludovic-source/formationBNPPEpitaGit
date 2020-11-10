package com.epita.tp1;

public class Voiture {
	
	private String couleur;
	private Moteur moteur;
	private int nbrePortes;
	
	public Voiture(String couleur, Moteur moteur, int nbrePortes) {
		this.couleur = couleur;
		this.moteur = moteur;
		this.nbrePortes = nbrePortes;
	}

	public void deplacer() {
		System.out.println("je me déplace");
	}
	
	public void arreter() {
		System.out.println("je m'arrête");
	}
	
	public void freiner() {
		System.out.println("je freine");
		
	}
	
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Moteur getMoteur() {
		return moteur;
	}

	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}

	public int getNbrePortes() {
		return nbrePortes;
	}

	public void setNbrePortes(int nbrePortes) {
		this.nbrePortes = nbrePortes;
	}
	
	

}
