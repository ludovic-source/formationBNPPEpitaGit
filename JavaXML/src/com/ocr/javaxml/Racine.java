package com.ocr.javaxml;

import java.util.ArrayList;

public class Racine {

    private ArrayList<Tronc> listTronc = new ArrayList<Tronc>();

    public void addTronc(Tronc t){
        this.listTronc.add(t);
    }

    public String toString(){
        String str = "Ce nœud racine contient : ";
        for(Tronc t : listTronc)
            str += "\n" + t;

        return str;
    }

}
