package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JSpinner.ListEditor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.MyList;
import main.MyListImpl;

public class MyListImplTest {

	// Les m�thodes appel�es avant et apr�s tous les tests nous servirons
	// � initialiser des variables et ressources communes � tous les tests
	// et � les nettoyer � la fin. 
	
	private static ArrayList<String> liste = new ArrayList<>();
	MyListImpl myList = new MyListImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("avant tout");
		String texte = "texte n�";
		for (int i = 0; i < 5; i++) {
			liste.add(i, texte+i);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("apr�s tout");
		for (String texte : liste) {
			System.out.println(texte);			
		}
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("avant un test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("apr�s un test");
	}

	@Test
	public void testAdd() {
		int tailleListeAvant = myList.getSize();
		myList.add("roi des pirates");
		if (myList.getSize() != tailleListeAvant + 1) {
			fail("AJOUT NON REALISE");
		}
	}

	@Test
	public void testRemoveAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAt() {
		fail("Not yet implemented");
	}

}
