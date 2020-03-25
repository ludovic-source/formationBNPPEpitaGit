package com.ocr.ludovic;

import java.util.Scanner;

public class Order {

    public final String[] menu = {"poulet", "boeuf", "végétarien"};
    private int menuChoisi;
    public final String[] side = {"légumes frais", "frites", "riz", "tout"};
    public final String[] boisson = {"eau plate", "eau gazeuse", "soda"};

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
        /* Order commande = new Order(); */ // inutile si on utilise le pré fixe this. à la place du nom de l'objet

        this.displayAvailableMenus();

        // choix du menu
        Scanner sc = new Scanner(System.in);
        int menuChoisi = sc.nextInt();
        this.displaySelectedMenu(menuChoisi);

        // choix de l'accompagnement
        this.displayAvailableSide();
        this.displaySelectedSide(sc.nextInt());

        // choix de la boisson
        if (menuChoisi != 2) {
            this.displayAvailableBoisson();
            this.displaySelectedBoisson(sc.nextInt());
        }

    }

    public void displaySelectedBoisson(int choixBoisson) {

        System.out.println("Vous avez choisi comme accompagnement : " + boisson[choixBoisson-1]);

    }

    public void displayAvailableBoisson() {
        System.out.println("");
        System.out.println("Choix boisson");
        for (int i = 0; i < boisson.length; i++) {
            System.out.println(i+1 + " - " + boisson[i]);
        }
        System.out.println("Que souhaitez-vous comme boisson ?");

    }

    public void displayAvailableSide() {
        System.out.println("");
        System.out.println("Choix accompagnement");
        for (int i = 0; i < side.length; i++) {
            System.out.println(i+1 + " - " + side[i]);
        }
        System.out.println("Que souhaitez-vous comme accompagnement ?");

    }

    public void displaySelectedSide(int numberSide) {

        System.out.println("Vous avez choisi comme accompagnement : " + side[numberSide-1]);

    }

}
