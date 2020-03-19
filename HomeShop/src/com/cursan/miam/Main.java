package com.cursan.miam;

public class Main {

    public static void main(String[] args) {

        Four petitfour = new Four();
        petitfour.puissance = 180;
        petitfour.capacite = 30;

        Four grandfour = new Four();
        grandfour.puissance = 260;
        grandfour.capacite = 55;

        petitfour.cuire();
        grandfour.cuire();

    }

}
