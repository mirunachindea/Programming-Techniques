package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Simulator;
import view.View;

/**
 * Controller class
 * @author Miruna
 *
 */
public class Controller {

	private View view;
	private Simulator simulation;
	/**
	 * Controller constructor
	 * @param view view component
	 * @param simulation model component
	 */
	public Controller(View view, Simulator simulation){
		this.view = view;	
		this.simulation = simulation;
		view.addOkBtnListener(new OkBtnListener());
	}
	
	/**
	 * Listener class for the "OK" button
	 * @author Miruna
	 *
	 */
	 class OkBtnListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
        	int minArrivingTime = view.getMinArrivingTime();
        	int maxArrivingTime = view.getMaxArrivingTime();
        	int minServiceTime = view.getMinServiceTime();
        	int maxServiceTime = view.getMaxServiceTime();
        	int queueNb = view.getQueueNb();
        	int simulationInterval = view.getSimulationInterval();
        	if(minArrivingTime > 0 && maxArrivingTime > 0 && minServiceTime > 0 && maxServiceTime > 0 && queueNb > 0 && simulationInterval > 0) {
        		simulation.setArrivalTimes(minArrivingTime, maxArrivingTime);
        		simulation.setServiceTimes(minServiceTime, maxServiceTime);
        		simulation.setQueueNb(queueNb);
        		simulation.setSimulationInterval(simulationInterval);
        		simulation.start();
        		view.startSimulation();
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Inputs cannot be <= 0!" );
        	}
        }
    }
}
