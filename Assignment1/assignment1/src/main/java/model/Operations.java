package model;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/*
 * Operations class
 * Has no attributes
 * All methods are static
 */
public class Operations {

	// method for addition of polynomials
	public static Polynomial add(Polynomial p1, Polynomial p2) {
		Polynomial newPol = new Polynomial();
		Monomial ctMon = new Monomial();
		float newCoeff;
		int newDegree;
		ListIterator <Monomial> iter1 = p1.getPolynomial().listIterator();
		// we copy the first polynomial into the result
		while(iter1.hasNext()) {
			ctMon = iter1.next();
			newCoeff = ctMon.getCoefficient();
			newDegree = ctMon.getDegree();
			Monomial newMon = new Monomial(newDegree, newCoeff);
			newPol.addTerm(newMon);
		}
		// we add every monomial from second polynomial to the result
		ListIterator <Monomial> iter2 = p2.getPolynomial().listIterator();
		while( iter2.hasNext()) {
			newPol.addTerm(iter2.next());
		}
		return newPol;
	}
	
	// method for subtraction of polynomials
	public static Polynomial substract(Polynomial p1, Polynomial p2) {
		Polynomial newPol = new Polynomial();
		Monomial ctMon = new Monomial();
		float newCoeff;
		int newDegree;
		ListIterator <Monomial> iter1 = p1.getPolynomial().listIterator();
		// we copy the first monomial into the result
		while(iter1.hasNext()) {
			ctMon = iter1.next();
			newCoeff = ctMon.getCoefficient();
			newDegree = ctMon.getDegree();
			Monomial newMon = new Monomial(newDegree, newCoeff);
			newPol.addTerm(newMon);
		}
		// we invert the sign of second polynomial's coefficients and add the terms to result
		ListIterator <Monomial> iter2 = p2.getPolynomial().listIterator();
		while( iter2.hasNext()) {
			Monomial currentMon = iter2.next();
			Monomial newMon2 = new Monomial(currentMon.getDegree(), -currentMon.getCoefficient());
			newPol.addTerm(newMon2);
		 }
		return newPol;
	}
	
	// method for differentiating a polynomial
	public static Polynomial differentiate(Polynomial p1) {
		Polynomial newPol = new Polynomial();
		Monomial currentMon = new Monomial();
		ListIterator <Monomial> iter = p1.getPolynomial().listIterator();
		while( iter.hasNext()) {
			currentMon = iter.next();
			if( currentMon.getDegree() > 0) {
				float newCoeff = currentMon.getDegree() * currentMon.getCoefficient();
				int newDegree = currentMon.getDegree() - 1;
				Monomial newMon = new Monomial(newDegree, newCoeff);
				newPol.addTerm(newMon);
			}
		}
		return newPol;
	}
	
	// method for multiplication of polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial newPol = new Polynomial();
		Monomial currentMon1 = new Monomial();
		Monomial currentMon2 = new Monomial();
		int newDeg;
		float newCoef;
		ListIterator <Monomial> iter1 = p1.getPolynomial().listIterator();
		ListIterator <Monomial> iter2 = p2.getPolynomial().listIterator();
		// we go through every term of first polynomial and multiply it with every term of second polynomial's
		while(iter1.hasNext()) {
			currentMon1 = iter1.next();
			while (iter2.hasNext()) {
				currentMon2 = iter2.next();
				newCoef = currentMon1.getCoefficient() * currentMon2.getCoefficient();
				newDeg = currentMon1.getDegree() + currentMon2.getDegree();
				Monomial newMon = new Monomial(newDeg, newCoef);
				newPol.addTerm(newMon);
			}
			iter2 = p2.getPolynomial().listIterator();
		}
		return newPol;
	}
	
	// method for integration of a polynomial
	public static Polynomial integrate(Polynomial p1) {
		Polynomial newPol = new Polynomial();
		Monomial currentMon = new Monomial();
		ListIterator <Monomial> iter = p1.getPolynomial().listIterator();
		while( iter.hasNext()) {
			currentMon = iter.next();
			if( currentMon.getDegree() >= 0) {
				int newDegree = currentMon.getDegree() + 1;
				float newCoeff = currentMon.getCoefficient() / newDegree;
				Monomial newMon = new Monomial(newDegree, newCoeff);
				newPol.addTerm(newMon);
			}
		}
		return newPol;
	}
	
	// method for division of two polynomials
	public static Polynomial[] divide(Polynomial p1, Polynomial p2) {
		Polynomial[] result = new Polynomial[2];
		Polynomial quotient = new Polynomial();
		Polynomial rest = new Polynomial();
		Monomial ctMon = new Monomial();
		float newCoeff;
		int newDegree;
		// we check if the coefficient of the second polynomial is equal to 0
		// if it is, we display the error message
		if ( p2.getPolynomial().get(0).getCoefficient() == 0 ) {
			result [0] = Polynomial.fromString("0x^0");
			result [1] = Polynomial.fromString("0x^0");
			JOptionPane.showMessageDialog(null, "You cannot divide by 0!");
			return result;
		}
		// we check if second polynomial's degree is equal to 0
		// if it is, we only divide the first polynomial's coefficients with that value
		else if (p2.getDegree() == 0) {
			ListIterator <Monomial> iter1 = p1.getPolynomial().listIterator();
			while(iter1.hasNext()) {
			ctMon = iter1.next();
			newCoeff = ctMon.getCoefficient() / p2.getPolynomial().get(0).getCoefficient();
			newDegree = ctMon.getDegree();
			System.out.println(newCoeff + newDegree);
			Monomial newMon = new Monomial(newDegree, newCoeff);
			quotient.addTerm(newMon);
			
			}
			result[0] = quotient;
			result[1] = rest;
			return result;
			
		}
		// if none of the above mentioned cases apply, we do the division as usual
		else {
		ListIterator <Monomial> iter1 = p1.getPolynomial().listIterator();
		// copy the first polynomial into the remainder
		while(iter1.hasNext()) {
			ctMon = iter1.next();
			newCoeff = ctMon.getCoefficient();
			newDegree = ctMon.getDegree();
			Monomial newMon = new Monomial(newDegree, newCoeff);
			rest.addTerm(newMon);
		}
		
		// while the divident's degree is bigger than divisor's degree
		while ( rest.getDegree() >= p2.getDegree() && rest.getPolynomial().get(0) != null) {
			// we do the division and store the quotient
			newCoeff = rest.getPolynomial().get(0).getCoefficient() / p2.getPolynomial().get(0).getCoefficient();
			newDegree = rest.getPolynomial().get(0).getDegree() - p2.getPolynomial().get(0).getDegree();
			Monomial quotMon = new Monomial(newDegree, newCoeff);
			Polynomial quotPol = new Polynomial();
			quotPol.addTerm(quotMon);
			quotient.addTerm(quotMon);
			Polynomial mult = multiply(p2,quotPol);
			rest = substract(rest, mult);
		}
		
		result[0] = quotient;
		result[1] = rest;
		return result;
		}
	}
}
	

