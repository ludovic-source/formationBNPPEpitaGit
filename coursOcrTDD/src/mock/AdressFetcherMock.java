package mock;

import main.impl.Adress;
import main.inter.AdressFetcher;

public class AdressFetcherMock implements AdressFetcher {

	@Override
	public Adress fetchAdress(String name) {
		return new Adress("Avenue champs-Elysés", "Mathias Dupond", 5, 75005, "Paris");
	}

}
