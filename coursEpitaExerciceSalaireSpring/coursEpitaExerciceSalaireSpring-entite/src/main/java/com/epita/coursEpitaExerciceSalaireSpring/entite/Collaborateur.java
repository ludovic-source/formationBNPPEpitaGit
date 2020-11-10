package com.epita.coursEpitaExerciceSalaireSpring.entite;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("collaborateur")
public class Collaborateur extends Personne {
	
	// On active le code ci-dessous si on a besoin de récupérer la liste des rémunérations
	//     en passant par une table d'association
	/*
	@OneToMany(mappedBy = "collaborateur", cascade = CascadeType.ALL)
	private Set<RemunerationAnnuelle> remunerations;
	
	public Set<RemunerationAnnuelle> getRemunerations() {
		return remunerations;
	}

	public void setRemunerations(Set<RemunerationAnnuelle> remunerations) {
		this.remunerations = remunerations;
	}	
	*/
}
