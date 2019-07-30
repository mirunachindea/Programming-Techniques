package presentation;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import businessLayer.EmailValidator;
import businessLayer.OrderValidator;
import businessLayer.QuantityValidator;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Model;
import model.Order;
import model.Product;
/**
 * Controller class
 * @author Miruna
 * 
 */
public class Controller {
	private static Model model;
	private static View view;
	/**
	 * Controller constructor
	 * @param view view
	 * @param model model
	 */
	public Controller(View view, Model model) {
		Controller.model = model;
		Controller.view = view;
		setTableClients();
		setTableProducts();
	}
	/**
	 * Method for getting the fields of an object in order to set table's header
	 * @param object object
	 * @return array of strings representing object's fields
	 */
	public static String[] getColumns(Object object) {
		int i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			i++;
		}
		String[] fields = new String[i];
		i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			fields[i] = field.getName();
			i++;
		}
		return fields;
	}
	/**
	 * Method for getting the values of an object's fields
	 * @param object object
	 * @return an array of strings representing object's fields' values
	 */
	private static String[] getRow(Object object) {
		int length = getColumns(object).length;
		String[] rows = new String[length];
		int i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(object);
				rows[i] = value.toString();
				i++;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	/**
	 * Method for getting the field values from a list of objects
	 * @param object list of objects
	 * @return a matrix of strings representing the list's fields' values
	 */
	private static <T> String[][] getRows(ArrayList<T> object) {
		String[][] rows = new String[20][7];
		int i = 0;
		for (T obj : object) {
			for (int j = 0; j < getColumns(obj).length; j++) {
				rows[i][j] = getRow(obj)[j];
			}
			i++;
		}
		return rows;
	}
	/**
	 * Method that uses reflection to generate the header of a table and then populate it
	 * @param object list of objects to populate the table
	 * @return table model 
	 */
	private static <T> DefaultTableModel createTable(ArrayList<T> object){
		String[] columns = getColumns(object.get(0));
		String[][] rows = getRows(object);
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}
	/**
	 * Method for creating the table of clients
	 */
	public static void setTableClients() {
		ArrayList<Client> clients = model.getClients();
		DefaultTableModel model = createTable(clients);
		view.table_clients.setModel(model);
		view.table_clients.repaint();
	}
	/**
	 * Method for creating the table of products
	 */
	public static void setTableProducts() {
		ArrayList<Product> products = model.getProducts();
		DefaultTableModel model = createTable(products);
		view.table_products.setModel(model);
		view.table_products.repaint();
	}
	/**
	 * Method for getting the values introduced in the text fields of client for inserting a new client and inserting the client
	 * @param name client name
	 * @param email client email
	 * @param address client address
	 * @param telephone client telephone number
	 */
	public static void getClientAdd(String name, String email, String address, String telephone) {
		Client client = new Client(0, name, email, address, telephone);
		EmailValidator ev = new EmailValidator();
		if (ev.validate(client) == 0) {
			JOptionPane.showMessageDialog(null, "Invalid e-mail!");
			return;
		}
		ClientDAO.insert(client);
		Client newClient = ClientDAO.findByEmail(client.getEmail());
		client.setId(newClient.getId());
		Model.clients.add(client);

	}
	/**
	 * Method for getting the values introduced in the text fields of client for updating a client and updating the client
	 * @param id client id
	 * @param name client name
	 * @param email client email
	 * @param address client address
	 * @param telephone client telephone number
	 */
	public static void getClientUpdate(int id, String name, String email, String address, String telephone) {
		Client client = new Client(id, name, email, address, telephone);
		EmailValidator ev = new EmailValidator();
		if (ev.validate(client) == 0) {
			JOptionPane.showMessageDialog(null, "Invalid e-mail!");
			return;
		}
		ClientDAO.update(client);
		for (Client c : Model.clients) {
			if (c.getId() == client.getId()) {
				c.setName(client.getName());
				c.setEmail(client.getEmail());
				c.setAddress(client.getAddress());
				c.setTelephone(client.getTelephone());
			}
		}
	}
	/**
	 * Method for getting id of the client to delete from the text field and deleting the client
	 * @param id client id
	 */
	public static void getClientDel(int id) {
		int found = ClientDAO.delete(id);
		for (Client c : Model.clients) {
			if (c.getId() == id) {
				Model.clients.remove(c);
			}
		}
		if (found == -1) {
			JOptionPane.showMessageDialog(null, "There is no client with id " + id + "!");
		}
	}
	/**
	 * Method for getting the values introduced in the text fields of product for inserting a new product and inserting the product
	 * @param name product name
	 * @param category product category
	 * @param brand product brand
	 * @param price product price
	 * @param nbinstock product stock number
	 */
	public static void getProductAdd(String name, String category, String brand, double price, int nbinstock) {
		Product product = new Product(0, name, category, brand, price, nbinstock);
		QuantityValidator qv = new QuantityValidator();
		if (qv.validate(product) == 0) {
			JOptionPane.showMessageDialog(null, "Price and stock number cannot be smaller than 0!");
			return;
		}
		ProductDAO.insert(product);
		Product newProduct = ProductDAO.findByName(product.getName());
		product.setId(newProduct.getId());
		Model.products.add(product);
	}
	/**
	 * Method for getting id of the product to delete from the text field and deleting the product
	 * @param id product id
	 */
	public static void getProductDel(int id) {
		ProductDAO.delete(id);
		for (Product p : Model.products) {
			if (p.getId() == id) {
				Model.products.remove(p);
			}
		}
	}
	/**
	 * Method for getting the values introduced in the text fields of product for updating a product and updating the product
	 * @param id product id
	 * @param name product name
	 * @param category product category
	 * @param brand product brand
	 * @param price product price
	 * @param nbinstock product stock number
	 */
	public static void getProdUpdate(int id, String name, String category, String brand, double price, int nbinstock) {
		Product product = new Product(id, name, category, brand, price, nbinstock);
		QuantityValidator qv = new QuantityValidator();
		if (qv.validate(product) == 0) {
			JOptionPane.showMessageDialog(null, "Price and stock number cannot be smaller than 0!");
			return;
		}
		ProductDAO.update(product);
		for (Product p : Model.products) {
			if (p.getId() == product.getId()) {
				p.setName(product.getName());
				p.setCategory(product.getCategory());
				p.setBrand(product.getBrand());
				p.setNbInStock(product.getNbInStock());
				p.setPrice(product.getPrice());

			}
		}
	}
	/**
	 * Method for getting the values introduced in the text fields of order for making a new order and inserting the order
	 * @param clientEmail client email
	 * @param productName product name
	 * @param quantity ordered quantity
	 */
	public static void getOrderAdd(String clientEmail, String productName, int quantity) {
		Order order = OrderDAO.insert(clientEmail, productName, quantity);
		OrderValidator ov = new OrderValidator();
		if (ov.validate(order) == 0) {
			JOptionPane.showMessageDialog(null, "Quantity cannot be smaller than 0!");
			return;
		}
		order.setId(Model.orders.size() + 1);
		Model.orders.add(order);
		String orderName = "Order " + order.getId();
		try {
			PrintWriter writer = new PrintWriter(orderName);
			writer.println("Order id: " + order.getId());
			writer.println("Client email: " + clientEmail);
			writer.println("Product name: " + productName);
			writer.println("Shipping address: " + order.getAddress());
			writer.println("Quantity: " + quantity); 
			writer.println("Total: " + order.getTotal());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view, model);
	}
}
