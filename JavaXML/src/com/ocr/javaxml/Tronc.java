package com.ocr.javaxml;

import java.util.ArrayList;

public class Tronc {

    private ArrayList<Branche> listBranche = new ArrayList<Branche>();
    private String attribut = null;

    public void setAttribut(String att){
        this.attribut = att;
    }

    public void addBranche(Branche b){
        this.listBranche.add(b);
    }

    public String toString(){
        String str = "  Un nœud tronc contient : ";
        str += "\n    un attribut qui vaut " + this.attribut;

        for(Branche b : listBranche)
            str += "\n   " + b;

        return str;
    }

}
