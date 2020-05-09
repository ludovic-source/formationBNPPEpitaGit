package com.ludovic.ocr.coursJava;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;


public class FenetreCalculatrice extends JFrame {

    private String texteCalculs = "0";
    private String dernierOperateur = "";

    private BoutonCalculatrice bouton0 = new BoutonCalculatrice("0");
    private BoutonCalculatrice bouton1 = new BoutonCalculatrice("1");
    private BoutonCalculatrice bouton2 = new BoutonCalculatrice("2");
    private BoutonCalculatrice bouton3 = new BoutonCalculatrice("3");
    private BoutonCalculatrice bouton4 = new BoutonCalculatrice("4");
    private BoutonCalculatrice bouton5 = new BoutonCalculatrice("5");
    private BoutonCalculatrice bouton6 = new BoutonCalculatrice("6");
    private BoutonCalculatrice bouton7 = new BoutonCalculatrice("7");
    private BoutonCalculatrice bouton8 = new BoutonCalculatrice("8");
    private BoutonCalculatrice bouton9 = new BoutonCalculatrice("9");
    private BoutonCalculatrice boutonEgal = new BoutonCalculatrice("=");
    private BoutonCalculatrice boutonPoint = new BoutonCalculatrice(".");
    private BoutonCalculatrice boutonC = new BoutonCalculatrice("C");
    private BoutonCalculatrice boutonAddition = new BoutonCalculatrice("+");
    private BoutonCalculatrice boutonSoustraction = new BoutonCalculatrice("-");
    private BoutonCalculatrice boutonMultiplication = new BoutonCalculatrice("*");
    private BoutonCalculatrice boutonDivision = new BoutonCalculatrice("/");

    private JPanel containerGlobal = new JPanel();
    private JPanel ecranCalculs = new JPanel();
    private JLabel labelCalculs = new JLabel(texteCalculs);


    public FenetreCalculatrice () {

        this.setTitle("Calculatrice");
        this.setSize(336, 364);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        initCalculatrice();






    }

    private void initCalculatrice() {

        containerGlobal.setBackground(Color.white);
        containerGlobal.setBorder(BorderFactory.createLineBorder(Color.black));
        containerGlobal.setPreferredSize(new Dimension(240, 260));
        containerGlobal.setLayout(new BorderLayout());

        // initier la partie haute : affichage calculs et résultats
        // ------Définition d'une police d'écriture
        Font police = new Font("Tahoma", Font.BOLD, 16);
        // ------On l'applique au JLabel
        labelCalculs.setFont(police);
        // ------Changement de la couleur du texte
        labelCalculs.setForeground(Color.blue);
        // ------On modifie l'alignement du texte grâce aux attributs statiques
        // ------de la classe JLabel
        labelCalculs.setPreferredSize(new Dimension(220, 20));
        labelCalculs.setHorizontalAlignment(JLabel.RIGHT);
        labelCalculs.setBorder(BorderFactory.createLineBorder(Color.black));
        ecranCalculs.setPreferredSize(new Dimension(220,30));
        ecranCalculs.add(labelCalculs);
        containerGlobal.add(ecranCalculs, BorderLayout.NORTH);

        // INITIER LES BOUTONS DE GAUCHE 0 à 9 et . et =
        JPanel paveNumerique = new JPanel();
        // ----- On définit le layout à utiliser sur le content pane
        GridLayout glGauche = new GridLayout(4, 3);
        paveNumerique.setLayout(glGauche);
        bouton1.setPreferredSize(new Dimension(50, 40));
        bouton1.addActionListener(new FenetreCalculatrice.Bouton1Listener());
        paveNumerique.add(bouton1);
        bouton2.setPreferredSize(new Dimension(50, 40));
        bouton2.addActionListener(new FenetreCalculatrice.Bouton2Listener());
        paveNumerique.add(bouton2);
        bouton3.setPreferredSize(new Dimension(50, 40));
        bouton3.addActionListener(new FenetreCalculatrice.Bouton3Listener());
        paveNumerique.add(bouton3);
        bouton4.setPreferredSize(new Dimension(50, 40));
        bouton4.addActionListener(new FenetreCalculatrice.Bouton4Listener());
        paveNumerique.add(bouton4);
        bouton5.setPreferredSize(new Dimension(50, 40));
        bouton5.addActionListener(new FenetreCalculatrice.Bouton5Listener());
        paveNumerique.add(bouton5);
        bouton6.setPreferredSize(new Dimension(50, 40));
        bouton6.addActionListener(new FenetreCalculatrice.Bouton6Listener());
        paveNumerique.add(bouton6);
        bouton7.setPreferredSize(new Dimension(50, 40));
        bouton7.addActionListener(new FenetreCalculatrice.Bouton7Listener());
        paveNumerique.add(bouton7);
        bouton8.setPreferredSize(new Dimension(50, 40));
        bouton8.addActionListener(new FenetreCalculatrice.Bouton8Listener());
        paveNumerique.add(bouton8);
        bouton9.setPreferredSize(new Dimension(50, 40));
        bouton9.addActionListener(new FenetreCalculatrice.Bouton9Listener());
        paveNumerique.add(bouton9);
        bouton0.setPreferredSize(new Dimension(50, 40));
        bouton0.addActionListener(new FenetreCalculatrice.Bouton0Listener());
        paveNumerique.add(bouton0);
        boutonPoint.setPreferredSize(new Dimension(50, 40));
        boutonPoint.addActionListener(new FenetreCalculatrice.BoutonPointListener());
        paveNumerique.add(boutonPoint);
        boutonEgal.setPreferredSize(new Dimension(50, 40));
        boutonEgal.addActionListener(new FenetreCalculatrice.BoutonEgalListener());
        paveNumerique.add(boutonEgal);
        containerGlobal.add(paveNumerique, BorderLayout.CENTER);

        // INITIER LES BOUTONS D'OPERATIONS
        JPanel paveOperations = new JPanel();
        // ----- On définit le layout à utiliser sur le content pane
        // ----- Trois lignes sur deux colonnes
        GridLayout glDroite = new GridLayout(5, 1);
        //glDroite.setHgap(15); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
        //glDroite.setVgap(15); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        paveOperations.setLayout(glDroite);
        boutonC.setPreferredSize(new Dimension(50, 40));
        boutonC.addActionListener(new FenetreCalculatrice.BoutonCListener());
        paveOperations.add(boutonC);
        boutonAddition.setPreferredSize(new Dimension(50, 31));
        boutonAddition.addActionListener(new FenetreCalculatrice.BoutonAdditionListener());
        paveOperations.add(boutonAddition);
        boutonSoustraction.setPreferredSize(new Dimension(50,31));
        boutonSoustraction.addActionListener(new FenetreCalculatrice.BoutonSoustractionListener());
        paveOperations.add(boutonSoustraction);
        boutonMultiplication.setPreferredSize(new Dimension(50, 31));
        boutonMultiplication.addActionListener(new FenetreCalculatrice.BoutonMultiplicationListener());
        paveOperations.add(boutonMultiplication);
        boutonDivision.setPreferredSize(new Dimension(50, 31));
        boutonDivision.addActionListener(new FenetreCalculatrice.BoutonDivisionListener());
        paveOperations.add(boutonDivision);
        containerGlobal.add(paveOperations, BorderLayout.EAST);

        this.setContentPane(containerGlobal);
        this.setVisible(true);

    }

    //Classe écoutant le bouton "1"
    class Bouton1Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "1";;
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "1";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "2"
    class Bouton2Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "2";;
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "2";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "3"
    class Bouton3Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "3";;
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "3";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "4"
    class Bouton4Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "4";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "4";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "5"
    class Bouton5Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "5";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "5";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "6"
    class Bouton6Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "6";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "6";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "7"
    class Bouton7Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "7";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "7";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "8"
    class Bouton8Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "8";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "8";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "9"
    class Bouton9Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "9";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "9";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "0"
    class Bouton0Listener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "0";
            }
            else {
                texteCalculs = texteCalculs + dernierOperateur + "0";
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "."
    class BoutonPointListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (texteCalculs.equals("0") && texteCalculs.length() == 1) {
                texteCalculs = "0";
            }
            else {
                texteCalculs = texteCalculs + "." ;
                dernierOperateur = "";
            }
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "="
    class BoutonEgalListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            //float operation = Float.parseFloat(String.valueOf(texteCalculs));
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String resultat = "";
            try {
                resultat = "" + engine.eval(texteCalculs);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            labelCalculs.setText(resultat);
            texteCalculs = "0";
        }
    }

    //Classe écoutant le bouton "C"
    class BoutonCListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            texteCalculs = "0" ;
            labelCalculs.setText(texteCalculs);
        }
    }

    //Classe écoutant le bouton "+"
    class BoutonAdditionListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            dernierOperateur = "+";
        }
    }

    //Classe écoutant le bouton "-"
    class BoutonSoustractionListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            dernierOperateur = "-";
        }
    }

    //Classe écoutant le bouton "*"
    class BoutonMultiplicationListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            dernierOperateur = "*";
        }
    }

    //Classe écoutant le bouton "/"
    class BoutonDivisionListener implements ActionListener {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            dernierOperateur = "/";
        }
    }

}
