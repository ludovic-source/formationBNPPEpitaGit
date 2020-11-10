package main.impl;

import main.inter.AdressDisplayer;
import main.inter.AdressFetcher;

public class AdressDisplayerImpl implements AdressDisplayer {

	private AdressFetcher adressFetcher;
	
	@Override
	public String displayAdress(String name) {
		Adress a = adressFetcher.fetchAdress(name);
		String address = a.getName() + "\n";
		address += a.getNb() + " " + a.getStreet() + "\n";
		address += a.getZip() + " " + a.getTown();
		return address;
	}

	@Override
	public void setAdressFetcher(AdressFetcher af) {
		this.adressFetcher = af;
	}
	
}
