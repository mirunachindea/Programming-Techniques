package dataLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

import businessLayer.MenuItem;
import businessLayer.Order;

public class FileWriter {
	
	public static void writeFile(Order order, HashSet<MenuItem> items, float price) {
		String orderName = "Order " + order.getId();
		try {
			PrintWriter writer = new PrintWriter(orderName);
			writer.println("Order number: " + order.getId());
			writer.println("Table: " + order.getTable());
			writer.println("Date: " + order.getDate() );
			writer.println();
			for(MenuItem item : items) {
				writer.println(item.getName() + " " + item.computePrice());
			}
			writer.println();
			writer.println("Total: " + price);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
