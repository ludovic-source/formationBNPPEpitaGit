package main;

import java.util.List;

public interface MyList {
	 
		void add(String texte);
		boolean removeAt(int pos);
		boolean removeItem(String texte);
		void setAt(String texte, int pos);
		String getAt(int pos);
		int getSize();
		void reset();

}
