package mvc;
import controller.Controller;
import model.*;
import view.View;

/*
 * Model-View-Controller class
 * Has as attributes model, view and controller
 */
public class MVC {

	public View view;
	public Model model;
	public Controller controller;
	
	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		@SuppressWarnings("unused")
		Controller controller = new Controller(view,model);
	}

}
