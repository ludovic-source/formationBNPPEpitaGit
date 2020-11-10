package heritage;

public class App {

	public static void main(String[] args) {
		
		IServiceTarification serviceTarification = new ServiceTarificationImpl();
		
		Animal mammifere = new Mammifere();
		mammifere.deplacer();
		
		Animal reptile = new Reptile();
		reptile.deplacer();
		reptile.getRegimeAlimentaire();
		mammifere.getRegimeAlimentaire();
		
		Reptile reptile2 = new Reptile();
		reptile2.getRegimeAlimentaire();
		
		System.out.println("prix : " + serviceTarification.calculerPrix(mammifere));
		

	}

}
