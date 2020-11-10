package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;

public class FenetreAvecOnglet extends JFrame{

    private JTabbedPane onglet;

    public FenetreAvecOnglet(){
        this.setLocationRelativeTo(null);
        this.setTitle("Gérer vos conteneurs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);

        //Création de plusieurs Panneau
        PanneauOnglet[] tPan = {   new PanneauOnglet(Color.RED), new PanneauOnglet(Color.GREEN), new PanneauOnglet(Color.BLUE)};

        //Création de notre conteneur d'onglets
        onglet = new JTabbedPane();
        int i = 0;
        for(PanneauOnglet pan : tPan){
            //Méthode d'ajout d'onglet
            onglet.add("Onglet n° "+(++i), pan);
            //Vous pouvez aussi utiliser la méthode addTab
            //onglet.addTab("Onglet n° "+(++i), pan);

        }
        //On passe ensuite les onglets au content pane
        this.getContentPane().add(onglet);
        this.setVisible(true);
    }

}
