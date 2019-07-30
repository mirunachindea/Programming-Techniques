package ro.tuc.pt.assignment1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Operations;
import model.Polynomial;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public Polynomial p1;
	public Polynomial p2;
	public Polynomial result1;
	public Polynomial result2;
	public String s1;
	public String s2;
	public String s3;
	public String s4;
	
	public void setUp() {
		 p1 = Polynomial.fromString("2x^3+7x^2+2x^1+9x^0");
		 p2 = Polynomial.fromString("2x^1+3x^0");
	}
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAddition()
    {
    	
		result1 = Operations.add(p1, p2);
		s1 = result1.toString();
		s2 = "2.0x^3 +7.0x^2 +4.0x +12.0 ";
		assertTrue(s1.equals(s2));
    }
    
    public void testSubtraction()
    {
    	
		result1 = Operations.substract(p1, p2);
		s1 = result1.toString();
		s2 = "2.0x^3 +7.0x^2 +6.0 ";
		assertTrue(s1.equals(s2));
    }
    
    public void testMultiplication()
    {
		result1 = Operations.multiply(p1, p2);
		s1 = result1.toString();
		s2 = "4.0x^4 +20.0x^3 +25.0x^2 +24.0x +27.0 ";
		assertTrue(s1.equals(s2));
    }
    public void testDivision()
    {
		result1 = Operations.divide(p1, p2)[0];
		s1 = result1.toString();
		result2 = Operations.divide(p1, p2)[1];
		s2 = result2.toString();
		s3 = "1.0x^2 +2.0x -2.0 ";
		s4 = "15.0 ";
		assertTrue(s1.equals(s3) && s2.equals(s4));
    }
    
    public void testIntegration()
    {
		result1 = Operations.integrate(p1);
		s1 = result1.toString();
		s2 = "0.5x^4 +2.3333333x^3 +1.0x^2 +9.0x ";
		assertTrue(s1.equals(s2));
    }
    public void testDifferentiation()
    {
		result1 = Operations.differentiate(p1);
		s1 = result1.toString();
		s2 = "6.0x^2 +14.0x +2.0 ";
		assertTrue(s1.equals(s2));
    }
    
      public void testDivisionByZero()
    {
    	p2 = Polynomial.fromString("0x^4");
		result1 = Operations.divide(p1, p2)[0];
		s1 = result1.toString();
		result2 = Operations.divide(p1, p2)[1];
		s2 = result2.toString();
		s3 = "0.0 ";
		s4 = "0.0 ";
		assertTrue(s1.equals(s3) && s2.equals(s4));
    }
    
    public void testWrongInput() {
    	p2 = Polynomial.fromString("abcde");
    	result1 = Operations.add(p1, p2);
    	s1 = result1.toString();
    	s2 = s1.toString();
    	assertTrue(s1.equals(s2));
    }
    
    public void testHighDegree() {
    	p2 = Polynomial.fromString("3x^9289876327387");
    	result1 = Operations.add(p1, p2);
    	s1 = result1.toString();
    	s2 = s1.toString();
    	assertTrue(s1.equals(s2));
    }
    
}
 