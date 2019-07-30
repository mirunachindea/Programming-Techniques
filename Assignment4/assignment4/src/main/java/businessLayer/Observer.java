package businessLayer;

import java.util.HashSet;

public interface Observer {

	public void update(Order order, HashSet<MenuItem> items);
}
