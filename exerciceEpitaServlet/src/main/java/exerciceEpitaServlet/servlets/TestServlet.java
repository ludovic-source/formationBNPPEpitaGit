package exerciceEpitaServlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exerciceEpitaServlet.dao.DaoFactory;
import exerciceEpitaServlet.dao.DaoPersonne;
import exerciceEpitaServlet.entite.Personne;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TestServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		creerPersonne();
		Personne personne = lirePersonne();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
			out.println("<HEAD>");
				out.println("<TITLE>Hello</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
				out.println("<H1>One Piece</H1>");
				out.println("<p>" + personne.getNom() + "</p>");
			out.println("</BODY>");
			out.println("<HEAD>");
			out.println("<TITLE>Hello</TITLE>");
		out.println("</HTML>");	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private static void creerPersonne() {
		Personne personne = new Personne();
		personne.setId(2);
		personne.setNom("Zoro");
		
		DaoPersonne daoPersonne = DaoFactory.getDaoPersonne();
		daoPersonne.createPersonne(personne);
		
	}
	
	private Personne lirePersonne() {
		
		DaoPersonne daoPersonne = DaoFactory.getDaoPersonne();
		Personne personne = daoPersonne.getPersonne(1);
		return personne;
		
	}

}
