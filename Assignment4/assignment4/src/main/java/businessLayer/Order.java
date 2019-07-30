package businessLayer;

import java.util.Date;

@SuppressWarnings("serial")
public class Order implements java.io.Serializable{
	
	private int id;
	private Date date;
	private int table;
	
	public Order() {
		this.id = 0;
		this.date = new Date();
		this.table = 0;
	}
	
	public Order(int id, int table) {
		this.id = id;
		this.date = new Date();
		this.table = table;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		if(this.id != 0) {
			sum <<= 2;
			sum |= id;
		}
		if(this.date != null) {
			sum <<= 2;
			sum |= date.hashCode();
		}
		if(this.table != 0) {
			sum <<= table;
			sum |= table;
		}
		return sum;
	}

}
