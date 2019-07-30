package model;

import java.util.ArrayList;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;

/**
 * Model class
 * Contains lists of clients, products and orders
 * @author Miruna
 *
 */
public class Model {

	public static ArrayList<Client> clients;
	public static ArrayList<Product> products;
	public static ArrayList<Order> orders;

	/**
	 * Constructor with no parameters
	 */
	public Model() {
		clients = ClientDAO.findAll();
		products = ProductDAO.findAll();
		orders = OrderDAO.findAll();
	}

	/**
	 * Method for getting all clients from database
	 * @return list of clients
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	/**
	 * Method for getting all products from database
	 * @return list of products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * Method for getting all orders from database
	 * @return list of orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

}
