package heritage;

public abstract class Animal {
		
	private String regimeAlimentaire;
	
	public abstract void deplacer();

	public String getRegimeAlimentaire() {
		return regimeAlimentaire;
	}

	public void setRegimeAlimentaire(String regimeAlimentaire) {
		this.regimeAlimentaire = regimeAlimentaire;
	}

	
}
