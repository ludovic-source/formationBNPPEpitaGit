package com.ludovic.ocr.coursJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class PanneauArdoise extends JPanel {


    //Couleur du pointeur
    private Color pointerColor = Color.red;
    //Forme du pointeur
    private String pointerType = "CERCLE";
    //Position X du pointeur
    private int posX = -10, oldX = -10;
    //Position Y du pointeur
    private int posY = -10, oldY = -10;
    //Pour savoir si on doit dessiner ou non
    private boolean erasing = true;
    //Taille du pointeur
    private int pointerSize = 15;
    //Collection de points !
    private ArrayList<Point> points = new ArrayList<Point>();

    public PanneauArdoise(){

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent e) {
                //On récupère les coordonnées de la souris et on enlève la moitié de la taille du pointeur pour centrer le tracé
                points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
                repaint();
            }

            public void mouseMoved(MouseEvent e) {}
        });

    }

    // Vous la connaissez maintenant, celle-là
    public void paintComponent(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Si on doit effacer, on ne passe pas dans le else => pas de dessin
        if(this.erasing){
            this.erasing = false;
        }
        else{
            //On parcourt notre collection de points
            for(Point p : this.points)
            {
                //On récupère la couleur
                g.setColor(p.getCouleur());

                //Selon le type de point
                if(p.getType().equals("CARRE")){
                    g.fillRect(p.getPosX(), p.getPosY(), p.getTaille(), p.getTaille());
                }
                else{
                    g.fillOval(p.getPosX(), p.getPosY(), p.getTaille(), p.getTaille());
                }
            }
        }
    }

    //Efface le contenu
    public void erase(){
        this.erasing = true;
        this.points = new ArrayList<Point>();
        repaint();
    }

    //Définit la couleur du pointeur
    public void setPointerColor(Color c){
        this.pointerColor = c;
    }

    //Définit la forme du pointeur
    public void setPointerType(String str){
        this.pointerType = str;
    }
}