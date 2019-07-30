package model;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/* 
 * Polynomial class
 * Has as attibutes degree and an ArrayList of monomials
 */
public class Polynomial {
	
	private int degree;
	private ArrayList<Monomial> polynomial;
	
	public Polynomial (int degree, ArrayList<Monomial> polynomial) {
		this.degree = degree;
		this.polynomial = polynomial;
	}
	
	public Polynomial() {
		this.degree = 0;
		this.polynomial = new ArrayList<Monomial>();
	}
	
	// method for getting polynomial's degree
	public int getDegree() {
		return this.degree;
	}
	
	// method for setting polynomial's degree
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	// method for setting poynomial's terms
	public void setPolynomial(ArrayList<Monomial> polynomial) {
		this.polynomial = polynomial;
	}
	
	// method for getting polynomial's terms
	public ArrayList<Monomial> getPolynomial(){
		return this.polynomial;
	}
	
	// method for sorting the terms in descending order by their degree
	private void sortPolyn() {
		Collections.sort(polynomial, new DegreeComparator());
	}
	
	// method for adding a term to the polynomial
	public void addTerm(Monomial monomialToAdd) {
		if ( polynomial == null) {
			polynomial.add(monomialToAdd);
			this.setDegree(monomialToAdd.getDegree());
		}
		else {
		ListIterator <Monomial> iter = polynomial.listIterator();
		boolean foundSameDegree = false;
		while(iter.hasNext()) {
			Monomial mon = iter.next();
			// if we find a monomial equal in degree with the monomial we want to add
			// we only add their coefficients
			if ( mon.getDegree() == monomialToAdd.getDegree()) {
				foundSameDegree = true;
				mon.setCoefficient(mon.getCoefficient() + monomialToAdd.getCoefficient());
				// if their coefficients' addition is equal to 0, we remove the term
				if (mon.getCoefficient() == 0) {
					iter.remove();
				}
			}
		}
		// if we don't find a monomial equal in degree with the monomial we want to add
		// we add the term in the list
		if ( foundSameDegree == false) {
			polynomial.add(monomialToAdd);
			if( degree < monomialToAdd.getDegree()) {
				this.setDegree(monomialToAdd.getDegree());
			}
		}
		// as we add terms, we sort the polynomial's terms
		this.sortPolyn();
		// we set the degree of the polynomial
		try {
		    this.setDegree(polynomial.get(0).getDegree());
		}
		catch(Exception ex) {
		this.setDegree(0);
		}
	 }
	}
	
	// method for displaying the polynomial's terms as a String
	@Override
	public String toString() {
		if ( this.polynomial.size() == 0) {
			return "0.0 ";
		}
		else {
		String myPol = new String("");
		ListIterator <Monomial> iter = polynomial.listIterator();
		while(iter.hasNext()) {
			Monomial myMon = iter.next();
			if( myMon.getDegree() == degree) {
				myPol += myMon.toString() + " ";
			}
			else {
				if(myMon.getCoefficient() > 0) {
					myPol += "+";
				}
				myPol += myMon.toString() + " ";
			}
		}
		return myPol;
		}
	}

	// method for building a polynomial from a String input
	public static Polynomial fromString(String userInput) {
		Pattern p = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
		Matcher m1 = p.matcher(userInput);
		Polynomial pol = new Polynomial();
		boolean found = false;
		boolean powerEx = false;
		while(m1.find()) {
			try {
			Monomial mon = new Monomial(Integer.parseInt(m1.group(2)), Float.parseFloat(m1.group(1)) );
			pol.addTerm(mon);
			found = true;
			}
			// check if the degree of the introduced polynomial is too large
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Degree too high!");
				powerEx = true;
			}
		}
		// check if the input matches the pattern
		if(found == false && powerEx == false) {
			JOptionPane.showMessageDialog(null, "Wrong input!");
		}
		return pol;
	}
	

}


