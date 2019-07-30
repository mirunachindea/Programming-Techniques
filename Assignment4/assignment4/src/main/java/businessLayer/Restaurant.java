package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JOptionPane;

import dataLayer.FileWriter;
import dataLayer.RestaurantSerializator;
import presentationLayer.AdministratorGUI;
import presentationLayer.ChefGUI;
import presentationLayer.WaiterGUI;

/**
 * @invariant isWellFormed()
 *
 */
@SuppressWarnings("serial")
public class Restaurant implements Observable, RestaurantProcessing, java.io.Serializable {

	private Map<Order, HashSet<MenuItem>> orders;
	private HashSet<MenuItem> menuItems;
	private ChefGUI chef;

	public Restaurant() {
		this.setOrders(new HashMap<Order, HashSet<MenuItem>>());
		this.setMenuItems(new HashSet<MenuItem>());
	}

	/**
	 * @pre id > 0
	 * @pre !name.equals("")
	 * @pre price > 0
	 * @pre !quantity.equals("")
	 * @post @result != null
	 */
	public MenuItem createMenuItem(int id, String name, float price, String quantity, ArrayList<MenuItem> items) {

		assert id > 0 && !name.equals("") && price > 0 && !quantity.equals("");
		assert isWellFormed();
		
		for (MenuItem item : menuItems) {
			if (id == item.getId()) {
				JOptionPane.showMessageDialog(null, "Id already exists!");
				return null;
			}
		}
		// base product
		if (items == null) {
			MenuItem item = new BaseProduct(id, name, price, quantity);
			menuItems.add(item);
			assert isWellFormed();
			return item;
		}
		// composite product
		else {
			MenuItem item = new CompositeProduct(id, name, price, quantity, items);
			menuItems.add(item);
			assert isWellFormed();
			return item;
		}
		
	}

	/**
	 * @pre id > 0
	 * @pre getMenuItems() != null
	 */
	public void deleteMenuItem(int id) {
		assert id > 0;
		assert isWellFormed();
		for (MenuItem item : menuItems) {
			if (item.getId() == id) {
				menuItems.remove(item);
				assert isWellFormed();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Item doesn't exist!");
	}

	/**
	 * @pre id > 0
	 * @pre !name.equals("")
	 * @pre price > 0
	 * @pre !quantity.equals("")
	 */
	public void editMenuItem(int id, String name, float price, String quantity, ArrayList<MenuItem> items) {
		assert id > 0 && !name.equals("") && price > 0 && !quantity.equals("");
		assert isWellFormed();
		for (MenuItem item : menuItems) {
			if (item.getId() == id) {
				// input - base product
				if (items == null) {
					System.out.println(item.getClass().toString());
					// if base product
					if (item.getClass().toString().equals("class businessLayer.BaseProduct")) {
						item.setName(name);
						item.setPrice(price);
						item.setQuantity(quantity);
					}
					// if composite product
					else if (item.getClass().toString().equals("class businessLayer.CompositeProduct")) {
						menuItems.remove(item);
						createMenuItem(id, name, price, quantity, null);
					}
				}
				// input - composite product
				else {
					if (item.getClass().toString().equals("class businessLayer.CompositeProduct")) {
						item.setName(name);
						item.setPrice(price);
						item.setQuantity(quantity);
						item.setItems(items);
					} else if (item.getClass().toString().equals("class businessLayer.BaseProduct")) {
						menuItems.remove(item);
						createMenuItem(id, name, price, quantity, items);
					}
				}
				assert isWellFormed();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Item doesn't exist!");
	}

	/**
	 * @pre order != null
	 * @pre items != null
	 */
	public void createOrder(Order order, HashSet<MenuItem> items) {
		assert order != null && items != null;
		assert isWellFormed();
		for (Map.Entry<Order, HashSet<MenuItem>> order2 : orders.entrySet()) {
			if (order.getId() == order2.getKey().getId()) {
				JOptionPane.showMessageDialog(null, "Id already exists!");
				return;
			}
		}
		orders.put(order, items);
		notifyObservers(order, items);
		assert isWellFormed();
	}

	/**
	 * @pre orderId > 0
	 * @pre getOrders() != null
	 * @post @result > 0
	 * @post @nochange
	 */
	public float computePriceOrder(int orderId) {
		assert orderId > 0 && getOrders() != null;
		assert isWellFormed();
		float price = 0;
		for (Order order : orders.keySet()) {
			if (order.getId() == orderId) {
				HashSet<MenuItem> items = orders.get(order);
				for (MenuItem item : items) {
					price += item.computePrice();
				}
			}
		}
		return price;
	}

	/**
	 * @pre orderId != null
	 * @pre getOrders() != null
	 * @post @nochange
	 */
	public void generateBill(int orderId) {
		assert orderId > 0 && getOrders() != null;
		assert isWellFormed();
		for (Order order : orders.keySet()) {
			if (order.getId() == orderId) {
				HashSet<MenuItem> items = orders.get(order);
				float price = computePriceOrder(orderId);
				FileWriter.writeFile(order, items, price);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Order doesn't exist!");
	}

	/**
	 * @pre true
	 * @post @nochange
	 */
	public HashSet<MenuItem> getMenuItems() {
		assert menuItems != null;
		return menuItems;
	}

	/**
	 * @pre menuItems != null
	 */
	public void setMenuItems(HashSet<MenuItem> menuItems) {
		assert menuItems != null;
		this.menuItems = menuItems;
	}

	/**
	 * @pre true
	 * @post @nochange
	 */
	public Map<Order, HashSet<MenuItem>> getOrders() {
		assert orders != null;
		return orders;
	}

	/**
	 * @pre orders != null
	 */
	public void setOrders(Map<Order, HashSet<MenuItem>> orders) {
		assert orders != null;
		this.orders = orders;
	}

	/**
	 * @pre true
	 * @post @nochange
	 */
	public ChefGUI getChef() {
		return chef;
	}

	/**
	 * @pre chef != null
	 */
	public void setChef(ChefGUI chef) {
		assert chef != null;
		this.chef = chef;
	}

	/**
	 * @pre id > 0
	 * @pre getMenuItems() != null
	 * @post @nochange
	 */
	public MenuItem getById(int id) {
		assert id > 0 && getMenuItems() != null;
		assert isWellFormed();
		for (MenuItem item : menuItems) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	/**
	 * @pre order != null
	 * @pre items != null
	 */
	public void notifyObservers(Order order, HashSet<MenuItem> items) {
		assert order != null && items != null;
		chef.update(order, items);
	}

	protected boolean isWellFormed() {
		if (menuItems != null) {
			for (MenuItem item : menuItems) {
				if (item == null) {
					return false;
				}
			}
		}
		if(orders != null) {
			for(Map.Entry<Order, HashSet<MenuItem>> order : orders.entrySet()) {
				if(order.getKey() == null) {
					return false;
				}
				for (MenuItem item : order.getValue()) {
					if(item == null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Restaurant restaurant = new Restaurant();

		MenuItem item1 = new BaseProduct(1, "Cartofi la cuptor", 5.5f, "200 g");
		MenuItem item2 = new BaseProduct(2, "Ceafa de porc", 10, "100g");
		MenuItem item3 = new BaseProduct(3, "Salata de legume", 3, "100g");

		ArrayList<MenuItem> composition1 = new ArrayList<MenuItem>();
		composition1.add(item1);
		composition1.add(item2);
		composition1.add(item3);

		restaurant.createMenuItem(1, "Cartofi la cuptor", 5.5f, "200 g",null);
		restaurant.createMenuItem(2, "Ceafa de porc", 10, "100g", null);
		restaurant.createMenuItem(3, "Salata de legume", 3, "100g", null);
		
		restaurant.createMenuItem(4, "Meniu ceafa de porc", 15, "400g", composition1);

		RestaurantSerializator.serialize(restaurant);

		Restaurant restaurant2 = RestaurantSerializator.deserialize();
		restaurant2.setChef(new ChefGUI());

		restaurant2.getOrders();
		AdministratorGUI agui = new AdministratorGUI(restaurant2);
		WaiterGUI wgui = new WaiterGUI(restaurant2);
		

	}
}
