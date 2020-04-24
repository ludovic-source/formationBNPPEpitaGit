package com.octest.forms;

import javax.servlet.http.HttpServletRequest;

public class ConnexionForm {
	
	private String resultat;
	
	public void verifierIdentifiants(HttpServletRequest request) {
		
		String login = request.getParameter("login");
		String password = request.getParameter("pass");
		
		if (password.equals(login + "123")) {
			resultat = "vous êtes bien connecté !";
		}
		else {
			resultat = "vos identifiants sont incorrects";
		}
		
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}	

}
