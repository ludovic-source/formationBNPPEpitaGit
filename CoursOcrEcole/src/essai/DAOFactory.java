package essai;

import java.sql.Connection;

public class DAOFactory extends AbstractDAOFactory {

	  protected static final Connection conn = ConnectSingleton.getInstance();   
	   
	  /**
	  * Retourne un objet Classe interagissant avec la BDD
	  * @return DAO
	  */
	  public DAO getClasseDAO(){
	    return new ClasseDAO(conn);
	  }

	  /**
	  * Retourne un objet Professeur interagissant avec la BDD
	  * @return DAO
	  */
	  public DAO getProfesseurDAO(){
	    return new ProfesseurDAO(conn);
	  }

	  /**
	  * Retourne un objet Eleve interagissant avec la BDD
	  * @return DAO
	  */
	  public DAO getEleveDAO(){
	    return new EleveDAO(conn);
	  }

	  /**
	  * Retourne un objet Matiere interagissant avec la BDD
	  * @return DAO
	  */
	  public DAO getMatiereDAO(){
	    return new MatiereDAO(conn);
	  }   

}
