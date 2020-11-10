package main;

public class CalculatorImpl implements Calculator {

	@Override
	public int multiply(int a, int b) {
		
		return 0;
	}

	@Override
	public int divide(int a, int b) {
		
		int resultat = 0;
		
		if (b == 0) {
			throw new ArithmeticException();
	    }
		
		boolean resEstNegatif = false;
		
		if (a < 0 && b > 0) {
			resEstNegatif = true;
			a = -a;
		}
		if (a > 0 && b < 0) {
			resEstNegatif = true;
			b = -b;
		}
		
		if (a < 0 && b < 0) {
			b = -b;
			a = -a;
		}
		
		while (a > 0) {
	        a = substract(a, b);
	        resultat ++;
		}
		if (resEstNegatif) {
			resultat = -resultat;
        }		
		
		return resultat;
	}

	@Override
	public int add(int a, int b) {
		int resultat = a;
		
		if (b >= 0) {
			for (int i=1; i <= b; i++) {
				resultat ++;
			}
		}
		else {
			for (int i=-1; i >= b; i--) {
				resultat --;
			}
		}
			
		return resultat;
	}

	@Override
	public int substract(int a, int b) {
		
		return 0;
	}

}
