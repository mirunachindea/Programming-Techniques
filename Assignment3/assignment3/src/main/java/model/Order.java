package model;

/**
 * Order class
 * @author Miruna
 *
 */
public class Order {

	private int id;
	private int clientId;
	private int productId;
	private int quantity;
	private String address;
	private double total;

	/**
	 * Order constructor with no parameters
	 */
	public Order() {

		this.id = 0;
		this.clientId = 0;
		this.productId = 0;
		this.quantity = 0;
		this.address = new String("");
		this.total = 0;
	}

	/**
	 * Order constructor
	 * @param id order id
	 * @param clientId client id
	 * @param productId product id
	 * @param quantity ordered quantity
	 * @param address shipping address
	 * @param total order total 
	 */
	public Order(int id, int clientId, int productId, int quantity, String address, double total) {

		this.id = id;
		this.clientId = clientId;
		this.productId = productId;
		this.quantity = quantity;
		this.address = address;
		this.total = total;
	}

	/**
	 * Method for getting order's id
	 * @return order id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method for setting order's id
	 * @param id order id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method for getting client's id
	 * @return client id
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * Method for setting client's id
	 * @param clientId client id
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * Method for getting product's id
	 * @return product id
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Method for setting product's id
	 * @param productId product id
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Method for getting ordered quantity
	 * @return ordered quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Method for setting ordered quantity
	 * @param quantity ordered quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Method for getting shipping address
	 * @return shipping address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method for setting shipping address
	 * @param address shipping address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** 
	 * Method for getting order total
	 * @return  order total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Method for setting order total
	 * @param total  order total
	 */
	public void setTotal(double total) {
		this.total = total;
	}

}
