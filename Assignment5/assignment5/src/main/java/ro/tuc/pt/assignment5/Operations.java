package ro.tuc.pt.assignment5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Operations class
 * 
 * @author Miruna
 *
 */
public class Operations {

	/**
	 * Reads the file and splits the line in three parts
	 * 
	 * @return list of all strings from file
	 */
	public static List<String> readLines() {

		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			list = stream.flatMap(string -> Stream.of(string.split("		"))).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Creates a list of objects of type MonitoredData
	 * 
	 * @param list list of all strings from file
	 * @return list of objects of type MonitoredData
	 */
	public static ArrayList<MonitoredData> readObj(List<String> list) {
		ArrayList<MonitoredData> data = new ArrayList<MonitoredData>();
		int i = 0;
		Date startTime = new Date();
		Date endTime = new Date();
		
		for (String item : list) {

			if (i == 3)
				i = 0;

			if (i == 0) {
				DateFormat format = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
				startTime = new Date();
				try {
					startTime = format.parse(item);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (i == 1) {
				DateFormat format = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
				endTime = new Date();
				try {
					endTime = format.parse(item);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} else if (i == 2) {
				String activity = item;
				MonitoredData dataItem = new MonitoredData(startTime, endTime, activity);
				data.add(dataItem);
			}
			i++;
		}
		return data;
	}

	/**
	 * Creates a list of all monitored dates
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return set of all monitored dates
	 */
	public static HashSet<String> getDates(ArrayList<MonitoredData> data) {

		HashSet<String> dates = new HashSet<String>(data.size());

		data.stream().filter(p -> dates.add(p.getStartTime().toString().substring(0, 10))).collect(Collectors.toList());

		data.stream().filter(p -> dates.add(p.getEndTime().toString().substring(0, 10))).collect(Collectors.toList());

		return dates;
	}

	/**
	 * Creates a list of all monitored activities
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return set of all monitored activities
	 */
	public static ArrayList<String> getActivities() {

		ArrayList<String> activities = new ArrayList<String>();

		activities.add("Leaving");
		activities.add("Sleeping");
		activities.add("Toileting");
		activities.add("Showering");
		activities.add("Breakfast");
		activities.add("Grooming");
		activities.add("Spare_Time/TV");
		activities.add("Lunch");
		activities.add("Snack");
		activities.add("Dinner");

		return activities;
	}

	/**
	 * Gets number of monitored days
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return number of monitored days
	 */
	public static int distinctDays(ArrayList<MonitoredData> data) {

		HashSet<String> dates = getDates(data);

		return dates.size();

	}

	/**
	 * Writes in file number of monitored days
	 * 
	 * @param days number of monitored days
	 */
	public static void writeDistinctDays(int days) {

		String fileName = "2. Number of days.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.println("Number of monitored days: " + days);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Counts how many times has appeared each activity over the entire monitoring
	 * period
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return map representing the activity and its count
	 */
	public static Map<String, Integer> mapActivities(ArrayList<MonitoredData> data) {

		Map<String, Integer> map1 = new HashMap<String, Integer>();

		ArrayList<String> activities = getActivities();
		for (String activity : activities) {

			ArrayList<MonitoredData> activityData = (ArrayList<MonitoredData>) data.stream()
					.filter(p -> p.getActivity().contains(activity)).collect(Collectors.toList());
			map1.put(activity, activityData.size());
		}
		return map1;
	}

	/**
	 * Writes in file the activity count
	 * 
	 * @param map map representing the activity and its count
	 */
	public static void writeActCountTotal(Map<String, Integer> map) {
		String fileName = "3. Activity count per total.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);
			for (Map.Entry<String, Integer> mItems : map.entrySet()) {
				writer.println(mItems.getKey() + " " + mItems.getValue());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Counts how many times has appeared each activity for each day over the
	 * monitoring period
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return map representing the day and its activity count
	 */
	public static Map<String, Map<String, Integer>> mapActivitiesDaily(ArrayList<MonitoredData> data) {

		Map<String, Map<String, Integer>> mapActivityDaily = new HashMap<String, Map<String, Integer>>();
		HashSet<String> dates = Operations.getDates(data);

		for (String date : dates) {
			ArrayList<MonitoredData> dailyData = (ArrayList<MonitoredData>) data.stream()
					.filter(p -> p.getStartTime().toString().substring(0, 10).equals(date)
							|| p.getEndTime().toString().substring(0, 10).equals(date))
					.collect(Collectors.toList());
			Map<String, Integer> mapDaily = Operations.mapActivities(dailyData);
			mapActivityDaily.put(date, mapDaily);
		}

		return mapActivityDaily;
	}

	/**
	 * Writes in file the activity count for each day
	 * 
	 * @param map map representing the activity and its count
	 */
	public static void writeActCountDaily(Map<String, Map<String, Integer>> mapActivityDaily) {
		String fileName = "4. Activity count per day.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);

			for (Map.Entry<String, Map<String, Integer>> mItems : mapActivityDaily.entrySet()) {
				writer.println("Date: " + mItems.getKey());
				writer.println(mItems.getValue().toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * For each activity compute the entire duration over the monitoring period
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return map represent the activity and its duration
	 */
	public static Map<String, Long> getActivityTime(ArrayList<MonitoredData> data) {

		ArrayList<String> activities = Operations.getActivities();

		Map<String, Long> activityTime = new HashMap<String, Long>();

		for (String activity : activities) {

			// filter the data for each activity
			ArrayList<MonitoredData> activityData = (ArrayList<MonitoredData>) data.stream()
					.filter(p -> p.getActivity().contains(activity)).collect(Collectors.toList());

			long time = 0;

			for (MonitoredData d : activityData) {
				time += (d.getEndTime().getTime() - d.getStartTime().getTime());
			}

			time = Math.abs(time);
			time /= 1000;

			activityTime.put(activity, time);
		}

		return activityTime;
	}

	/**
	 * Writes in file duration of each activity
	 * 
	 * @param activityTime map representing the activity and its duration
	 */
	public static void writeActTime(Map<String, Long> activityTime) {

		String fileName = "6. Activity time.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);

			for (Map.Entry<String, Long> act : activityTime.entrySet()) {
				writer.println(act.getKey() + " " + Operations.timeToString(act.getValue()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Transforms the time from seconds to hours, minutes and seconds
	 * 
	 * @param time time in seconds
	 * @return string representing time in hours, minutes and seconds
	 */
	public static String timeToString(long time) {

		int hours = (int) time / 3600;
		int rem = (int) time - hours * 3600;
		int mins = rem / 60;
		rem -= mins * 60;
		int secs = rem;

		String s = hours + "h " + mins + "m " + secs + "s ";
		return s;
	}

	/**
	 * Filter the activities that have 90% of the monitoring records with duration
	 * less than 5 minutes
	 * 
	 * @param data list of objects of type MonitoredData
	 * @return list of activities that have 90% of the monitoring records with
	 *         duration less than 5 minutes
	 */
	public static ArrayList<String> filterActivities2(ArrayList<MonitoredData> data) {

		ArrayList<String> activities = Operations.getActivities();
		ArrayList<String> fa = new ArrayList<String>();

		for (String activity : activities) {
			ArrayList<MonitoredData> activityData = (ArrayList<MonitoredData>) data.stream()
					.filter(p -> p.getActivity().contains(activity)).collect(Collectors.toList());
			int total = activityData.size();
			float part = 0;
			for (MonitoredData data2 : activityData) {
				long time = (Math.abs(data2.getEndTime().getTime() - data2.getStartTime().getTime())) / 1000;
				if (time < 300) {
					part++;
				}
				
			}

			if (part / total <= 0.9) {
				fa.add(activity);
			}
			
		}

		return fa;
	}

	/**
	 * Writes in file the activities that have 90% of the monitoring records with
	 * duration less than 5 minutes
	 * 
	 * @param fa list of activities
	 */
	public static void writeAct5Min(ArrayList<String> fa) {

		String fileName = "7. Activities 5m.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);

			for (String a : fa) {
				writer.println(a);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * For each line from the file map for the activity labels the duration recorded
	 * on that line
	 * 
	 * @param data list of objects of type MonitoredData
	 */
	public static Map<Integer, Long> timePerLine(ArrayList<MonitoredData> data) {
		
		ArrayList<Long> times = new ArrayList<Long>();
		Map<Integer, Long> mapTimes = new HashMap<Integer, Long>();
		
		data.stream().filter(p -> times.add(p.getEndTime().getTime() - p.getStartTime().getTime())).collect(Collectors.toList());
		
		int i = 1;
		for(Long time : times) {
			mapTimes.put(i, time);
			i++;
		}

		return mapTimes;
	}
	
	public static void writeTimePerLine(Map<Integer, Long> mapTimes) {
		
		String fileName = "5. Time per line.txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);
			for(Map.Entry<Integer, Long> time : mapTimes.entrySet()) {
				writer.println("Line " + time.getKey() + ": " + timeToString(time.getValue()/1000));
			}
				
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
