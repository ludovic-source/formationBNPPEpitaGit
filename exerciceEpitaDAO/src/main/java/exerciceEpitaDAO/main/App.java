package exerciceEpitaDAO.main;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import exerciceEpitaDAO.DAO.DAOFactory;
import exerciceEpitaDAO.DAO.VoitureDAO;
import exerciceEpitaDAO.entite.Voiture;

public class App {

	public static void main(String[] args) {
		
		try {
			Server.createTcpServer().start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();		
		
		Voiture voiture = new Voiture();
		voiture.setName("Porsche");
		
		voitureDAO.create(voiture);
		
		Voiture voitureLue2 = voitureDAO.findSingleVoiture(1);
		System.out.println("La voiture est une : " + voitureLue2.getName());
				
	}



}

