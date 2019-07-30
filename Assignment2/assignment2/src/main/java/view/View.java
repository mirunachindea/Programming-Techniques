package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * View class
 * @author Miruna
 *
 */
public class View{
	
	private Label l_customerTime;
	private Label l_serviceTime;
	private Label l_queueNb;
	private Label l_line1;
	private Label l_line2;
	private Label l_simulationInterval;
	private Label l_time;
	
	private JTextField t_minCT;
	private JTextField t_maxCT;
	private JTextField t_minST;
	private JTextField t_maxST;
	private JTextField t_queueNb;
	private JTextField t_simInt;
	
	private static JTextField t_time;
	private static JTextArea t_queues;
	private static JTextArea t_logger;
	
	private JButton b_ok;
	
	private JFrame dataFrame;
	private JFrame simulationFrame;
	
	private JPanel panel_CT;
	private JPanel panel_ST;
	private JPanel panel_Q;
	private JPanel panel_SI;
	private JPanel panel_B;
	private JPanel panel_time;
	private JPanel panel_SIM;
	private JPanel panel_queues;
	private JScrollPane pane_scroll;

	/**
	 * View constructor with no parameters
	 */
	public View() {
		this.initialize();
	}
	/**
	 * Method for View initialization
	 */
	private void initialize() {
		l_customerTime = new Label("Arriving time between customers");
		l_customerTime.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_serviceTime = new Label("Service time");
		l_serviceTime.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_queueNb = new Label("Number of queues");
		l_queueNb.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_line1 = new Label("-");
		l_line1.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_line2 = new Label("-");
		l_line2.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_simulationInterval = new Label("Simulation interval");
		l_simulationInterval.setFont(new Font("Times Roman", Font.PLAIN, 14));
		l_time = new Label("Time");
		l_time.setFont(new Font("Times Roman", Font.PLAIN, 14));
		
		t_minCT = new JTextField(5);
		t_maxCT = new JTextField(5);
		t_minST = new JTextField(5);
		t_maxST = new JTextField(5);
		t_queueNb = new JTextField(5);
		t_simInt = new JTextField(5);
		t_time = new JTextField(5);
		t_time.setEditable(false);
		t_queues = new JTextArea(20,20);
		t_queues.setEditable(false);
		t_queues.setBackground(new Color(213,200,250));
		t_queues.setFont(new Font("Times Roman", Font.PLAIN, 15));
		t_logger = new JTextArea(20,30);
		t_logger.setEditable(false);
		b_ok = new JButton("OK");
		b_ok.setMaximumSize(new Dimension(5,5));
		
		dataFrame = new JFrame("Introduce data");
		dataFrame.setSize(400, 400);
		dataFrame.setLayout(new GridLayout(5, 1));
		
		simulationFrame = new JFrame("Simulation");
		simulationFrame.setSize(800, 600);
		simulationFrame.setBackground(new Color(213,200,250));
		
		panel_CT = new JPanel();
		panel_CT.setLayout(new FlowLayout());
		panel_CT.setBackground(new Color(213,200,250));
		panel_CT.add(l_customerTime);
		panel_CT.add(t_minCT);
		panel_CT.add(l_line1);
		panel_CT.add(t_maxCT);
		
		panel_ST = new JPanel();
		panel_ST.setBackground(new Color(213,200,250));
		panel_ST.add(l_serviceTime);
		panel_ST.add(t_minST);
		panel_ST.add(l_line2);
		panel_ST.add(t_maxST);
		
		panel_Q = new JPanel();
		panel_Q.setLayout(new FlowLayout());
		panel_Q.setBackground(new Color(213,200,250));
		panel_Q.add(l_queueNb);
		panel_Q.add(t_queueNb);
		
		panel_SI = new JPanel();
		panel_SI.setLayout(new FlowLayout());
		panel_SI.setBackground(new Color(213,200,250));
		panel_SI.add(l_simulationInterval);
		panel_SI.add(t_simInt);
		
		panel_B = new JPanel();
		panel_B.setLayout(new FlowLayout());
		panel_B.setBackground(new Color(213,200,250));
		panel_B.add(b_ok);
		
		panel_time = new JPanel();
		panel_time.setPreferredSize(new Dimension(5,10));
		panel_time.setLayout(new FlowLayout());
		panel_time.setBackground(new Color(213,200,250));
		panel_time.add(l_time);
		panel_time.add(t_time);
		
		panel_queues = new JPanel();
		panel_queues.setLayout(new FlowLayout());
		panel_queues.setBackground(new Color(213,200,250));
		panel_queues.add(t_queues);
		
		pane_scroll = new JScrollPane(t_logger);
		panel_SIM = new JPanel();
		panel_SIM.setLayout(new GridLayout(3,1));
		panel_SIM.setBackground(new Color(213,200,250));
		panel_SIM.add(panel_time);
		panel_SIM.add(panel_queues);
		panel_SIM.add(pane_scroll);
		
		dataFrame.add(panel_CT);
		dataFrame.add(panel_ST);
		dataFrame.add(panel_Q);
		dataFrame.add(panel_SI);
		dataFrame.add(panel_B);
		dataFrame.setVisible(true);
		
		simulationFrame.add(panel_SIM);
		simulationFrame.setVisible(false);
	}
	
	/**
	 * Action Listener method for the "OK" button
	 * @param a action listener
	 */
	public void addOkBtnListener(ActionListener a) {
		b_ok.addActionListener(a);
	}
	/**
	 * Method for getting the min arriving time between customers from the gui
	 * @return min arriving time
	 */
	public int getMinArrivingTime() {
		int arrivingTime = -1;
		try {
			arrivingTime = Integer.parseInt(t_minCT.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return arrivingTime;
	}
	/**
	 * Method for getting the max arriving time between customers from the gui
	 * @return max arriving time
	 */
	public int getMaxArrivingTime() {
		int arrivingTime = -1;
		try {
			arrivingTime = Integer.parseInt(t_maxCT.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return arrivingTime;
	}
	/**
	 * Method for getting the min service time for a customer from the gui
	 * @return min service time
	 */
	public int getMinServiceTime() {
		int serviceTime = -1;
		try {
			serviceTime = Integer.parseInt(t_minST.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return serviceTime;
	}
	/**
	 * Method for getting the max service time for a customer from the gui
	 * @return max service time
	 */
	public int getMaxServiceTime() {
		int serviceTime = -1;
		try {
			serviceTime = Integer.parseInt(t_maxST.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return serviceTime;
	}
	/**
	 * Method for getting the number of queues from the gui
	 * @return number of queues
	 */
	public int getQueueNb() {
		int queueNb = -1;
		try {
			queueNb = Integer.parseInt(t_queueNb.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return queueNb;
	}
	/**
	 * Method for getting the simulation time interval from the gui
	 * @return simulation time interval
	 */
	public int getSimulationInterval() {
		int simulationTime = -1;
		try {
			simulationTime = Integer.parseInt(t_simInt.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Incorrect input!");
		}
		return simulationTime;
	}
	/**
	 * Method for starting the simulation
	 */
	public void startSimulation() {
		dataFrame.setVisible(false);
		simulationFrame.setVisible(true);
	}
	/**
	 * Method for displaying the current time
	 * @param ctTime current time
	 */
	public static void setCtTime(int ctTime) {
		t_time.setText(ctTime + "");
	}
	/**
	 * Method for displaying the queue evolution
	 * @param queues queue evolution
	 */
	public static void setQueues(String queues) {
		t_queues.setText(queues);
	}
	/**
	 * Method for displaying the logger file
	 * @param logger logger file
	 */
	public static void setLogger(String logger) {
		t_logger.append(logger);
	}
}
