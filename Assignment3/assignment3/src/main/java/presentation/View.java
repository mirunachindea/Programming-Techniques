package presentation;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Product;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

/**
 * View class
 * @author Miruna
 *
 */
public class View {

	private JFrame frame;
	public JTable table_clients;
	private JTextField t_clientName1;
	private JTextField t_clientEmail1;
	private JTextField t_clientAddr1;
	private JTextField t_clientTel1;
	private JTextField t_clientName2;
	private JTextField t_clientEmail2;
	private JTextField t_clientAddr2;
	private JTextField t_clientTel2;
	private JTextField t_clientEdit;
	private JTextField t_cId2;
	public JTable table_products;
	private JTextField t_prodEdit;
	private JTextField t_prodName1;
	private JTextField t_prodCat1;
	private JTextField t_prodBrand1;
	private JTextField textField_6;
	private JTextField t_prodName2;
	private JTextField t_prodCat2;
	private JTextField t_prodBrand2;
	private JTextField t_prodPrice2;
	private JTextField t_prodId2;
	private JTextField t_prodNb1;
	private JTextField t_prodNb2;
	private JTextField t_quantity;

	/**
	 * View constructor
	 */
	public View() {
		initialize();
	}

	/**
	 * Method for initializing the view and adding listeners
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1104, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1086, 617);
		frame.getContentPane().add(tabbedPane);

		JPanel tab_client = new JPanel();
		tabbedPane.addTab("Clients", null, tab_client, null);
		tab_client.setLayout(null);

		t_clientName1 = new JTextField();
		t_clientName1.setBounds(675, 141, 116, 22);
		tab_client.add(t_clientName1);
		t_clientName1.setColumns(10);

		t_clientEmail1 = new JTextField();
		t_clientEmail1.setBounds(675, 176, 116, 22);
		tab_client.add(t_clientEmail1);
		t_clientEmail1.setColumns(10);

		t_clientAddr1 = new JTextField();
		t_clientAddr1.setBounds(675, 211, 116, 22);
		tab_client.add(t_clientAddr1);
		t_clientAddr1.setColumns(10);

		t_clientTel1 = new JTextField();
		t_clientTel1.setBounds(675, 246, 116, 22);
		tab_client.add(t_clientTel1);
		t_clientTel1.setColumns(10);

		t_clientName2 = new JTextField();
		t_clientName2.setColumns(10);
		t_clientName2.setBounds(930, 141, 116, 22);
		tab_client.add(t_clientName2);

		t_clientEmail2 = new JTextField();
		t_clientEmail2.setColumns(10);
		t_clientEmail2.setBounds(930, 176, 116, 22);
		tab_client.add(t_clientEmail2);

		t_clientAddr2 = new JTextField();
		t_clientAddr2.setColumns(10);
		t_clientAddr2.setBounds(930, 211, 116, 22);
		tab_client.add(t_clientAddr2);

		t_clientTel2 = new JTextField();
		t_clientTel2.setColumns(10);
		t_clientTel2.setBounds(930, 246, 116, 22);
		tab_client.add(t_clientTel2);

		t_clientEdit = new JTextField();
		t_clientEdit.setBounds(930, 106, 116, 22);
		tab_client.add(t_clientEdit);
		t_clientEdit.setColumns(10);

		JButton btn_cEdit = new JButton("Edit");
		btn_cEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (t_clientEdit.getText().equals("") || t_clientName2.getText().equals("")
						|| t_clientEmail2.getText().equals("") || t_clientAddr2.getText().equals("")
						|| t_clientTel2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are mandatory!");
					return;
				}
				int id = 0;
				try {
					id = Integer.parseInt(t_clientEdit.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Id must be an integer!");
					return;
				}
				Controller.getClientUpdate(id, t_clientName2.getText(), t_clientEmail2.getText(),
						t_clientAddr2.getText(), t_clientTel2.getText());
				Controller.setTableClients();
			}
		});
		btn_cEdit.setBounds(954, 286, 71, 25);
		tab_client.add(btn_cEdit);

		JButton btn_cAdd = new JButton("Add");
		btn_cAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (t_clientName1.getText().equals("") || t_clientEmail1.getText().equals("")
						|| t_clientAddr1.getText().equals("") || t_clientTel1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are mandatory!");
					return;
				}
				Controller.getClientAdd(t_clientName1.getText(), t_clientEmail1.getText(), t_clientAddr1.getText(),
						t_clientTel1.getText());
				Controller.setTableClients();
			}
		});

		btn_cAdd.setBounds(701, 286, 71, 25);
		tab_client.add(btn_cAdd);

		JLabel l_clientEdit = new JLabel("Edit client");
		l_clientEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_clientEdit.setBounds(904, 52, 131, 16);
		tab_client.add(l_clientEdit);

		JLabel l_clientAdd = new JLabel("Add a new client");
		l_clientAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_clientAdd.setBounds(646, 52, 161, 16);
		tab_client.add(l_clientAdd);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(892, 112, 38, 16);
		tab_client.add(lblId);

		JLabel l_cName2 = new JLabel("Name");
		l_cName2.setBounds(889, 144, 38, 16);
		tab_client.add(l_cName2);

		JLabel l_cEmail2 = new JLabel("E-mail");
		l_cEmail2.setBounds(880, 179, 38, 16);
		tab_client.add(l_cEmail2);

		JLabel l_cAddr2 = new JLabel("Address");
		l_cAddr2.setBounds(874, 214, 56, 16);
		tab_client.add(l_cAddr2);

		JLabel l_cTel2 = new JLabel("Telephone");
		l_cTel2.setBounds(859, 249, 71, 16);
		tab_client.add(l_cTel2);

		JLabel l_cName1 = new JLabel("Name");
		l_cName1.setBounds(625, 144, 44, 16);
		tab_client.add(l_cName1);

		JLabel l_cEmail1 = new JLabel("E-mail");
		l_cEmail1.setBounds(625, 179, 56, 16);
		tab_client.add(l_cEmail1);

		JLabel l_cAddr1 = new JLabel("Address");
		l_cAddr1.setBounds(613, 214, 56, 16);
		tab_client.add(l_cAddr1);

		JLabel l_cTel1 = new JLabel("Telephone");
		l_cTel1.setBounds(610, 249, 71, 16);
		tab_client.add(l_cTel1);

		JLabel l_clientDel = new JLabel("Delete client");
		l_clientDel.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_clientDel.setBounds(757, 374, 161, 16);
		tab_client.add(l_clientDel);

		JLabel l_cId = new JLabel("Id");
		l_cId.setBounds(740, 423, 44, 16);
		tab_client.add(l_cId);

		t_cId2 = new JTextField();
		t_cId2.setColumns(10);
		t_cId2.setBounds(761, 420, 116, 22);
		tab_client.add(t_cId2);

		JButton btn_cDel = new JButton("Delete");
		btn_cDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				try {
					id = Integer.parseInt(t_cId2.getText());
					System.out.println(id);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Id must be an integer!");
					return;
				}
				Controller.getClientDel(id);
				Controller.setTableClients();
			}
		});
		btn_cDel.setBounds(904, 419, 99, 25);
		tab_client.add(btn_cDel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 40, 589, 498);
		tab_client.add(scrollPane);

		table_clients = new JTable();
		table_clients.setVisible(true);
		table_clients.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(table_clients);

		JPanel tab_product = new JPanel();
		tabbedPane.addTab("Products", null, tab_product, null);
		tab_product.setLayout(null);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(12, 40, 589, 498);
		tab_product.add(scrollPane2);

		table_products = new JTable();
		table_products.setBounds(12, 52, 572, 445);
		scrollPane2.setViewportView(table_products);

		JLabel l_prodAdd = new JLabel("Add a new product");
		l_prodAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_prodAdd.setBounds(646, 54, 176, 16);

		JLabel l_prodEdit = new JLabel("Edit product");
		l_prodEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_prodEdit.setBounds(904, 54, 131, 16);
		tab_product.add(l_prodEdit);

		t_prodEdit = new JTextField();
		t_prodEdit.setColumns(10);
		t_prodEdit.setBounds(930, 111, 116, 22);
		tab_product.add(t_prodEdit);

		t_prodName1 = new JTextField();
		t_prodName1.setColumns(10);
		t_prodName1.setBounds(675, 143, 116, 22);
		tab_product.add(t_prodName1);

		t_prodCat1 = new JTextField();
		t_prodCat1.setColumns(10);
		t_prodCat1.setBounds(675, 178, 116, 22);
		tab_product.add(t_prodCat1);

		JLabel l_pName1 = new JLabel("Name");
		l_pName1.setBounds(625, 146, 44, 16);
		tab_product.add(l_pName1);

		JLabel l_pCat1 = new JLabel("Category");
		l_pCat1.setBounds(613, 181, 56, 16);
		tab_product.add(l_pCat1);

		JLabel l_pBrand1 = new JLabel("Brand");
		l_pBrand1.setBounds(631, 215, 38, 16);
		tab_product.add(l_pBrand1);

		JLabel t_prodPrice1 = new JLabel("Price");
		t_prodPrice1.setBounds(636, 251, 71, 16);
		tab_product.add(t_prodPrice1);

		t_prodBrand1 = new JTextField();
		t_prodBrand1.setColumns(10);
		t_prodBrand1.setBounds(675, 213, 116, 22);
		tab_product.add(t_prodBrand1);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(675, 248, 116, 22);
		tab_product.add(textField_6);

		JButton btn_pAdd = new JButton("Add");
		btn_pAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double price = 0;
				int nbinstock = 0;
				try {
					price = Double.parseDouble(textField_6.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid price!");
					return;
				}
				try {
					nbinstock = Integer.parseInt(t_prodNb1.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid number!");
					return;
				}
				Controller.getProductAdd(t_prodName1.getText(), t_prodCat1.getText(), t_prodBrand1.getText(), price,
						nbinstock);
				Controller.setTableProducts();
			}
		});
		btn_pAdd.setBounds(701, 312, 71, 25);
		tab_product.add(btn_pAdd);

		t_prodName2 = new JTextField();
		t_prodName2.setColumns(10);
		t_prodName2.setBounds(930, 143, 116, 22);
		tab_product.add(t_prodName2);

		t_prodCat2 = new JTextField();
		t_prodCat2.setColumns(10);
		t_prodCat2.setBounds(930, 178, 116, 22);
		tab_product.add(t_prodCat2);

		t_prodBrand2 = new JTextField();
		t_prodBrand2.setColumns(10);
		t_prodBrand2.setBounds(930, 213, 116, 22);
		tab_product.add(t_prodBrand2);

		t_prodPrice2 = new JTextField();
		t_prodPrice2.setColumns(10);
		t_prodPrice2.setBounds(930, 248, 116, 22);
		tab_product.add(t_prodPrice2);

		JLabel l_pName2 = new JLabel("Name");
		l_pName2.setBounds(889, 146, 38, 16);
		tab_product.add(l_pName2);

		JLabel l_pCat2 = new JLabel("Category");
		l_pCat2.setBounds(868, 181, 59, 16);
		tab_product.add(l_pCat2);

		JLabel l_pBrand2 = new JLabel("Brand");
		l_pBrand2.setBounds(883, 215, 44, 16);
		tab_product.add(l_pBrand2);

		JLabel l_pPrice2 = new JLabel("Price");
		l_pPrice2.setBounds(893, 251, 44, 16);
		tab_product.add(l_pPrice2);

		JButton btn_pEdit = new JButton("Edit");
		btn_pEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				double price = 0;
				int nbinstock = 0;
				try {
					id = Integer.parseInt(t_prodEdit.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Id must be an integer!");
					return;
				}
				try {
					price = Double.parseDouble(t_prodPrice2.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid price!");
					return;
				}
				try {
					nbinstock = Integer.parseInt(t_prodNb2.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid number!");
					return;
				}
				Controller.getProdUpdate(id, t_prodName2.getText(), t_prodCat2.getText(), t_prodBrand2.getText(), price,
						nbinstock);
				Controller.setTableProducts();

			}
		});
		btn_pEdit.setBounds(964, 312, 71, 25);
		tab_product.add(btn_pEdit);

		JLabel l_prodDel = new JLabel("Delete product");
		l_prodDel.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_prodDel.setBounds(757, 376, 161, 16);
		tab_product.add(l_prodDel);

		t_prodId2 = new JTextField();
		t_prodId2.setColumns(10);
		t_prodId2.setBounds(761, 422, 116, 22);
		tab_product.add(t_prodId2);

		JLabel l_pId = new JLabel("Id");
		l_pId.setBounds(737, 425, 44, 16);
		tab_product.add(l_pId);

		JButton btn_pDel = new JButton("Delete");
		btn_pDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				try {
					id = Integer.parseInt(t_prodId2.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Id must be an integer!");
					return;
				}
				Controller.getProductDel(id);
				Controller.setTableProducts();
			}
		});
		btn_pDel.setBounds(904, 421, 116, 25);
		tab_product.add(btn_pDel);

		t_prodNb1 = new JTextField();
		t_prodNb1.setColumns(10);
		t_prodNb1.setBounds(675, 280, 116, 22);
		tab_product.add(t_prodNb1);

		t_prodNb2 = new JTextField();
		t_prodNb2.setColumns(10);
		t_prodNb2.setBounds(930, 280, 116, 22);
		tab_product.add(t_prodNb2);

		JLabel l_pNb1 = new JLabel("Nb in stock");
		l_pNb1.setBounds(609, 283, 71, 16);
		tab_product.add(l_pNb1);

		JLabel l_pNb2 = new JLabel("Nb in stock");
		l_pNb2.setBounds(856, 283, 71, 16);
		tab_product.add(l_pNb2);

		JLabel l_pIdEdit = new JLabel("Id");
		l_pIdEdit.setBounds(904, 114, 44, 16);
		tab_product.add(l_pIdEdit);

		JLabel lblAddANew = new JLabel("Add a new product");
		lblAddANew.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddANew.setBounds(647, 54, 177, 16);
		tab_product.add(lblAddANew);

		JPanel tab_order = new JPanel();
		tabbedPane.addTab("Orders", null, tab_order, null);
		tab_order.setLayout(null);

		JLabel l_newOrder = new JLabel("Make a new order");
		l_newOrder.setFont(new Font("Tahoma", Font.BOLD, 18));
		l_newOrder.setBounds(443, 50, 176, 16);
		tab_order.add(l_newOrder);

		JLabel l_clientEmail = new JLabel("Client e-mail");
		l_clientEmail.setBounds(417, 122, 87, 16);
		tab_order.add(l_clientEmail);

		@SuppressWarnings("rawtypes")
		final JComboBox box_client = new JComboBox();
		box_client.setBounds(501, 119, 253, 22);
		tab_order.add(box_client);

		ArrayList<Client> clients = ClientDAO.findAll();
		for (Client client : clients) {
			box_client.addItem(client.getEmail());
		}

		@SuppressWarnings("rawtypes")
		final JComboBox box_product = new JComboBox();
		box_product.setBounds(501, 166, 253, 22);
		tab_order.add(box_product);

		ArrayList<Product> products = ProductDAO.findAll();
		for (Product product : products) {
			box_product.addItem(product.getName());
		}

		JLabel l_productName = new JLabel("Product name");
		l_productName.setBounds(410, 169, 79, 16);
		tab_order.add(l_productName);

		JLabel l_quantity = new JLabel("Quantity");
		l_quantity.setBounds(433, 213, 56, 16);
		tab_order.add(l_quantity);

		t_quantity = new JTextField();
		t_quantity.setBounds(501, 210, 107, 22);
		tab_order.add(t_quantity);
		t_quantity.setColumns(10);

		JButton btn_orderAdd = new JButton("Add order");
		btn_orderAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int quantity = 0;
				try {
					quantity = Integer.parseInt(t_quantity.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Quantity must be an integer!");
					return;
				}

				String email = box_client.getSelectedItem().toString();
				String name = box_product.getSelectedItem().toString();

				Controller.getOrderAdd(email, name, quantity);
				Controller.setTableProducts();
			}
		});
		btn_orderAdd.setBounds(464, 293, 97, 25);
		tab_order.add(btn_orderAdd);
	}
}