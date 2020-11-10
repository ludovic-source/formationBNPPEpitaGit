package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import main.impl.AdressDisplayerImpl;
import main.inter.AdressDisplayer;
import mock.AdressFetcherMock;

public class AdressDisplayerTest {

	private static AdressDisplayer adresse;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adresse = new AdressDisplayerImpl();
		adresse.setAdressFetcher(new AdressFetcherMock());
	}
	
	@Test
	public void testDisplayAddress() {
		String resutlatTheorique = "Mathias Dupond\n5 Avenue champs-Elysés\n75005 Paris";
		String ResultatPratique = adresse.displayAdress("Dupond");
		assertTrue(ResultatPratique.compareTo(resutlatTheorique) == 0);
	}


}
