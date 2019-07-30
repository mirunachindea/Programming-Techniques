package model;

/** 
 * Product class
 * @author Miruna
 *
 */
public class Product {

	private int id;
	private String name;
	private String category;
	private String brand;
	private double price;
	private int nbInStock;

	/**
	 * Product constructor with no parameters
	 */
	public Product() {

		this.id = 0;
		this.name = new String("");
		this.category = new String("");
		this.brand = new String("");
		this.price = 0;
		this.nbInStock = 0;
	}
	
	/**
	 * Product constructor
	 * @param id product id
	 * @param name product name
	 * @param category product category
	 * @param brand product brand
	 * @param price product price
	 * @param nbInStock product stock number
	 */
	public Product(int id, String name, String category, String brand, double price, int nbInStock) {

		this.id = id;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.nbInStock = nbInStock;
	}

	/**
	 * Method for getting product's id
	 * @return product's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method for setting product's id
	 * @param id product's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method for getting product's name
	 * @return product's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method for setting product's name
	 * @param name product's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method for getting product's category
	 * @return product's category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Method for setting product's category
	 * @param category product's category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Method for getting product's brand
	 * @return product's brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Method for setting product's brand
	 * @param brand product's brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Method for getting product's price
	 * @return product's price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method for setting product's price
	 * @param price product's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Method for getting product's stock number
	 * @return product's stock number
	 */
	public int getNbInStock() {
		return nbInStock;
	}

	/**
	 * Method for setting product's stock number
	 * @param nbInStock product's stock number
	 */
	public void setNbInStock(int nbInStock) {
		this.nbInStock = nbInStock;
	}

}