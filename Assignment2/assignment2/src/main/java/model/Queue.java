package model;
import java.util.ArrayList;

import view.View;

/**
 * 
 * @author Miruna
 * Queue class
 */
public class Queue extends Thread {
	private int number;
	private ArrayList<Client> queue;
	/**
	 * Queue constructor with no parameters
	 */
	public Queue() {
		this.number = 0;
		this.queue = new ArrayList<Client>();
	}
	/**
	 * Queue constructor
	 * @param number queue's number
	 */
	public Queue(int number) {
		this.number = number;
		this.queue = new ArrayList<Client>();
	}
	/**
	 * Method for setting queue's number
	 * @param number queue's number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Method for getting queue's size(number of clients)
	 * @return queue's size
	 */
	public int getSize() {
		return this.queue.size();
	}
	/**
	 * Method for getting queue's number
	 * @return queue's number
	 */
	public int getNumber() {
		return this.number;
	}
	/**
	 * Method for getting queue's list of clients
	 * @return queue's list of clients
	 */
	public ArrayList<Client> getQueue(){
		return this.queue;
	}
	@Override
	public String toString() {
		String s = new String("Q" + this.number + ": ");
			if (!queue.isEmpty()) {
				for(Client clients : queue) {
					s += clients.toString() + " ";
				}
			}
		return s;
	}
	/**
	 * Method for removing queue's first client
	 */
	public void deQ() {
		if ( !queue.isEmpty()) {
			queue.remove(0);
		}
	}
	/**
	 * Method for adding a new client to the queue
	 * @param client new client
	 */
	public void enQ(Client client) {
		queue.add(client);
	}
	/**
	 * Method for getting queue's total waiting time
	 * @return queue's total waiting time
	 */
	public int getWaitingTime() {
		if( queue.size() == 0) {
			return 0;
		}
		else {
			int time = 0;
			for(Client clients : queue) {
				time += clients.getServiceTime();
			}
			return time;
		}
	}
	/**
	 * Method for getting first client's service time
	 * @return first client's service time
	 */
	public int getFirstClientTime() {
		if(!queue.isEmpty())
			return queue.get(0).getServiceTime();
		else return 0;
	}
	/**
	 * Run method for the queue
	 */
	public void run() {
		while(true) {
			if(!queue.isEmpty()) {
				try {
					sleep(this.getFirstClientTime() * 1000 );
				}
				catch(InterruptedException ex){
					System.out.println(ex);
				}
				if(Simulator.getCurrentTime() <= Simulator.SIMULATION_TIME)
					View.setLogger("Client number " + queue.get(0).getNumber() + " left at time " + Simulator.getCurrentTime() + "\n");
				this.deQ();
			}
			else
				try {
					sleep(1000);
				}
				catch(InterruptedException ex){
					System.out.println(ex);
				}
		}
	}
}
