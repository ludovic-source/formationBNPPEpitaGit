package exerciceEpitaClassSalaire.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exerciceEpitaClassSalaire.dao.AdressePostaleDao;
import exerciceEpitaClassSalaire.dao.DaoFactory;
import exerciceEpitaClassSalaire.dao.EntrepriseDAO;
import exerciceEpitaClassSalaire.dao.PersonneDao;
import exerciceEpitaClassSalaire.entite.AdressePostale;
import exerciceEpitaClassSalaire.entite.Employee;
import exerciceEpitaClassSalaire.entite.Entreprise;
import exerciceEpitaClassSalaire.entite.Personne;

public class App {

	public static void main(String[] args) {
		
		PersonneDao personneDao = DaoFactory.getPersonneDao();
		AdressePostaleDao adressePostaleDao = DaoFactory.getAdressePostaletDao();	
		EntrepriseDAO entrepriseDAO = DaoFactory.getEntrepriseDao();
	
				
		// créer une nouvelle adresse postale
		AdressePostale adressePostale = new AdressePostale();
		adressePostale.setId_adresse(500);
		adressePostale.setCode_postal(94340);
		adressePostale.setNom_rue("One piece");
		adressePostaleDao.createAdressePostale(adressePostale);
	
		// créer une entreprise
		Entreprise entreprise = new Entreprise();
		entreprise.setAdresse_postale_entreprise(adressePostale);
		entreprise.setNom_entreprise("Restaurant Le Baratie");
		entrepriseDAO.createEntreprise(entreprise);
		
		// créer une nouvelle personne et son adresse postale
		Set<Personne> personnes = new HashSet<>();
		Personne personne = new Employee();
		personne.setId_personne(1);
		personne.setAdressePostale(adressePostale);
		personne.setNom_personne("Lufy");
		personne.setEntreprise(entreprise);
		personnes.add(personne);
		adressePostale.setPersonnes(personnes);		
		personneDao.createPersonne(personne);
		
		personne.setId_personne(2);
		personne.setAdressePostale(adressePostale);
		personne.setNom_personne("Zoro");
		//personne.setId_entreprise(1000);
		personne.setEntreprise(entreprise);
		personneDao.createPersonne(personne);
		personne.setId_personne(3);
		personne.setAdressePostale(adressePostale);
		personne.setNom_personne("Nami");
		personne.setEntreprise(entreprise);
		//personne.setId_entreprise(1000);	
		personneDao.createPersonne(personne);	
		
		/*
		// récupérer toutes les personnes
		List<Personne> listePersonnes = personneDao.getAllPersonnes();
		for (Personne personneLue : listePersonnes) {
			System.out.println("nom personne : " + personneLue.getNom_personne());
		}
		/*
		Personne personne = personneDao.getPersonne(1);
		System.out.print("nom personne" + personne.getNom_personne());

		// récupérer une adresse dans la table
		AdressePostale adressePostale = adressePostaleDao.getAdressePostale(1);
		System.out.print("adresse postale" + adressePostale.getNom_rue());
		*/
	}

}
