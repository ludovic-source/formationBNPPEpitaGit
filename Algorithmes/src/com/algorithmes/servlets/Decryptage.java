package com.algorithmes.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algorithmes.forms.DecryptageForm;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.xml.internal.ws.api.pipe.NextAction;


@WebServlet("/Decryptage")
public class Decryptage extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   
    public Decryptage() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		this.getServletContext().getRequestDispatcher("/WEB-INF/decryptage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// traitement de la requete
		DecryptageForm decryptageForm = new DecryptageForm();
		decryptageForm.decrypterForm(request);
		
		// recuperation du resultat
		request.setAttribute("texteChiffre", decryptageForm.getResultat());
		request.setAttribute("texteNonChiffre", decryptageForm.getTexteAchiffrer());
		request.setAttribute("mapFrequenceLettres", decryptageForm.getMapFrequenceLettresTexteChiffre());
		request.setAttribute("texteDecrypte", decryptageForm.getTexteDecrypte());
		request.setAttribute("tabLettresAdjacentes", decryptageForm.getTabLettresAdjacentes());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/decryptage.jsp").forward(request, response);
	}

}
