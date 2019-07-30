package businessLayer;

import java.util.ArrayList;

public interface MenuItem {
	
	public int getId();
	public void setId(int id);
	public String getName();
	public void setName(String name);
	public float computePrice();
	public void setPrice(float price);
	public String getQuantity();
	public void setQuantity(String quantity);
	public ArrayList<MenuItem> getItems();
	public void setItems(ArrayList<MenuItem> items);

}
