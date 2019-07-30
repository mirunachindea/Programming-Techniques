package presentationLayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class AdministratorGUI {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel dataPanel;
	private JPanel addPanel;
	private JPanel idPanel;
	private JPanel namePanel;
	private JPanel pricePanel;
	private JPanel quantityPanel;
	private JPanel compositionPanel;
	private JPanel buttonsPanel;
	private JPanel delPanel;
	private JPanel deletePanel;

	private JLabel l_addOrEdit;
	private JLabel l_id1;
	private JLabel l_name;
	private JLabel l_price;
	private JLabel l_quantity;
	private JLabel l_composition;
	private JLabel l_delete;
	private JLabel l_id2;

	private JTextField t_id1;
	private JTextField t_name;
	private JTextField t_price;
	private JTextField t_quantity;
	private JTextField[] t_composition;
	private JTextField t_id2;

	private JButton b_add;
	private JButton b_edit;
	private JButton b_delete;

	private JScrollPane scrollPane;

	private JTable table_menu;

	private Restaurant restaurant;

	public AdministratorGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		initialize();
	}

	private void initialize() {

		frame = new JFrame("Administrator");
		frame.setSize(1400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setBackground(new Color(139, 221, 239));

		scrollPane = new JScrollPane();
		scrollPane.setSize(800, 600);
		scrollPane.setVisible(true);
		scrollPane.setBackground(new Color(139, 221, 239));

		table_menu = new JTable();
		table_menu.setVisible(true);
		table_menu.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(table_menu);

		setMenuTable();

		l_addOrEdit = new JLabel("ADD OR EDIT PRODUCT");

		l_id1 = new JLabel("id");
		l_name = new JLabel("name");
		l_price = new JLabel("price");
		l_quantity = new JLabel("quantity");
		l_composition = new JLabel("composition");
		l_delete = new JLabel("DELETE PRODUCT");
		l_id2 = new JLabel("id");

		t_id1 = new JTextField(10);
		t_id2 = new JTextField(10);
		t_name = new JTextField(15);
		t_price = new JTextField(10);
		t_quantity = new JTextField(10);
		t_composition = new JTextField[6];
		for (int i = 0; i <= 5; i++) {
			t_composition[i] = new JTextField(5);
		}

		b_add = new JButton("Add");
		b_edit = new JButton("Edit");
		b_delete = new JButton("Delete");

		
		mainPanel = new JPanel();
		mainPanel.setSize(1400, 600);
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.setVisible(true);
		mainPanel.setBackground(new Color(139, 221, 239));

		dataPanel = new JPanel();
		dataPanel.setSize(600, 600);
		dataPanel.setLayout(new GridLayout(9, 1));
		dataPanel.setVisible(true);
		dataPanel.setBackground(new Color(139, 221, 239));

		addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout());
		addPanel.add(l_addOrEdit);
		addPanel.setBackground(new Color(139, 221, 239));
		
		idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout());
		idPanel.add(l_id1);
		idPanel.add(t_id1);
		idPanel.setVisible(true);
		idPanel.setBackground(new Color(139, 221, 239));

		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(l_name);
		namePanel.add(t_name);
		namePanel.setBackground(new Color(139, 221, 239));

		pricePanel = new JPanel();
		pricePanel.setLayout(new FlowLayout());
		pricePanel.add(l_price);
		pricePanel.add(t_price);
		pricePanel.setBackground(new Color(139, 221, 239));
		
		quantityPanel = new JPanel();
		quantityPanel.setLayout(new FlowLayout());
		quantityPanel.add(l_quantity);
		quantityPanel.add(t_quantity);
		quantityPanel.setBackground(new Color(139, 221, 239));

		compositionPanel = new JPanel();
		compositionPanel.setLayout(new FlowLayout());
		compositionPanel.add(l_composition);
		for (int i = 0; i <= 5; i++) {
			compositionPanel.add(t_composition[i]);
		}
		compositionPanel.setBackground(new Color(139, 221, 239));

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(b_add);
		buttonsPanel.add(b_edit);
		buttonsPanel.setBackground(new Color(139, 221, 239));

		delPanel = new JPanel();
		delPanel.setLayout(new FlowLayout());
		delPanel.add(l_delete);
		delPanel.setBackground(new Color(139, 221, 239));
		
		deletePanel = new JPanel();
		deletePanel.setLayout(new FlowLayout());
		deletePanel.add(l_id2);
		deletePanel.add(t_id2);
		deletePanel.add(b_delete);
		deletePanel.setBackground(new Color(139, 221, 239));

		dataPanel.add(addPanel);
		dataPanel.add(idPanel);
		dataPanel.add(namePanel);
		dataPanel.add(pricePanel);
		dataPanel.add(quantityPanel);
		dataPanel.add(compositionPanel);
		dataPanel.add(buttonsPanel);
		dataPanel.add(delPanel);
		dataPanel.add(deletePanel);
		dataPanel.setVisible(true);

		mainPanel.add(scrollPane);
		mainPanel.add(dataPanel);
		frame.add(mainPanel);
		
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				float price = 0;
				try {
					id = Integer.parseInt(t_id1.getText());
					price = Float.parseFloat(t_price.getText());
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}

				ArrayList<MenuItem> items = new ArrayList<MenuItem>();
				boolean composite = false;
				for (int i = 0; i <= 5; i++) {
					int id2 = 0;
					if (!t_composition[i].getText().equals("")) {
						composite = true;
						try {
							id2 = Integer.parseInt(t_composition[i].getText());
							for (MenuItem item : restaurant.getMenuItems()) {
								if(id2 == item.getId()) {
									items.add(item);
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							return;
						}
					}
				}
				if(composite) {
					restaurant.createMenuItem(id, t_name.getText(), price, t_quantity.getText(), items);
				}
				else {
					restaurant.createMenuItem(id, t_name.getText(), price, t_quantity.getText(), null);
				}
				setMenuTable();
				
			}
		});


	b_edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int id = 0;
			float price = 0;
			try {
				id = Integer.parseInt(t_id1.getText());
				price = Float.parseFloat(t_price.getText());
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}

			ArrayList<MenuItem> items = new ArrayList<MenuItem>();
			boolean composite = false;
			for (int i = 0; i <= 5; i++) {
				int id2 = 0;
				if (!t_composition[i].getText().equals("")) {
					composite = true;
					try {
						id2 = Integer.parseInt(t_composition[i].getText());
						for (MenuItem item : restaurant.getMenuItems()) {
							if(id2 == item.getId()) {
								items.add(item);
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						return;
					}
				}
			}
			if(composite) {
				restaurant.editMenuItem(id, t_name.getText(), price, t_quantity.getText(), items);
			}
			else {
				restaurant.editMenuItem(id, t_name.getText(), price, t_quantity.getText(), null);
			}
			setMenuTable();
			
		}
	});
	
	b_delete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int id = 0;
			try {
				id = Integer.parseInt(t_id2.getText());
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			restaurant.deleteMenuItem(id);
			setMenuTable();	
		}
	});



}
	private void setMenuTable() {
		String[] columns = { "id", "name", "price", "quantity", "composition" };
		String[][] rows = new String[40][5];
		int i = 0;
		for (MenuItem item : restaurant.getMenuItems()) {
			rows[i][0] = item.getId() + "";
			rows[i][1] = item.getName();
			rows[i][2] = item.computePrice() + "";
			rows[i][3] = item.getQuantity();
			String composition = new String("");
			// composite product
			if (item.getClass().toString().equals("class businessLayer.CompositeProduct")) {
				for (MenuItem item2 : item.getItems()) {
					if (item2.equals(item.getItems().get(item.getItems().size() - 1)))
						composition += item2.getName() + "";
					else
						composition += item2.getName() + ", ";
				}
			}
			rows[i][4] = composition;
			i++;
		}
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table_menu.setModel(model);
		table_menu.repaint();
	}

}
