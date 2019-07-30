package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * View class
 */
public class View {

	// text fields
	private JTextField textPol1;
	private JTextField textPol2;
	private JTextField textResult;
	// buttons
	private JButton addBtn;
	private JButton subBtn;
	private JButton mulBtn;
	private JButton divBtn;
	private JButton difBtn;
	private JButton intBtn;
	private JButton clearPol1;
	private JButton clearPol2;
	private JButton clearResult;
	// labels
	private Label labelPol1;
	private Label labelPol2;
	private Label labelResult;
	// frames
	private JFrame mainFrame;
	// panels
	private JPanel panelPol1;
	private JPanel panelPol2;
	private JPanel panelResult;
	private JPanel panelButtons;
	
	// constructor which calls the initialize method
	public View(){
		this.initialize();
	}
	
	// this method initalizes the View class
	private void initialize() {
		
		// we set the components' text, dimensions and colours
		textPol1 = new JTextField(20);
		textPol1.setPreferredSize(new Dimension(20,25));
		textPol2 = new JTextField(20);
		textPol2.setPreferredSize(new Dimension(20,25));
		textResult = new JTextField(20);
		textResult.setPreferredSize(new Dimension(20,25));
		textResult.setEditable(false);
		
		addBtn = new JButton("Add polynomials");
		addBtn.setBackground(new Color(148, 209, 192));
		subBtn = new JButton("Subtract polynomials");
		subBtn.setBackground(new Color(148, 209, 192));
		mulBtn = new JButton("Multiply polynomials");
		mulBtn.setBackground(new Color(148, 209, 192));
		divBtn = new JButton("Divide polynomials");
		divBtn.setBackground(new Color(148, 209, 192));
		difBtn = new JButton("Differentiate polynomial 1");
		difBtn.setBackground(new Color(88, 196, 166));
		intBtn = new JButton("Integrate polynomial 1");
		intBtn.setBackground(new Color(88, 196, 166));
		clearPol1 = new JButton("Clear");
		clearPol1.setBackground(new Color(166, 237, 214));
		clearPol2 = new JButton("Clear");
		clearPol2.setBackground(new Color(166, 237, 214));
		clearResult = new JButton("Clear");
		clearResult.setBackground(new Color(166, 237, 214));
		
		labelPol1 = new Label("Polynomial 1:");
		labelPol1.setFont(new Font("Times Roman", Font.PLAIN, 14));
		labelPol2 = new Label("Polynomial 2:");
		labelPol2.setFont(new Font("Times Roman", Font.PLAIN, 14));
		labelResult = new Label("Result:");
		labelResult.setFont(new Font("Times Roman", Font.PLAIN, 14));
		
		
		mainFrame = new JFrame("Operations on polynomials");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(4,1));
		
		panelPol1 = new JPanel();
		panelPol1.setLayout(new FlowLayout());
		panelPol1.add(labelPol1);
		panelPol1.add(textPol1);
		panelPol1.add(clearPol1);
		panelPol1.setBackground(new Color(210, 242, 233));
	
		panelPol2 = new JPanel();
		panelPol2.setLayout(new FlowLayout());
		panelPol2.add(labelPol2);
		panelPol2.add(textPol2);
		panelPol2.add(clearPol2);
		panelPol2.setBackground(new Color(210, 242, 233));
		
		panelResult = new JPanel();
		panelResult.setLayout(new FlowLayout());
		panelResult.add(labelResult);
		panelResult.add(textResult);
		panelResult.add(clearResult);
		panelResult.setBackground(new Color(210, 242, 233));
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(3,3));
		panelButtons.setSize(new Dimension(50,50));
		panelButtons.add(addBtn);
		panelButtons.add(subBtn);
		panelButtons.add(mulBtn);
		panelButtons.add(divBtn);
		panelButtons.add(difBtn);
		panelButtons.add(intBtn);
		panelButtons.setBackground(new Color(210, 242, 233));
		
		mainFrame.add(panelPol1);
		mainFrame.add(panelPol2);
		mainFrame.add(panelResult);
		mainFrame.add(panelButtons);
		mainFrame.setVisible(true);	
		
	}
	
	// listener classes for buttons
	public void addClearListener1(ActionListener a) {
		clearPol1.addActionListener(a);
	}
	
	public void addClearListener2(ActionListener a) {
		clearPol2.addActionListener(a);
	}
	
	public void addClearListener3(ActionListener a) {
		clearResult.addActionListener(a);
	}
	
	public void addAdditionListener(ActionListener a) {
		addBtn.addActionListener(a);
	}
	
	public void addSubstractionListener(ActionListener a) {
		subBtn.addActionListener(a);
	}
	
	public void addMultiplyListener(ActionListener a) {
		mulBtn.addActionListener(a);
	}
	
	public void addDivideListener(ActionListener a) {
		divBtn.addActionListener(a);
	}
	
	public void addIntegrateListener(ActionListener a) {
		intBtn.addActionListener(a);
	}
	
	public void addDifferentiateListener(ActionListener a) {
		difBtn.addActionListener(a);
	}
	
	public void reset1() {
		textPol1.setText("");
	}
	
	public void reset2() {
		textPol2.setText("");
	}
	
	public void reset3() {
		textResult.setText("");
	}
	
	public String getUserInput1() {
		return textPol1.getText();
	}
	
	public String getUserInput2() {
		return textPol2.getText();
	}
	
	
	public void setResult(String result) {
		textResult.setText(result);
	}
	
}
