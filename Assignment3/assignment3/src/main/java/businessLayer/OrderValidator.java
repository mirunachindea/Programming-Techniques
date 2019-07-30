package businessLayer;

import model.Order;
/**
 * Class for validating the ordered quantity
 * @author Miruna
 *
 */
public class OrderValidator implements Validator<Order> {

	/**
	 * Method for validating an order
	 * @param o order
	 * @return 1 if the ordered quantity is higher than 0, 0 otherwise
	 */
	public int validate(Order o) {
		if (o.getQuantity() <= 0) {
			return 0;
		}
		return 1;
	}
}
