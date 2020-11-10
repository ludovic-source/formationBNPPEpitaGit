package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArdoiseMagique extends JFrame {

    // DECLARATION DES VARIABLES POUR LE MENU
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFichier = new JMenu("Fichier");
    private JMenu menuEdition = new JMenu("Edition");
    private JMenu menuEdition1 = new JMenu("Forme du pointeur");
    private JMenu menuEdition2 = new JMenu("Couleur du pointeur");

    private JMenuItem fichierItem1 = new JMenuItem("Effacer");
    private JMenuItem fichierItem2 = new JMenuItem("Quitter");
    private JMenuItem editionFormeItem1 = new JMenuItem("Rond");
    private JMenuItem editionFormeItem2 = new JMenuItem("Carré");
    private JMenuItem editionCouleurItem1 = new JMenuItem("Rouge");
    private JMenuItem editionCouleurItem2 = new JMenuItem("Vert");
    private JMenuItem editionCouleurItem3 = new JMenuItem("Bleu");

    // DECLARATION DES VARIABLES POUR LA BARRE D'OUTILS
    private JToolBar toolBar = new JToolBar();
    //Les boutons de la barre d'outils
    private JButton carreNoir = new JButton(new ImageIcon("carreNoir.png"));
    private JButton rondNoir = new JButton(new ImageIcon("rondNoir.png"));
    private JButton carreRouge = new JButton(new ImageIcon("carreRouge.png"));
    private JButton carreBleu = new JButton(new ImageIcon("carreBleu.png"));
    private JButton carreVert = new JButton(new ImageIcon("carreVert.png"));
    private Color fondBouton = Color.white;
    private FormeListener formeListener = new FormeListener();
    private CouleurListener couleurListener = new CouleurListener();

    // ZONE DE DESSIN
    private PanneauArdoise dessin = new PanneauArdoise();

    public ArdoiseMagique () {

        this.setTitle("Ardoise magique");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // on initialise la barre de menu
        initBarMenu();

        // on initialise la barre d'outils
        initToolBar();

        //On positionne notre zone de dessin
        this.getContentPane().add(dessin, BorderLayout.CENTER);

        this.setVisible(true);

    }

    private void initToolBar() {

        this.carreNoir.addActionListener(formeListener);
        this.rondNoir.addActionListener((formeListener));
        this.carreNoir.setBackground(fondBouton);
        this.rondNoir.setBackground(fondBouton);
        this.toolBar.add(carreNoir);
        this.toolBar.add(rondNoir);
        this.toolBar.addSeparator();

        this.carreRouge.addActionListener(couleurListener);
        this.carreBleu.addActionListener(couleurListener);
        this.carreVert.addActionListener(couleurListener);
        this.carreRouge.setBackground(fondBouton);
        this.carreBleu.setBackground(fondBouton);
        this.carreVert.setBackground(fondBouton);
        this.toolBar.add(carreRouge);
        this.toolBar.add(carreBleu);
        this.toolBar.add(carreVert);


    }

    private void initBarMenu() {

        Font police = new Font("Tahoma", Font.BOLD, 16);

        fichierItem1.setFont(police);
        this.menuFichier.add(fichierItem1);
        fichierItem2.setFont(police);
        this.menuFichier.add(fichierItem2);
        editionFormeItem1.setFont(police);
        this.menuEdition1.add(editionFormeItem1);
        editionFormeItem2.setFont(police);
        this.menuEdition1.add(editionFormeItem2);
        editionCouleurItem1.setFont(police);
        this.menuEdition2.add(editionCouleurItem1);
        editionCouleurItem2.setFont(police);
        this.menuEdition2.add(editionCouleurItem2);
        editionCouleurItem3.setFont(police);
        this.menuEdition2.add(editionCouleurItem3);
        menuEdition1.setFont(police);
        this.menuEdition.add(menuEdition1);
        menuEdition2.setFont(police);
        this.menuEdition.add(menuEdition2);
        menuFichier.setFont(police);
        this.menuBar.add(menuFichier);
        menuEdition.setFont(police);
        this.menuBar.add(menuEdition);
        menuBar.setFont(police);
        this.setJMenuBar(menuBar);

        // ajout des ecouteurs "effacer"
        fichierItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.erase();
            }
        });

        // ajout des ecouteurs "quitter"
        fichierItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        // ajout des ecouteurs "forme"
        editionFormeItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.setPointerType("ROND");
            }
        });
        editionFormeItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.setPointerType("CARRE");
            }
        });

        // ajout des ecouteurs "couleur"
        editionCouleurItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.setPointerColor(Color.red);
            }
        });
        editionCouleurItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.setPointerColor(Color.green);
            }
        });
        editionCouleurItem3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                dessin.setPointerColor(Color.blue);
            }
        });

        this.add(toolBar, BorderLayout.NORTH);

    }

    class FormeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            System.out.println("la forme choisie est : " + e.getSource());
            if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")){
                if(e.getSource()== carreNoir) {
                    dessin.setPointerType("CARRE");
                }
                else {
                    dessin.setPointerType("ROND");
                }
            }
            else{
                if(e.getSource()== carreNoir) {
                    dessin.setPointerType("CARRE");
                }
                else {
                    dessin.setPointerType("ROND");
                }
            }
        }
    }

    class CouleurListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            System.out.println("la couleur choisie est : " + e.getSource());
            if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {
                System.out.println("OK !");
                if (e.getSource() == carreVert) {
                    dessin.setPointerColor(Color.green);
                }
                else {
                    if (e.getSource() == carreBleu) {
                        dessin.setPointerColor(Color.blue);
                    }
                    else {
                        dessin.setPointerColor(Color.red);
                    }
                }
            }
            else {
                if(e.getSource()== carreVert) {
                    dessin.setPointerColor(Color.green);
                }
                else {
                    if (e.getSource() == carreBleu) {
                        dessin.setPointerColor(Color.blue);
                    }
                    else {
                        dessin.setPointerColor(Color.red);
                    }
                }
            }
        }
    }

}
