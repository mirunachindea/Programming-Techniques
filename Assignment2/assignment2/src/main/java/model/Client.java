package model;

/**
 * 
 * @author Miruna
 * Client class
 */
public class Client {
	private int number;
	private int arrivalTime;
	private int serviceTime;
	/**
	 * Client constructor with no parameters
	 */
	public Client() {
		this.number = 0;
		this.arrivalTime = 0;
		this.serviceTime = 0;
	}
	/**
	 * Client constructor
	 * @param number order number
	 * @param arrivalTime time of arrival
	 * @param serviceTime time of service
	 */
	public Client(int number, int arrivalTime, int serviceTime) {
		this.number = number;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}
	
	/**
	 * Method for setting client's number
	 * @param number order number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Method for setting client's arrival time
	 * @param arrivalTime time of arrival
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * Method for setting client's service time
	 * @param serviceTime time of service
	 */
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * Method for getting client's order number
	 * @return client's number
	 */
	public int getNumber() {
		return this.number;
	}
	/**
	 * Method for getting client's arrival time
	 * @return client's arrival time
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	/**
	 * Method for getting client's service time
	 * @return client's service time
	 */
	public int getServiceTime() {
		return this.serviceTime;
	}
	@Override
	public String toString() {
		return "C" + this.number+"("+ this.arrivalTime+ ", " + this.serviceTime +")";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
