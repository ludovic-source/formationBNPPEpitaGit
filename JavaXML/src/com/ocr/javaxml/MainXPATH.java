package com.ocr.javaxml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainXPATH {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("xml/arbre.xml");
            Document xml = builder.parse(fileXML);
            Element root = xml.getDocumentElement();
            XPathFactory xpf = XPathFactory.newInstance();
            XPath path = xpf.newXPath();

            String expression = "/racine/tronc/branche[1]";
            String str = (String) path.evaluate(expression, root);
            System.out.println(str);
            System.out.println("-------------------------------------");

            expression = "/racine/tronc/branche[2]";
            str = (String) path.evaluate(expression, root);
            System.out.println(str);
            System.out.println("-------------------------------------");

            expression = "/racine/tronc/branche[1]/feuille[1]";
            str = (String) path.evaluate(expression, root);
            System.out.println(str);
            System.out.println("-------------------------------------");

            expression = "/racine/tronc/branche[2]/feuille[2]";
            str = (String) path.evaluate(expression, root);
            System.out.println(str);
            System.out.println("-------------------------------------");

            expression = "//branche";
            boolean bool = (Boolean) path.evaluate(expression, root, XPathConstants.BOOLEAN);
            System.out.println(bool);

            expression = "/branche";
            bool = (Boolean) path.evaluate(expression, root, XPathConstants.BOOLEAN);
            System.out.println(bool);
            System.out.println("-------------------------------------");

            //Nous allons récupérer le premier nœud branche
            expression = "//branche[1]";
            //Nous castons le résultat en Node
            Node node = (Node) path.evaluate(expression, root, XPathConstants.NODE);
            //Nous affichons son contenu
            System.out.println(node.getNodeName() + " : " + node.getTextContent());
            System.out.println("-------------------------------------");

            //nous allons récupérer le premier nœud feuille
            expression = "feuille[1]";
            Element branche = (Element) node;
            //Mais cette fois, notre point de départ sera la branche récupérée précédemment
            node = (Node) path.evaluate(expression, branche, XPathConstants.NODE);
            //Nous affichons le contenu
            System.out.println(node.getNodeName() + " : " + node.getTextContent());
            System.out.println("-------------------------------------");

            //nous allons récupérer le deuxième nœud feuille
            //du deuxième nœud branche
            expression = "//branche[2]/feuille[2]";
            //Mais nous réutilisons la racine comme point de départ
            node = (Node) path.evaluate(expression, root, XPathConstants.NODE);
            //Nous affichons le contenu
            System.out.println(node.getNodeName() + " : " + node.getTextContent());
            System.out.println("-------------------------------------");

            //récupération de tous les nœuds branche du fichier
            expression = "//branche";
            //On cast le résultat en Nodelist
            NodeList list = (NodeList) path.evaluate(expression, root, XPathConstants.NODESET);
            int nodesLength = list.getLength();

            //Parcours de la boucle
            for (int i = 0; i < nodesLength; i++) {
                Node n = list.item(i);
                System.out.println(n.getNodeName() + " : " + n.getTextContent());

                //ici, en changeant de point de départ et avec cette expression
                //nous allons récupérer la liste des nœuds feuille
                //du nœud branche actuellement utilisé
                expression = "feuille";
                path.compile(expression);
                //Nous prenons donc comme point de départ n
                NodeList list2 = (NodeList) path.evaluate(expression, n, XPathConstants.NODESET);
                int nodesLength2 = list2.getLength();

                //nous parcourons maintenant la liste des nœuds feuille du nœud branche en cours
                for (int j = 0; j < nodesLength2; j++) {
                    Node n2 = list2.item(j);
                    System.out.println(n2.getNodeName() + " : " + n2.getTextContent());
                }
                System.out.println("--------------------------------------");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
