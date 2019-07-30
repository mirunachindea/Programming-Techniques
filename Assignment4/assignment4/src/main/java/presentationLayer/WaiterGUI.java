package presentationLayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;

public class WaiterGUI {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel dataPanel;
	private JPanel addPanel;
	private JPanel idPanel1;
	private JPanel tablePanel;
	private JPanel itemsPanel;
	private JPanel buttonPanel;
	private JPanel billPanel;
	private JPanel idPanel2;

	private JLabel l_add;
	private JLabel l_id1;
	private JLabel l_table;
	private JLabel l_items;
	private JLabel l_bill;
	private JLabel l_id2;

	private JTextField t_id1;
	private JTextField t_table;
	private JTextField[] t_items;
	private JTextField t_id2;

	private JButton b_add;
	private JButton b_bill;

	private JScrollPane scrollPane;

	private JTable table_order;

	private Restaurant restaurant;

	public WaiterGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		initialize(restaurant);
	}

	private void initialize(final Restaurant restaurant) {

		frame = new JFrame("Waiter");
		frame.setSize(1400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setBackground(new Color(139, 239, 190));

		scrollPane = new JScrollPane();
		scrollPane.setSize(800, 600);
		scrollPane.setVisible(true);
		scrollPane.setBackground(new Color(139, 239, 190));

		table_order = new JTable();
		table_order.setVisible(true);
		table_order.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(table_order);

		setOrderTable();

		l_add = new JLabel("ADD A NEW ORDER");
		l_id1 = new JLabel("id");
		l_table = new JLabel("table");
		l_items = new JLabel("products");
		l_bill = new JLabel("GENERATE BILL");
		l_id2 = new JLabel("id");

		t_id1 = new JTextField(10);
		t_id2 = new JTextField(10);
		t_table = new JTextField(10);
		t_items = new JTextField[10];
		for (int i = 0; i <= 9; i++) {
			t_items[i] = new JTextField(5);
		}

		b_add = new JButton("Add");
		b_bill = new JButton("Get bill");

		mainPanel = new JPanel();
		mainPanel.setSize(1400, 600);
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.setVisible(true);
		mainPanel.setBackground(new Color(139, 239, 190));

		dataPanel = new JPanel();
		dataPanel.setSize(600, 600);
		dataPanel.setLayout(new GridLayout(7, 1));
		dataPanel.setVisible(true);
		dataPanel.setBackground(new Color(139, 239, 190));

		addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout());
		addPanel.add(l_add);
		addPanel.setBackground(new Color(139, 239, 190));

		idPanel1 = new JPanel();
		idPanel1.setLayout(new FlowLayout());
		idPanel1.add(l_id1);
		idPanel1.add(t_id1);
		idPanel1.setVisible(true);
		idPanel1.setBackground(new Color(139, 239, 190));

		tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout());
		tablePanel.add(l_table);
		tablePanel.add(t_table);
		tablePanel.setBackground(new Color(139, 239, 190));

		itemsPanel = new JPanel();
		itemsPanel.setLayout(new FlowLayout());
		itemsPanel.add(l_items);
		for (int i = 0; i <= 9; i++) {
			itemsPanel.add(t_items[i]);
		}
		itemsPanel.setBackground(new Color(139, 239, 190));

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(b_add);
		buttonPanel.setBackground(new Color(139, 239, 190));
		
		billPanel = new JPanel();
		billPanel.setLayout(new FlowLayout());
		billPanel.add(l_bill);
		billPanel.setBackground(new Color(139, 239, 190));

		idPanel2 = new JPanel();
		idPanel2.setLayout(new FlowLayout());
		idPanel2.add(l_id2);
		idPanel2.add(t_id2);
		idPanel2.add(b_bill);
		idPanel2.setBackground(new Color(139, 239, 190));

		dataPanel.add(addPanel);
		dataPanel.add(idPanel1);
		dataPanel.add(tablePanel);
		dataPanel.add(itemsPanel);
		dataPanel.add(buttonPanel);
		dataPanel.add(billPanel);
		dataPanel.add(idPanel2);
		dataPanel.setVisible(true);

		mainPanel.add(scrollPane);
		mainPanel.add(dataPanel);
		frame.add(mainPanel);

		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				int table = 0;
				try {
					id = Integer.parseInt(t_id1.getText());
					table = Integer.parseInt(t_table.getText());
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				Order order = new Order(id, table);
				HashSet<MenuItem> items = new HashSet<MenuItem>();
				for (int i = 0; i <= 9; i++) {
					int itemId = 0;
					if (!t_items[i].getText().equals("")) {
						try {
							itemId = Integer.parseInt(t_items[i].getText());
							items.add(restaurant.getById(itemId));

						} catch (NumberFormatException ex) {
							ex.printStackTrace();
						}
					}
				}
				restaurant.createOrder(order, items);
				setOrderTable();
			}
		});
		
		b_bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				try {
					id = Integer.parseInt(t_id2.getText());
					
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				restaurant.generateBill(id);
			}
		});
	}

	private void setOrderTable() {
		String[] columns = { "id", "table", "date", "products" };
		String[][] rows = new String[40][4];
		int i = 0;
		for (Map.Entry<Order, HashSet<MenuItem>> order : restaurant.getOrders().entrySet()) {
			rows[i][0] = order.getKey().getId() + "";
			rows[i][1] = order.getKey().getTable() + "";
			rows[i][2] = order.getKey().getDate() + "";

			String items = new String("");
			for (MenuItem item : order.getValue()) {
				items += item.getName() + ", ";
			}

			items = items.substring(0, items.length() - 2);
			rows[i][3] = items;
			i++;
		}
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		table_order.setModel(model);
		table_order.repaint();
	}

}
