package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreFormulaire extends JFrame {

    private JPanel container = new JPanel();
    private JComboBox<String> combo = new JComboBox<>();
    private JLabel label = new JLabel("Une ComboBox");

    public FenetreFormulaire(){
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        combo.setPreferredSize(new Dimension(100, 20));
        combo.addItem("Luffy roi des pirates");
        combo.addItem("Zoro");
        combo.addItem("Nami");
        combo.addItem("Sanji");
        // pour communiquer avec la combobox
        combo.addItemListener(new ItemState());
        combo.addActionListener(new ItemAction());

        JPanel top = new JPanel();
        top.add(label);
        top.add(combo);
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }

    //Classe interne implémentant l'interface ItemListener
    class ItemState implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            System.out.println("événement déclenché sur : " + e.getItem() + " - etat : " + e.getStateChange());
        }
    }

    class ItemAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ActionListener : action sur " + combo.getSelectedItem());
        }
    }

}
