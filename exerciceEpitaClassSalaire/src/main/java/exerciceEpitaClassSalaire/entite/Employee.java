package exerciceEpitaClassSalaire.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class Employee extends Personne {	
	
}
