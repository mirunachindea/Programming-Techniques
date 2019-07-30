package businessLayer;

/**
 * Validator interface
 * @author Miruna
 *
 * @param <T> object to validate
 */
public interface Validator<T> {

	public int validate(T t);
}