package model;

lfhhrfkrkjjkfkgk 
ljf
/*
 * Model class
 * Has as attributes two polynomials
 * The methods call the operation methods
 */
public class Model {
	private Polynomial p1;
	private Polynomial p2;
	
	public Model() {
		this.p1 = null;
		this.p2 = null;
	}
	
	public Model(Polynomial p1, Polynomial p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	public void setPol1(Polynomial p1) {
		this.p1 = p1;
	}
	
	public void setPol2(Polynomial p2) {
		this.p2 = p2;
	}
	
	public Polynomial getPol1() {
		return this.p1;
	}
	
	public Polynomial getPol2() {
		return this.p2;
	}
	
	public Polynomial getSum() {
		return Operations.add(p1, p2);
	}
	
	public Polynomial getDif() {
		return Operations.substract(p1, p2);
	}
	
	public Polynomial getMul() {
		return Operations.multiply(p1, p2);
	}
	
	public String getDiv() {
		Polynomial[] result = Operations.divide(p1, p2);
		return result[0].toString() + " rest " + result[1].toString();
	}
	
	public Polynomial getIntegrate() {
		return Operations.integrate(p1);
	}
	
	public Polynomial getDifferentiate() {
		return Operations.differentiate(p1);
	}
	
}
