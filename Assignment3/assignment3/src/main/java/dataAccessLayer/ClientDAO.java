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
/**
 * Class Client Data Access Object
 * @author Miruna
 *
 */
public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private final static String findAllStatementString = "SELECT * FROM client";
	private final static String insertStatementString = "INSERT INTO client (name,email,address,telephone)" + " VALUES (?,?,?,?)";
	private final static String findByIdStatementString = "SELECT * FROM client where id = ?";
	private final static String findByEmailStatementString = "SELECT * FROM client where email = ?";
	private final static String deleteStatementString = "DELETE FROM client where id = ?";
	private final static String updateStatementString = "UPDATE client set name = ?, email = ?, address = ?, telephone = ? where id = ?";
	/**
	 * Method for getting all clients in the table
	 * @return list of clients in table
	 */
	public static ArrayList<Client> findAll() {
		ArrayList<Client> clients = new ArrayList<Client>();
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findAllStatementString);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				clients.add(new Client(id, name, email, address, telephone));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return clients;
	}
	/**
	 * Method for finding a client by id
	 * @param clientId client's id
	 * @return client 
	 */
	public static Client findById(int clientId) {
		Client toReturn = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findByIdStatementString);
			st.setInt(1, clientId);
			rs = st.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				toReturn = new Client(clientId, name, email, address, telephone);
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
	 * Method for finding a client by e-mail
	 * @param clientEmail client's e-mail
	 * @return client
	 */
	public static Client findByEmail(String clientEmail) {
		Client toReturn = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(findByEmailStatementString);
			st.setString(1, clientEmail);
			rs = st.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				toReturn = new Client(id, name, clientEmail, address, telephone);
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
	 * Method for inserting a client in table
	 * @param client client to insert
	 * @return -1 if the insertion was unsuccessful, a positive number otherwise
	 */
	public static int insert(Client client) {
		if (findByEmail(client.getEmail()) != null) {
			return -1;
		}
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		int insertedId = -1;
		try {
			st = con.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, client.getName());
			st.setString(2, client.getEmail());
			st.setString(3, client.getAddress());
			st.setString(4, client.getTelephone());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.getStackTrace();
		} finally {
			ConnectionFactory.close(st);
			ConnectionFactory.close(con);
		}
		return insertedId;
	}
	/**
	 * Method for deleting a client from table
	 * @param clientId client's id
	 * @return 1 if deletion was successful, -1 otherwise
	 */
	public static int delete(int clientId) {
		if (findById(clientId) == null) {
			return -1;
		}
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement(deleteStatementString);
			st.setInt(1, clientId);
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
	 * Method for updating a client
	 * @param client client to update
	 * @return 1 if update was successful, -1 otherwise
	 */
	public static int update(Client client) {
		if (findByEmail(client.getEmail()) == null || findById(client.getId()).getEmail().equals(client.getEmail())) {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement(updateStatementString);
				st.setString(1, client.getName());
				st.setString(2, client.getEmail());
				st.setString(3, client.getAddress());
				st.setString(4, client.getTelephone());
				st.setInt(5, client.getId());
				st.executeUpdate();
			} catch (SQLException ex) {
				ex.getStackTrace();
			} finally {
				ConnectionFactory.close(st);
				ConnectionFactory.close(con);
			}
			return 1;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id!");
		}
		return -1;
	}
}
