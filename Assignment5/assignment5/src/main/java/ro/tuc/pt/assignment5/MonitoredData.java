package ro.tuc.pt.assignment5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MonitoredData class
 * 
 * @author Miruna
 *
 */

public class MonitoredData {

	Date startTime;
	Date endTime;
	String activity;

	/**
	 * Constructor without parameters
	 */
	public MonitoredData() {
		this.startTime = new Date();
		this.endTime = new Date();
		this.activity = new String();
	}

	/**
	 * Constructor
	 * 
	 * @param startTime activity start time
	 * @param endTime   activity end time
	 * @param activity  activity name
	 */
	public MonitoredData(Date startTime, Date endTime, String activity) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	/**
	 * Start time getter
	 * 
	 * @return start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Start time setter
	 * 
	 * @param startTime start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * End time getter
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * End time setter
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Activity name getter
	 * 
	 * @return activity name
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * Activity name setter
	 * 
	 * @param activity activity name
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "MonitoredData [startTime=" + startTime + ", endTime=" + endTime + ", activity=" + activity + "]";
	}

	public static void main(String[] args) {

		/*
		 * 1. Read the data from the file Activity.txt using streams and split each line
		 * in 3 parts
		 */
		List<String> list = Operations.readLines();

		/*
		 * 1. Create a list of objects of type MonitoredData.
		 */
		ArrayList<MonitoredData> data = Operations.readObj(list);

		/*
		 * 2. Count how many days of monitored data appears in the log Display in file.
		 */
		int days = Operations.distinctDays(data);
		Operations.writeDistinctDays(days);

		/*
		 * 3. Count how many times has appeared each activity over the entire monitoring
		 * period. Display in file.
		 */
		Map<String, Integer> mapActivity = Operations.mapActivities(data);
		Operations.writeActCountTotal(mapActivity);

		/*
		 * 4. Count how many times has appeared each activity for each day over the
		 * monitoring period. Display in file.
		 */
		Map<String, Map<String, Integer>> mapActivityDaily = Operations.mapActivitiesDaily(data);
		Operations.writeActCountDaily(mapActivityDaily);

		/*
		 * 5. For each line from the file map for the activity label the duration
		 * recorded on that line. Display in file.
		 */
		Map<Integer, Long> mapTimes = Operations.timePerLine(data);
		Operations.writeTimePerLine(mapTimes);

		/*
		 * 6. For each activity compute the entire duration over the monitoring period.
		 * Display in file.
		 */
		Map<String, Long> activityTime = Operations.getActivityTime(data);
		Operations.writeActTime(activityTime);

		/*
		 * 7. Filter the activities that have 90% of the monitoring records with
		 * duration less than 5 minutes. Display in file.
		 */
		ArrayList<String> act = Operations.filterActivities2(data);
		Operations.writeAct5Min(act);

	}
}
