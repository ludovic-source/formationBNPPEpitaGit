package com.ocr.ludovic;

import java.awt.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Order {

    public final String[] menu = {"poulet", "boeuf", "végétarien"};
    private int menuChoisi;
    public final String[] side = {"légumes frais", "frites", "riz", "tout"};
    public final String[] boisson = {"eau plate", "eau gazeuse", "soda"};

    public void runMenu() {
        /* Order commande = new Order(); */ // inutile si on utilise le pré fixe this. à la place du nom de l'objet

        int choixMenu;
        int choixAccompagnement;
        int choixBoisson;
        Scanner sc = new Scanner(System.in);

        System.out.println("Combien de menu souhaitez-vous ?");
        int nbreMenu;
        try {
            nbreMenu = sc.nextInt();

            for (int i = 1; i <= nbreMenu; i++) {
                choixMenu = Interaction.askSomething("menu", menu);
                choixAccompagnement = Interaction.askSomething("accompagnement", side);
                if (choixMenu == 1 || choixMenu == 3) {
                    choixBoisson = Interaction.askSomething("boisson", boisson);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("il faut saisir un nombre !!!, recommencez : ");
            // ex.printStackTrace();
            // nbreMenu = sc.nextInt();
        }

    }

    public void displayAvailableMenus() {

        System.out.println("Choix menu");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i+1 + " - " + menu[i]);
        }
        System.out.println("Que souhaitez-vous comme menu ?");

    }

    public void displaySelectedMenu(int nbMenu) {
        menuChoisi = nbMenu;
        System.out.println("Vous avez choisi le menu " + menu[menuChoisi-1]);
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
