package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private Panneau pan = new Panneau();
    private Bouton bouton1 = new Bouton(" GO ");
    private Bouton bouton2 = new Bouton("STOP");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Choix de la forme");
    private JComboBox<String> comboBox = new JComboBox<>();
    private JCheckBox checkBoxTransformation = new JCheckBox("transformation");
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;

    public Fenetre(){
        this.setTitle("Animation");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);

        //Nous ajoutons notre fenêtre à la liste des auditeurs de notre bouton
        bouton1.addActionListener(new Bouton1Listener());
        bouton1.setEnabled(false);
        bouton2.addActionListener(new Bouton2Listener());

        // nous ajoutons les 2 boutons dans un même container
        JPanel containerBouton = new JPanel();
        containerBouton.add(bouton1);
        containerBouton.add(bouton2);
        container.add(containerBouton, BorderLayout.SOUTH);

        //Définition d'une police d'écriture
        Font police = new Font("Tahoma", Font.BOLD, 16);
        //On l'applique au JLabel
        label.setFont(police);
        //Changement de la couleur du texte
        label.setForeground(Color.blue);
        //On modifie l'alignement du texte grâce aux attributs statiques
        //de la classe JLabel
        label.setHorizontalAlignment(JLabel.CENTER);
        comboBox.addItem("ROND");
        comboBox.addItem("CARRE");
        comboBox.addItem("TRIANGLE");
        comboBox.addItem("ETOILE");
        // pour communiquer avec la combobox et la checkBox
        comboBox.addActionListener(new Fenetre.FormeListener());
        checkBoxTransformation.addActionListener(new TransformationListener());

        JPanel partieHaute = new JPanel();
        partieHaute.add(label);
        partieHaute.add(comboBox);
        partieHaute.add(checkBoxTransformation);
        container.add(partieHaute, BorderLayout.NORTH);

        this.setContentPane(container);
        this.setVisible(true);
        go();

    }

    private void setAnimated() {
        this.animated = true;
    }

    private void go(){

        //Les coordonnées de départ de notre rond
        x = pan.getPosX();
        y = pan.getPosY();

        //Dans cet exemple, j'utilise une boucle while
        //Vous verrez qu'elle fonctionne très bien
        while(this.animated) {
            //Si la coordonnée x est inférieure à 1, on avance
            if (x < 1) backX = false;
            //Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
            if (x > pan.getWidth() - 50) backX = true;
            //Idem pour l'axe y
            if (y < 1) backY = false;
            if (y > pan.getHeight() - 50) backY = true;

            //Si on avance, on incrémente la coordonnée
            if (!backX)
                pan.setPosX(++x);
                //Sinon, on décrémente
            else
                pan.setPosX(--x);
            //Idem pour l'axe Y
            if (!backY)
                pan.setPosY(++y);
            else
                pan.setPosY(--y);

            //On redessine notre Panneau
            pan.repaint();

            //Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //Classe écoutant notre premier bouton GO
    class Bouton1Listener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {

            // AFFICHER UNE BOITE DE DIALOGUE POUR CONFIRMER LE LANCEMENT DE L'ANIMATION
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous lancer l'animation ?",
                    "Lancement de l'animation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.OK_OPTION) {
                animated = true;
                t = new Thread(new PlayAnimation());
                t.start();
                bouton1.setEnabled(false);
                bouton2.setEnabled(true);
            }
        }
    }

    //Classe écoutant notre 2ème bouton STOP
    class Bouton2Listener implements ActionListener{

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0) {
            animated = false;
            bouton1.setEnabled(true);
            bouton2.setEnabled(false);
        }
    }

    class PlayAnimation implements Runnable{
        public void run() {
            go();
        }
    }

    class FormeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //La méthode retourne un Object puisque nous passons des Object dans une liste
            //Il faut donc utiliser la méthode toString() pour retourner un String (ou utiliser un cast)
            pan.setForme((String) comboBox.getSelectedItem());
        }
    }

    class TransformationListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
            System.out.println("coucou :" + checkBoxTransformation.isSelected());
            pan.setTransformation(checkBoxTransformation.isSelected());
        }
    }
}