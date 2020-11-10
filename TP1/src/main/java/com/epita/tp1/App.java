package com.epita.tp1;

public class App {

	public static void main(String[] args) {
		
		Moteur moteur = new Moteur();
		moteur.setPuissance(7);
		
		Voiture twingo = new Voiture("blue", moteur, 5);
		twingo.deplacer();
		
		twingo.setCouleur("red");
		twingo.getCouleur();

		// pour r�cup�rer le moteur de la twingo et le conserver
		// attention � l'adresse m�moire, on ne cr�e pas un nouveau moteur
		Moteur moteurTwingo = twingo.getMoteur();
		System.out.println("moteur : " + moteur);
		System.out.println("moteurTwingo : " + moteurTwingo);
		System.out.println("la puissance du moteur de la twingo est : " + moteurTwingo.getPuissance());

	}

}
