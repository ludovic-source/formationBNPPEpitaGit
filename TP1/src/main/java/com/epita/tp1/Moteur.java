package com.epita.tp1;

public class Moteur {
	
	private int puissance;
	private String type;
		
	public Moteur() {
	}

	public Moteur(int puissance, String type) {
		this.puissance = puissance;
		this.type = type;
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
