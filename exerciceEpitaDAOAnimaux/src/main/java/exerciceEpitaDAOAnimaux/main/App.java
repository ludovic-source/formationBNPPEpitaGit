package exerciceEpitaDAOAnimaux.main;

import exerciceEpitaDAOAnimaux.DAO.DAOFactory;
import exerciceEpitaDAOAnimaux.DAO.MammifereDAO;
import exerciceEpitaDAOAnimaux.entite.Mammifere;

public class App {

	public static void main(String[] args) {
		
		MammifereDAO mammifereDAO = DAOFactory.getMammifereDAO();
		
		// ajout d'un mammifere dans la table
		Mammifere mammifereACreerMammifere = new Mammifere();
		mammifereACreerMammifere.setId(1);
		mammifereACreerMammifere.setName("Chien");
		mammifereDAO.createMammifere(mammifereACreerMammifere);
		mammifereACreerMammifere.setId(2);
		mammifereACreerMammifere.setName("Chat");
		mammifereDAO.createMammifere(mammifereACreerMammifere);
		mammifereACreerMammifere.setId(3);
		mammifereACreerMammifere.setName("Lion");
		mammifereDAO.createMammifere(mammifereACreerMammifere);
		
		// lecture d'un mammifere
		Mammifere mammifereLu; 
		for (int i = 1; i <= 3; i++ ) {
			mammifereLu= mammifereDAO.lireMammifere(i);
			System.out.println("le mamifere " + i + " lu dans la table est un " + mammifereLu.getName());
		}
		
		
	}

}
