package controller;
import java.awt.event.*;

import model.*;
import view.View;

public class Controller {

	private View view;
	private Model model;
	
	/*
	 *  Constructor for Controller class
	 */
	public Controller(View view, Model model){
		this.view = view;	
		this.model = model;
		view.addClearListener1(new ClearListener1());
		view.addClearListener2(new ClearListener2());
		view.addClearListener3(new ClearListener3());
		view.addAdditionListener(new AdditionListener());
		view.addSubstractionListener(new SubstractionListener());
		view.addMultiplyListener(new MultiplyListener());
		view.addDivideListener(new DivideListener());
		view.addIntegrateListener(new IntegrateListener());
		view.addDifferentiateListener(new DifferentiateListener());
	}
	
	/*
	 * Listener classes for "Clear" buttons
	 */
	
	class ClearListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.reset1();
        }
	}
    class ClearListener2 implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            view.reset2();
        }
    }
     class ClearListener3 implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            view.reset3();
         }
     }
     
     // Listener class for "Add" button
     class AdditionListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 String pol2Text = view.getUserInput2();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 Polynomial p2 = Polynomial.fromString(pol2Text);
    		 model.setPol1(p1);
    		 model.setPol2(p2);
    		 Polynomial result = model.getSum();
    		 view.setResult(result.toString());
    	 }
     }
     
     // Listener class for "Subtract" button
     class SubstractionListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 String pol2Text = view.getUserInput2();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 Polynomial p2 = Polynomial.fromString(pol2Text);
    		 model.setPol1(p1);
    		 model.setPol2(p2);
    		 Polynomial result = model.getDif();
    		 view.setResult(result.toString());
    	 }
     }
     
     // Listener class for "Multiply" button
     class MultiplyListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 String pol2Text = view.getUserInput2();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 Polynomial p2 = Polynomial.fromString(pol2Text);
    		 model.setPol1(p1);
    		 model.setPol2(p2);
    		 Polynomial result = model.getMul();
    		 view.setResult(result.toString());
    	 }
     }
     
     // Listener class for "Divide" button
     class DivideListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 String pol2Text = view.getUserInput2();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 Polynomial p2 = Polynomial.fromString(pol2Text);
    		 model.setPol1(p1);
    		 model.setPol2(p2);
    		 view.setResult(model.getDiv());
    	 }
     }
     
     // Listener class for "Integrate" button
     class IntegrateListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 model.setPol1(p1);
    		 Polynomial result = model.getIntegrate();
    		 view.setResult(result.toString());
    	 }
     }
     
     // Listener class for "Differentiate" button
     class DifferentiateListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 String pol1Text = view.getUserInput1();
    		 Polynomial p1 = Polynomial.fromString(pol1Text);
    		 model.setPol1(p1);
    		 Polynomial result = model.getDifferentiate();
    		 view.setResult(result.toString());
    	 }
     }
}
