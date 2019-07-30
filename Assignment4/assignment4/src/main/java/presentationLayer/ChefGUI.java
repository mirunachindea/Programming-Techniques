package presentationLayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import businessLayer.MenuItem;
import businessLayer.Observer;
import businessLayer.Order;

@SuppressWarnings("serial")
public class ChefGUI implements Observer, java.io.Serializable{

	
	private JFrame frame;
	private JPanel panel;
	private JTextArea t_notify;
	
	public ChefGUI() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame("Chef");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		frame.setBackground(new Color(242, 174, 96));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setVisible(true);
		panel.setBackground(new Color(242, 174, 96));
		
		t_notify = new JTextArea(30, 40);
		t_notify.setVisible(true);
		t_notify.setEditable(false);
		t_notify.setBackground(new Color(242, 174, 96));
		t_notify.setFont(new Font("Times Roman", Font.PLAIN, 15));
		
		panel.add(t_notify);
		frame.add(panel);
	}

	public void update(Order order, HashSet<MenuItem> items) {
		StringBuilder notification = new StringBuilder("");
		notification.append("Order " + order.getId() + ": ");
		for(MenuItem item : items) {
			notification.append(item.getName() + ", ");
		}
		t_notify.append(notification.substring(0, notification.length() - 2));
		t_notify.append("\n");
		t_notify.repaint();
	}

	
}
