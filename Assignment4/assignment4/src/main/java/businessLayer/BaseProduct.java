package businessLayer;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class BaseProduct implements MenuItem, java.io.Serializable{
	
	private int id;
	private String name;
	private float price;
	private String quantity;

	public BaseProduct() {
		this.id = 0;
		this.name = new String();
		this.quantity = new String();
		this.price = 0;
	}

	public BaseProduct(int id, String name, float price, String quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float computePrice() {
		return price;
	}
	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ArrayList<MenuItem> getItems() {
		return null;
	}

	public void setItems(ArrayList<MenuItem> items) {
	
	}

}
