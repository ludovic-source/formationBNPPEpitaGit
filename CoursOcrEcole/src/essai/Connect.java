package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class Connect {

public static void main(String[] args) {
	  try {
		    Class.forName("org.postgresql.Driver");
		       
		    String url = "jdbc:postgresql://localhost:5432/Ecole";
		    String user = "postgres";
		    String passwd = "PLATON";
		    
		    String profNomPrecedent = "";
		       
		    Connection conn = DriverManager.getConnection(url, user, passwd);
		       
		    //Création d'un objet Statement
		    Statement state = conn.createStatement();
		    //L'objet ResultSet contient le résultat de la requête SQL
		    String query = "SELECT * FROM professeur "
    							+ "LEFT JOIN j_mat_prof "
    							+ "ON professeur.prof_id = j_mat_prof.jmp_prof_k "
    							+ "LEFT JOIN matiere ON jmp_mat_k = mat_id "
    							+ "ORDER BY prof_nom;";
		    ResultSet result = state.executeQuery(query);	
		    
		    System.out.println("\n---------------------------------");
		    
			while(result.next()){ 
			    			
				if (result.getString("prof_nom").equals(profNomPrecedent)) {
					System.out.print("\t" + result.getString("mat_nom"));
				}
				else {					
				    System.out.println("\n---------------------------------");	
					System.out.print("\t" + result.getString("prof_nom") + "\t | \t" + result.getString("prof_prenom") + "\t |");
					System.out.print("\t" + result.getString("mat_nom"));
					profNomPrecedent = result.getString("prof_nom");
				}
			    
			}
		
		    result.close();
		    state.close();
	       
		  	} catch (Exception e) {
		  		e.printStackTrace();	  		
		  	}      
	  }
}