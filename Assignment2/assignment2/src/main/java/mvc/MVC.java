package mvc;
import controller.Controller;
import model.Simulator;
import view.View;

public class MVC {

	public View view;
	public Simulator simulation;
	public Controller controller;
	
	public MVC() {
		this.initialize();
	}
	
	private void initialize() {
		View view = new View();
		Simulator simulation = new Simulator();
		@SuppressWarnings("unused")
		Controller controller = new Controller(view, simulation);
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MVC mvc = new MVC();
	}

}

