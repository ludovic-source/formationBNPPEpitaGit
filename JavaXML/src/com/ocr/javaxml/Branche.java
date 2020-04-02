package com.ocr.javaxml;

import java.util.ArrayList;

public class Branche {

    private ArrayList<Fruit> listFruit = new ArrayList<Fruit>();
    private ArrayList<Feuille> listFeuille = new ArrayList<Feuille>();
    private String attribut = null;

    public void addFruit(Fruit fruit){
        this.listFruit.add(fruit);
    }

    public void addFeuille(Feuille feuille){
        this.listFeuille.add(feuille);
    }

    public void setAttribut(String att){
        this.attribut = att;
    }

    public String toString(){
        String str = "    Un nœud branche contient : ";
        str += "\n          un attribut qui vaut " + this.attribut;
        for(Fruit f : listFruit)
            str += "\n\n       " + f;

        for(Feuille f : listFeuille)
            str += "\n\n      " + f;

        return str;
    }
}
