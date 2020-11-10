package essai;

public class FirstTestsDAO {

	  public static void main(String[] args) {
		  
		    //Testons des �l�ves
		    DAO<Eleve> eleveDao = new EleveDAO(ConnectSingleton.getInstance());
		    for(int i = 1; i < 5; i++){
		      Eleve eleve = eleveDao.find(i);
		      System.out.println("El�ve N�" + eleve.getId() + "  - " + eleve.getNom() + " " + eleve.getPrenom());
		    }
		      
		    System.out.println("\n********************************\n");
		      
		    //Voyons voir les professeurs
		    DAO<Professeur> profDao = new ProfesseurDAO(ConnectSingleton.getInstance());
		    for(int i = 4; i < 8; i++){
		      Professeur prof = profDao.find(i);
		      System.out.println(prof.getNom() + " " + prof.getPrenom() + " enseigne : ");
		      for(Matiere mat : prof.getListMatiere())
		        System.out.println("\t * " + mat.getNom());
		    }
		      
		    System.out.println("\n********************************\n");
		      
		    //Et l�, c'est la classe
		    DAO<Classe> classeDao = new ClasseDAO(ConnectSingleton.getInstance());
		    Classe classe = classeDao.find(11);
		      
		    System.out.println("Classe de " + classe.getNom());
		    System.out.println("\nListe des �l�ves :");
		    for(Eleve eleve : classe.getListEleve())
		      System.out.println("  - " + eleve.getNom() + " " + eleve.getPrenom());
		      
		    System.out.println("\nListe des professeurs :");
		    for(Professeur prof : classe.getListProfesseur())
		      System.out.println("  - " + prof.getNom() + " " + prof.getPrenom());      
		  }
}
