package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Utilisateur;
import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;


public class TestSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
       
    public TestSession() {
        super();
    }

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// pour récupérer les cookies existants
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("valeurCookie")) {
                    request.setAttribute("valeurCookie", cookie.getValue());
                }
            }
        }
		
        try {
            request.setAttribute("utilisateurs", utilisateurDao.lister());
        }
        catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/testSession.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = (String) request.getParameter("nom");
		String prenom = (String) request.getParameter("prenom");
		String valeurCookie = "OnePiece";
		
        HttpSession session = request.getSession();

        session.setAttribute("nom", nom);
        session.setAttribute("prenom", prenom);        
        
        Cookie cookie = new Cookie("valeurCookie", valeurCookie);
        cookie.setMaxAge(60 * 60 * 24 * 30); // l'age s'exprime en secondes, ici il sera valable pendant 1 mois
        response.addCookie(cookie);
       
        try {        
	        Utilisateur utilisateur = new Utilisateur(); 
			utilisateur.setNom(nom);		
	        utilisateur.setPrenom(prenom);
	        
	        utilisateurDao.ajouter(utilisateur);
	        
	        request.setAttribute("utilisateurs", utilisateurDao.lister());
        }
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());        	
        }
	        
        this.getServletContext().getRequestDispatcher("/WEB-INF/testSession.jsp").forward(request, response);
	}

}
