package model;
import java.util.ArrayList;
import view.View;
/** 
 * Simulation class
 * @author Miruna
 */
public class Simulator extends Thread{
	private int MIN_ARRIVAL_TIME;
	private int MAX_ARRIVAL_TIME;
	private int NB_OF_QUEUES;
	private int MIN_SERVICE_TIME;
	private int MAX_SERVICE_TIME;
	public static int SIMULATION_TIME;
	private int nbOfClients;
	private int totalWaitingTime;
	private int totalServiceTime;
	private static int currentTime;
	private int peakHour;
	private ArrayList<Queue> queues;
	/**
	 * Simulation constructor with no parameters
	 */
	public Simulator() {
		this.nbOfClients = 0;
		this.totalWaitingTime = 0;
		this.totalServiceTime = 0;
		this.peakHour = 1;
		currentTime = 1;
	}
	/**
	 * Method for setting the interval of arriving time between clients
	 * @param minArrivingTime minimum arriving time between clients
	 * @param maxArrivingTime maximum arriving time between clients
	 */
	public void setArrivalTimes(int minArrivingTime, int maxArrivingTime) {
		this.MIN_ARRIVAL_TIME = minArrivingTime;
		this.MAX_ARRIVAL_TIME = maxArrivingTime;
	}
	/**
	 * Method for setting the interval of service time of clients
	 * @param minServiceTime minimum service time
	 * @param maxServiceTime maximum service time
	 */
	public void setServiceTimes(int minServiceTime, int maxServiceTime) {
		this.MIN_SERVICE_TIME = minServiceTime;
		this.MAX_SERVICE_TIME = maxServiceTime;
	}
	/**
	 * Method for setting the number of queues
	 * @param queueNb number of queues
	 */
	public void setQueueNb(int queueNb) {
		this.NB_OF_QUEUES = queueNb;
		this.queues = new ArrayList<Queue>();
		for(int i=1; i <= NB_OF_QUEUES; i++) {
			queues.add(new Queue(i));
		}
	}
	/**
	 * Method for getting the number of queues
	 * @return number of queues
	 */
	public int getQueueNb() {
		return this.NB_OF_QUEUES;
	}
	/**
	 * Method for setting the time interval of simulation
	 * @param simInterval time interval of simulation
	 */
	public void setSimulationInterval(int simInterval) {
		SIMULATION_TIME = simInterval;
	}
	/**
	 * Method for getting the list of queues
	 * @return list of queues
	 */
	public ArrayList<Queue> getQueues(){
		return this.queues;
	}
	/**
	 * Method for generating a random client
	 * @param arrivalTime client's arrival time
	 * @return new client
	 */
	private Client getRandomClient(int arrivalTime) {
		int range_s = MAX_SERVICE_TIME - MIN_SERVICE_TIME + 1;
		int serviceTime = (int)(Math.random() * range_s) + MIN_SERVICE_TIME;
		Client newClient = new Client(++nbOfClients, arrivalTime, serviceTime);
		return newClient;
	}
	/**
	 * Method for getting the queue with the smallest waiting time
	 * @return queue with the smallest waiting time
	 */
	private int getMinQueue() {
		int minQueue = -1;
		int minTime = 99999999;
		for(int i=0; i < queues.size(); i++) {
			if( queues.get(i).getQueue().size() == 0) {
				return i;
			}
			if( queues.get(i).getWaitingTime() < minTime) {
				minQueue = i;
				minTime = queues.get(i).getWaitingTime();
			}
		}
		return minQueue;
	}
	public static int getCurrentTime() {
		return currentTime;
	}
	/**
	 * Method for adding a client to the smallest queue
	 * @param client client to be added
	 * @return the queue in which the client was added
	 */
	private Queue addToQueue(Client client) {
		int minQueue = this.getMinQueue();
		queues.get(minQueue).enQ(client);
		return queues.get(minQueue);
	}
	@Override
	public String toString() {
		String s = new String("");
		if (!queues.isEmpty()) {
				for(Queue queue : queues) {
					s += queue.toString() + "\n";
				}
			}
		return s;
	}
	/**
	 * Run method
	 */
	public void run() {
		for(int i=0; i < queues.size(); i++)
			queues.get(i).start();
		int nextArrivalTime;
		int nextArrivalTimeCount = 0;
		int maxNbOfClients = 0;
		int ctNbOfClients;
		int emptyQueueTime = 0;
		int range = MAX_ARRIVAL_TIME - MIN_ARRIVAL_TIME + 1;
		addNewClient();
		nextArrivalTime = (int)(Math.random() * range) + MIN_ARRIVAL_TIME;
		while( currentTime <= SIMULATION_TIME) {
			View.setCtTime(currentTime);
			ctNbOfClients = this.getNbOfClients();
			if( ctNbOfClients > maxNbOfClients ) {
				maxNbOfClients = ctNbOfClients;
				this.peakHour = currentTime;
			}
			if ( isEmptyQueue() ) {
				emptyQueueTime ++;
			}
			if( nextArrivalTimeCount == nextArrivalTime) {
				addNewClient();
				nextArrivalTime = (int)(Math.random() * range) + MIN_ARRIVAL_TIME;
				nextArrivalTimeCount = 0;
			}
			View.setQueues(this.toString());
			try {
				sleep(1000);
			}
			catch(InterruptedException ex) {
				System.out.println(ex);
			}
			nextArrivalTimeCount ++;
			currentTime ++;
		}
		View.setLogger("Average waiting time:" + this.getAvgWaitingTime() + " s\nAverage service time: " + this.getAvgServiceTime() + " s\nPeak hour: " + this.peakHour + "\nEmpty queue time: " + emptyQueueTime + " s");
	}
	/**
	 * Method for getting the average waiting time
	 * @return average waiting time
	 */
	public float getAvgWaitingTime() {
		return (float)totalWaitingTime / nbOfClients;
	}
	/**
	 * Method for getting the average service time
	 * @return average service time
	 */
	public float getAvgServiceTime() {
		return (float)totalServiceTime / nbOfClients;
	}
	/**
	 * Method for getting the number of clients at a time
	 * @return number of clients at a time
	 */
	public int getNbOfClients() {
		int nbOfClients = 0;
		for(Queue queue : queues) {
			nbOfClients += queue.getSize();
		}
		return nbOfClients;
	}
	/**
	 * Method for adding a new client and displaying the information in logger
	 */
	public void addNewClient() {
		String logger = new String("");
		Client newClient = getRandomClient(currentTime);
		Queue queueToAdd = addToQueue(newClient);
		totalWaitingTime += queueToAdd.getWaitingTime();
		totalServiceTime += newClient.getServiceTime();
		logger = "Client number " + newClient.getNumber() + " arrived at time " + newClient.getArrivalTime() + " with service time " + newClient.getServiceTime() + "\n" + "Client number " + newClient.getNumber() + " was placed in queue number " + queueToAdd.getNumber() + "\n";;
		View.setLogger(logger);
	}
	/**
	 * Method for checking if one or more queues are empty
	 * @return true if one more queues are empty, false otherwise
	 */
	boolean isEmptyQueue() {
		if( !queues.isEmpty()) {
			for(Queue queue : queues) {
				if (queue.getQueue().isEmpty()){
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
