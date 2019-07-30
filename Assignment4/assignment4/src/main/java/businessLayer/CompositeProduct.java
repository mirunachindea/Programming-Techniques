package businessLayer;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class CompositeProduct implements MenuItem,java.io.Serializable {
	
	private int id;
	private String name;
	private float price;
	private String quantity;
	private ArrayList<MenuItem> items;
	
	public CompositeProduct() {
		this.id = 0;
		this.name = new String("");
		this.price = 0;
		this.quantity = new String("");
		this.items = new ArrayList<MenuItem>();
	}
	
	public CompositeProduct(int id, String name, float price, String quantity, ArrayList<MenuItem> items) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.items = items;
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

	public ArrayList<MenuItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<MenuItem> items) {
		this.items = items;
	}

	public float computePrice() {
		return price;
	}
	
	public void addItem(MenuItem item) {
		items.add(item);
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	
}
