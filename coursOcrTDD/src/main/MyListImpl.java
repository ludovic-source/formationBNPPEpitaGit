package main;

import java.util.ArrayList;
import java.util.List;

public class MyListImpl implements MyList {

	private ArrayList<String> liste = new ArrayList<>();
	
	@Override
	public void add(String texte) {			
		//liste.add(texte);
	}

	@Override
	public boolean removeAt(int pos) {
		if (pos < liste.size() && pos > -1 ) {
			liste.remove(pos);			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean removeItem(String texte) {
		if (liste.contains(texte)) {	
			liste.remove(texte);
			return true;			
		}
		else {
			return false;
		}
	}

	@Override
	public void setAt(String texte, int pos) {
		liste.add(pos, texte);		
	}

	@Override
	public String getAt(int pos) {		
		return liste.get(pos);
	}

	@Override
	public int getSize() {
		return liste.size();
	}

	@Override
	public void reset() {
		liste = new ArrayList<>();		
	}

}
