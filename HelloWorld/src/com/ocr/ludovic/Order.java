package com.ocr.ludovic;

import java.util.Scanner;

public class Order {

    private final String[] menu = {"poulet", "boeuf", "végétarien"};
    private int menuChoisi;

    /**
     * Display all available menus in the restaurant.
     */
    public void displayAvailableMenus() {

        System.out.println("Choix menu");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i+1 + " - " + menu[i]);
        }
        System.out.println("Que souhaitez-vous comme menu ?");

    }

    /**
     * Display a selected menu.
     * @param nbMenu The selected menu.
     */
    public void displaySelectedMenu(int nbMenu) {
        menuChoisi = nbMenu;
        System.out.println("Vous avez choisi le menu " + menu[menuChoisi-1]);
    }

    public void runMenu() {
        /* Order commande = new Order(); */ // inutile si on utilise le pré fix this. à la place du nom de l'objet

        this.displayAvailableMenus();

        Scanner sc = new Scanner(System.in);
        int menuChoisi = sc.nextInt();

        this.displaySelectedMenu(menuChoisi);

    }

}
