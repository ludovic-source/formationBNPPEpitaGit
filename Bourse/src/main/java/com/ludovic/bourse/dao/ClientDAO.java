package com.ludovic.bourse.dao;

import com.ludovic.bourse.Client;

import java.sql.*;
import java.util.*;

public class ClientDAO {

    private String url = "jdbc:mysql://localhost/bourse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String pwd = "PLATON";


    // récupérer le nom et prénom du client correspondant à l'identifiant saisi par le client

    public Client getClient(int identifiantSaisi) {

        Client client = new Client();

        // on recupère tous les clients contenus dans la table mysql Client
        // puis on conserve uniquement le client dont l'identifiant saisi correspond

        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client");
            while (rs.next()) {
                int identifiant = rs.getInt("identifiant");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                if (identifiant == identifiantSaisi) {
                    client.setIdentifiant(identifiant);
                    client.setNom(nom);
                    client.setPrenom(prenom);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;

    }

}
