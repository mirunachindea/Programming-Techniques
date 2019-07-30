package businessLayer;

import java.util.ArrayList;
import java.util.HashSet;

public interface RestaurantProcessing {
	
	// Administrator operations 
	public MenuItem createMenuItem(int id, String name, float price, String quantity, ArrayList<MenuItem> items);
	public void deleteMenuItem(int id);
	public void editMenuItem(int id, String name, float price, String quantity, ArrayList<MenuItem> items);
	
	// Waiter operations
	public void createOrder(Order order, HashSet<MenuItem> items);
	public float computePriceOrder(int orderId);
	public void generateBill(int orderId);
	
}
