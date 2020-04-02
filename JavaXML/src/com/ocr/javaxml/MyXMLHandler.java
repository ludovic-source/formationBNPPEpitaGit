package com.ocr.javaxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLHandler extends DefaultHandler {

    private String node = null;
    private Racine racine;
    private Tronc tronc;
    private Branche branche;
    private Fruit fruit;
    private Feuille feuille;

    //Juste pour le contrôle de la validation du fichier XML avec DOCTYPE
    @Override
    public void notationDecl(String name, String publicId, String systemId){
        System.out.println(name + " - "  + publicId + " - " + systemId);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("ERROR : " + e.getMessage());
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("FATAL ERROR : " + e.getMessage());
        throw e;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {}

    //début du parsing
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Début du parsing");
    }
    //fin du parsing
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin du parsing");
    }

    /**
     * Redéfinition de la méthode pour intercepter les événements
     */
    @Override
    public void startElement(String namespaceURI, String lname,
                             String qname, Attributes attrs) throws SAXException {

        //Nous stockons le nom du nœud pour gérer l'endroit ou affecter la valeur du nœud
        node = qname;

        //dès que nous rencontrons un élément, nous créons l'objet associé
        if(qname.equals("racine")){
            racine = new Racine();
        }
        else if(qname.equals("tronc")){
            tronc = new Tronc();
            tronc.setAttribut(attrs.getValue(0));
        }
        else if(qname.equals("branche")){
            branche = new Branche();
            branche.setAttribut(attrs.getValue(0));
        }
        else if(qname.equals("fruit")){
            fruit = new Fruit();
        }
        else if(qname.equals("feuille")){
            feuille = new Feuille();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException{
        //Lorsque nous détectons la fin d'un élément
        //nous l'ajoutons à son objet parent
        if(qName.equals("tronc")){
            racine.addTronc(tronc);
        }
        else if(qName.equals("branche")){
            tronc.addBranche(branche);
        }
        else if(qName.equals("fruit")){
            branche.addFruit(fruit);
        }
        else if(qName.equals("feuille")){
            branche.addFeuille(feuille);
        }
    }

    /**
     * permet de récupérer la valeur d'un nœud
     */
    @Override
    public void characters(char[] data, int start, int end){

        String str = new String(data, start, end);
        //Il n'y a des valeurs de nœud que pour ces trois là
        //Dès que nous les rencontrons, nous stockons la valeur dans l'objet ad hoc
        if(node.equals("couleur")){
            fruit.setCouleur(str);
        }
        else if(node.equals("nom")){
            fruit.setNom(str);
        }
        else if(node.equals("feuille")){
            feuille.setNom(str);
        }

    }

    public Racine getRacine(){
        return racine;
    }
}
