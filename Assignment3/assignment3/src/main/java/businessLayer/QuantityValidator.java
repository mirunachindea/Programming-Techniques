package businessLayer;

import model.Product;

/**
 * Class for validating a product
 * @author Miruna
 *
 */
public class QuantityValidator implements Validator<Product> {
	
	/**
	 * Method for validating a product's stock number and price
	 * @param p product
	 * @return 1 if the number in stock and the price are higher than 0, 0 otherwise
	 */
	public int validate(Product p) {
		if (p.getNbInStock() <= 0 || p.getPrice() <= 0) {
			return 0;
		}
		return 1;
	}
}
