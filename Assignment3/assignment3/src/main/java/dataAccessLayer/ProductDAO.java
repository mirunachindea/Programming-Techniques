package dataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import model.Product;
/**
 * Class Product Data Access Object
 * @author Miruna
 *
 */
public class ProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

	private final static String findAllStatementString = "SELECT * FROM product";
	private final static String insertStatementString = "INSERT INTO product (name,category,brand,price,nbinstock)" + " VALUES (?,?,?,?,?)";
	private final static String findByIdStatementString = "SELECT * FROM product where id = ?";
	private final static String findByNameStatementString = "SELECT * FROM product where name = ?";
	private final static String deleteStatementString = "DELETE FROM product where id = ?";
	private final static String updateStatementString = "UPDATE product set name = ?, category = ?, brand = ?, price = ?, nbinstock = ? where id = ?";
	/**
	 * Method for getting all products from table
	 * @return list of products
	 */
	public static ArrayList<Product> findAll() {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findAllStatementString);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String category = rs.getString("category");
				String brand = rs.getString("brand");
				double price = rs.getDouble("price");
				int nbinstock = rs.getInt("nbinstock");
				products.add(new Product(id, name, category, brand, price, nbinstock));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return products;
	}
	/**
	 * Method for finding a product by id
	 * @param productId product's id
	 * @return product
	 */
	public static Product findById(int productId) {
		Product toReturn = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findByIdStatementString);
			st.setInt(1, productId);
			rs = st.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String category = rs.getString("category");
				String brand = rs.getString("brand");
				float price = rs.getFloat("price");
				int nbinstock = rs.getInt("nbinstock");
				toReturn = new Product(productId, name, category, brand, price, nbinstock);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return toReturn;
	}
	/**
	 * Method for finding a product by its name
	 * @param productName product's name
	 * @return product
	 */
	public static Product findByName(String productName) {
		Product toReturn = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findByNameStatementString);
			st.setString(1, productName);
			rs = st.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String brand = rs.getString("brand");
				float price = rs.getFloat("price");
				int nbinstock = rs.getInt("nbinstock");
				toReturn = new Product(id, productName, category, brand, price, nbinstock);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return toReturn;
	}
	/**
	 * Method for inserting a product in table
	 * @param product product to insert
	 * @return -1 if insertion unsuccessful, positive number otherwise
	 */
	public static int insert(Product product) {
		if (findByName(product.getName()) != null) {
			return -1;
		}
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		int insertedId = -1;
		try {
			st = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, product.getName());
			st.setString(2, product.getCategory());
			st.setString(3, product.getBrand());
			st.setDouble(4, product.getPrice());
			st.setInt(5, product.getNbInStock());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next())
				insertedId = rs.getInt(1);
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return insertedId;
	}
	/** 
	 * Method for deleting a product from table
	 * @param productId product's id
	 * @return 1 if deletion was successful, -1 otherwise
	 */
	public static int delete(int productId) {
		if (findById(productId) == null) {
			return -1;
		}
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(deleteStatementString);
			st.setInt(1, productId);
			st.executeUpdate();
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return 1;
	}
	/**
	 * Method for updating a product
	 * @param product product to update
	 * @return 1 if update was successful, -1 otherwise
	 */
	public static int update(Product product) {
		if (findByName(product.getName()) == null || findById(product.getId()).getName().equals(product.getName())) {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement(updateStatementString);
				st.setString(1, product.getName());
				st.setString(2, product.getCategory());
				st.setString(3, product.getBrand());
				st.setDouble(4, product.getPrice());
				st.setInt(5, product.getNbInStock());
				st.setInt(6, product.getId());
				st.executeUpdate();
			} catch (SQLException ex) {
				ex.getStackTrace();
			} finally {
				ConnectionFactory.close(st);
				ConnectionFactory.close(con);
			}
			return 1;
		}
		return -1;
	}
}