package com.epita.tp1;

public class App {

	public static void main(String[] args) {
		
		Moteur moteur = new Moteur();
		moteur.setPuissance(7);
		
		Voiture twingo = new Voiture("blue", moteur, 5);
		twingo.deplacer();
		
		twingo.setCouleur("red");
		twingo.getCouleur();

		// pour récupérer le moteur de la twingo et le conserver
		// attention à l'adresse mémoire, on ne crée pas un nouveau moteur
		Moteur moteurTwingo = twingo.getMoteur();
		System.out.println("moteur : " + moteur);
		System.out.println("moteurTwingo : " + moteurTwingo);
		System.out.println("la puissance du moteur de la twingo est : " + moteurTwingo.getPuissance());

	}

}
