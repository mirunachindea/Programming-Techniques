package businessLayer;

import java.util.HashSet;

public interface Observable {

	public void notifyObservers(Order order, HashSet<MenuItem> items);
}
