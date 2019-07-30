package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Client;
import model.Order;
import model.Product;

/**
 * Class Order Data Access Object
 * @author Miruna
 *
 */
public class OrderDAO {

	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private final static String findAllStatementString = "SELECT * FROM orders";
	private static final String insertStatementString = "INSERT INTO orders (clientid,productid,quantity,address,total) VALUES (?,?,?,?,?)";

	/**
	 * Method for getting all orders from table
	 * @return list of orders
	 */
	public static ArrayList<Order> findAll() {

		ArrayList<Order> orders = new ArrayList<Order>();

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(findAllStatementString);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idclient = rs.getInt("clientid");
				int idprod = rs.getInt("productid");
				int quantity = rs.getInt("quantity");
				String address = rs.getString("address");
				float total = rs.getFloat("total");
				orders.add(new Order(id, idclient, idprod, quantity, address, total));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return orders;

	}

	/**
	 * Method for inserting an order
	 * @param clientEmail client's email
	 * @param productName product's name
	 * @param quantity ordered quantity
	 * @return order
	 */
	public static Order insert(String clientEmail, String productName, int quantity) {

		Client client;
		Product product;
		Order order = new Order();
		if ((client = ClientDAO.findByEmail(clientEmail)) == null) {
			return null;
		}
		if ((product = ProductDAO.findByName(productName)) == null) {
			return null;
		}
		if (quantity > product.getNbInStock()) {
			JOptionPane.showMessageDialog(null, "Understock!");
			return null;
		}
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		
		order.setClientId(client.getId());
		order.setProductId(product.getId());
		order.setQuantity(quantity);
		order.setAddress(client.getAddress());
		order.setTotal(product.getPrice() * quantity);
		
		System.out.println(order.toString());
		
		try {
			st = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, order.getClientId());
			st.setInt(2, order.getProductId());
			st.setInt(3, order.getQuantity());
			st.setString(4, order.getAddress());
			st.setDouble(5, order.getTotal());
			st.executeUpdate();
			
			product.setNbInStock(product.getNbInStock() - quantity);
			ProductDAO.update(product);
			if( product.getNbInStock() == 0) {
				ProductDAO.delete(product.getId());
			}
			
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {
			
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return order;
	}
}
