package model;
import java.util.Comparator;

/*
 * Class for comparing the monomials by their degree
 */
public class DegreeComparator implements Comparator<Monomial>{
	public int compare(Monomial m1, Monomial m2) {
		return m2.getDegree() - m1.getDegree();
		
	}
}
