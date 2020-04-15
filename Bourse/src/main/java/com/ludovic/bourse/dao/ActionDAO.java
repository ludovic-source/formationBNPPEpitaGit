package com.ludovic.bourse.dao;

import com.ludovic.bourse.Action;
import com.ludovic.bourse.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ActionDAO {

    private String url = "jdbc:mysql://localhost/bourse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String pwd = "PLATON";


    // récupérer toutes les actions et leurs valeurs
    // on retourne le contenu de l'action demandée

    public Action getAction(String libelleAction) {

        Action action = new Action();

        // on recupère toutes les actions contenues dans la table mysql Action
        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM action");
            while (rs.next()) {
                String libelle = rs.getString("libelle");
                float valeurCourante = rs.getFloat("valeur_courante");
                if (libelle == libelleAction) {
                    action.setLibelle(libelle);
                    action.setValeurCourante(valeurCourante);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action;
    }

    // récupérer toutes les actions de la base de données
    public ArrayList<Action> getAllActions() {


        ArrayList<Action> ListeActions = new ArrayList<>();

        // on recupère toutes les actions contenues dans la table mysql Action
        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM action");
            while (rs.next()) {
                Action action = new Action();
                String libelle = rs.getString("libelle");
                action.setLibelle(libelle);
                float valeurCourante = rs.getFloat("valeur_courante");
                action.setValeurCourante(valeurCourante);
                ListeActions.add(action);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeActions;
    }

    // insérer/mettre à jour une action dans la table
    public void setAction(Action action) {

        String requeteLecture = "SELECT * FROM action WHERE libelle = '" + action.getLibelle() +"'";
        String requeteInsertion = "INSERT INTO action (libelle, valeur_courante) VALUES ('"
                + action.getLibelle() + "', " + action.getValeurCourante() + ")";
        String requeteMiseAJour = "UPDATE action set valeur_courante = "
                + action.getValeurCourante() + " WHERE libelle = '" + action.getLibelle() + "'";

        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(requeteLecture);

            System.out.println(rs.getRow());
            if (rs.getRow() != 0) {
                System.out.println((requeteMiseAJour));
                int nb_maj = statement.executeUpdate(requeteMiseAJour);
            }
            else {
                System.out.println((requeteInsertion));
                int nb_maj = statement.executeUpdate(requeteInsertion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
