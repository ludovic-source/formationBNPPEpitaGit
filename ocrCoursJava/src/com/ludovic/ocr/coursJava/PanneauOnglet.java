package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;

public class PanneauOnglet extends JPanel {

    private Color color = Color.white;
    private static int COUNT = 0;
    private String message = "";

    public PanneauOnglet(){}

    public PanneauOnglet(Color color){
        this.color = color;
        this.message = "Contenu du panneau N°" + (++COUNT);
    }
    public void paintComponent(Graphics g){
        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(this.message, 10, 20);
    }


}
