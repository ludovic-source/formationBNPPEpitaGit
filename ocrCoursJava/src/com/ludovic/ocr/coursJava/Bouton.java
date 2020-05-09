package com.ludovic.ocr.coursJava;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public class Bouton extends JButton {

    private String name;

    public Bouton(String str){
        super(str);
        this.name = str;
    }

}
