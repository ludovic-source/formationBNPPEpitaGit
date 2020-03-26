package com.ocr.ludovic;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    System.out.println("Bienvenue au resto OpenClassRooms !");

	    Order commande = new Order();

	    commande.runMenu();

	    OrderReader.lireFichierMenu();

    }
}
