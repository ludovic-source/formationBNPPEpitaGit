package com.ludovic.bourse;

import com.ludovic.bourse.dao.ClientDAO;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int identifiantSaisi = 0007;
        Client client = new Client();
        client = new ClientDAO().getClient(0007);

        System.out.println("identifiant : " + client.getIdentifiant());
        System.out.println("nom : " + client.getNom());
        System.out.println("prénom : " + client.getPrenom());

    }
}
