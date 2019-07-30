package model;

/*
 * Monomial class
 */
public class Monomial {

	private int degree;
	private float coefficient;
	
	public Monomial(int degree, float coefficient) {
		this.degree = degree;	
		this.coefficient = coefficient;
	}
	
	public Monomial() {
		this.degree = 0;
		this.coefficient = 0;
	}
	
	// method for getting monomial's degree
	public int getDegree() {
		return this.degree;
	}
	
	// method for getting monomial's coefficient
	public float getCoefficient() {
		return this.coefficient;
	}
	
	// method for setting monomial's degree
	public void setDegree(int degree) {
			this.degree = degree;
	}
	
	// method for setting monomial's coefficient
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
	
	// method for displaying the monomial as a String
	@Override
	public String toString() {
		if( this.coefficient == 0) {
			return "0.0";
		}
		else if( this.degree == 0) {
			return coefficient + "";
		}
		else if( this.degree == 1) {
			return coefficient + "x";
		}
		else return coefficient + "x^" + degree;
	}
	
}

