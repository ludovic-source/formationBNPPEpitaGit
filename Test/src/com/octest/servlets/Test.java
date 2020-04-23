package com.octest.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Auteur;


@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		request.setAttribute("name", name);
		request.setAttribute("heure", "nuit");
		String[] noms = {"Bill", "Warren", "Jeff"};
		request.setAttribute("noms", noms);
		
		Auteur auteur = new Auteur();
		auteur.setNom("Buffet");
		auteur.setPrenom("Warren");
		auteur.setActif(true);
		
		request.setAttribute("auteur", auteur);
		
		String[] titres = { "Soleil noir", "tintin aux pays des merveilles", "du rififi dans One Piece" };
		request.setAttribute("titres", titres);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
