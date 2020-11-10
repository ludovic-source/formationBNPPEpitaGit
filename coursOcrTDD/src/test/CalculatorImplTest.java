package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import main.Calculator;
import main.CalculatorImpl;

public class CalculatorImplTest {

	@Test
	public final void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDivide() {
		
		Calculator calc = new CalculatorImpl();
		int a, b, res;
		
		a = 5; 
	    b  = 5; 
	    res = a / b;
	    
		if (calc.divide(a, b) != res) {
			fail("a et b positif");
	    }
		
		a = 0; 
	    b  = 5; 
	    res = a / b;
		if (calc.divide(a, b) != res) {
			fail("a nul");
	    }
		
		a = -5; 
	    b  = 5; 
	    res = a / b;
		if (calc.divide(a, b) != res) {
			fail("a negatif");
	    }
		
		a = 5; 
	    b  = -5; 
	    res = a / b;
		if (calc.divide(a, b) != res) {
			fail("b negatif");
	    }
		
		a = -5; 
	    b  = -5; 
	    res = a / b;
		if (calc.divide(a, b) != res) {
			fail("a et b negatif");
	    }
		
	}

	@Test (expected = ArithmeticException.class)
	public final void testDivideByZero() {
		
		Calculator calc = new CalculatorImpl();
		int a, b, res;
		
		a = 5; 
	    b  = 0; 
	    res = 0;
		if (calc.divide(a, b) != res) {
			fail("b nul");
	    }
		
		a = 0; 
	    b  = 0; 
	    res = 0;
		if (calc.divide(a, b) != res) {
			fail("a et b nuls");
	    }
	}

	
	@Test
	public final void testAdd() {

	    Calculator calc = new CalculatorImpl();
	    int a, b, res;
	    
	    // on doit tester plusieurs cas de figure
	    // lorsque vous écrivez un test, il faut tester avec quelques valeurs standards,
	    // qui n'ont pas de signification particulière. 
	    // Puis il faut tester avec les cas limites : nombres négatifs, nuls... 
	    // Si vous prenez des objets, et s'ils étaient null...

	    a = 5; 
        b = 5;
        res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("a et b positif");
	    }
	    // ON PEUT REMPLACER IF PAR : assertTrue("a et b positif", calc.add(a, b) == res);

	    a = 0; 
	    b  = 5; 
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("a nul");
	    }

	    a = 5; 
	    b  = 0;   
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("b nul");
	    }

	    a = 0; 
	    b  = 0;  
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("a et b nuls");
	    }

	    a = -5; 
	    b  = 5; 
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("a negatif");
	    }
	    
	    a = 5; 
	    b  = -5; 
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("b negatif");
	    }

	    a = -5; 
	    b  = -5; 
	    res = a + b;
	    if (calc.add(a, b) != res) {
	        fail("a et b negatif");
	    }
	}

	@Test
	public final void testSubstract() {
		fail("Not yet implemented");
	}

}
