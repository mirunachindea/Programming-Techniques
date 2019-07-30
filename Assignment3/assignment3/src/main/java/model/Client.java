package model;
/**
 * Client class
 * @author Miruna
 *
 */
public class Client {

	private int id;
	private String name;
	private String email;
	private String address;
	private String telephone;
	
	/**
	 * Client constructor with no parameters
	 */
	public Client() {

		this.id = 0;
		this.name = new String("");
		this.email = new String("");
		this.address = new String("");
		this.telephone = new String("");
	}

	/**
	 * Client constructor
	 * @param id client id
	 * @param name client name
	 * @param email client e-mail
	 * @param address client address
	 * @param telephone client telephone number
	 */
	public Client(int id, String name, String email, String address, String telephone) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
	}

	/**
	 * Method for getting client's id
	 * @return client's id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Method for setting client's id
	 * @param id client's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * Method for getting client's name
	 * @return client's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method for setting client's name
	 * @param name client's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method for getting client's email
	 * @return client's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method for setting client's email
	 * @param email client's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method for getting client's address
	 * @return client's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method for setting client's address
	 * @param address client's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method for getting client's telephone number
	 * @return client's telephone number
	 */
	public String getTelephone() {
		return telephone;
	}

	/** 
	 * Method for setting client's telephone number
	 * @param telephone client's telephone number
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
