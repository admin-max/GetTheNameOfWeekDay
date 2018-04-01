package onTheSpareTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetTheNameOfDay
{
	public static void main(String[] args) 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) 
		{
			System.out.println("Please enter month:");
			String month = readLine(br);
			if (month.isEmpty())
				break;

			System.out.println("Please enter day:");
			int day = Integer.parseInt(readLine(br));

			System.out.println("Please enter year 0-9999:");
			int year = Integer.parseInt(readLine(br));

			System.out.println("The day of the week is: " + getDayOfWeek(month, day, year));
		}

	}

	static String readLine(BufferedReader br) 
	{
		try 
		{
			return br.readLine().trim();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}

	static ArrayList<String> days = new ArrayList<>();
	static 
	{
		days.add("Sunday");
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
	}

	static Map<String, Integer> months = new HashMap<String, Integer>();

	// Information about codes for each month was found at
	// http://gmmentalgym.blogspot.ca/2011/03/day-of-week-for-any-date-revised.html#ndatebasics

	static 
	{
		months.put("January", 6);
		months.put("February", 2);
		months.put("March", 2);
		months.put("April", 5);
		months.put("May", 0);
		months.put("June", 3);
		months.put("July", 5);
		months.put("August", 1);
		months.put("September", 4);
		months.put("October", 6);
		months.put("November", 2);
		months.put("December", 4);
	}

	static Map<Integer, Integer> years = new HashMap<Integer, Integer>();

	// Fulfilling map with years and corresponding to them codes (indexes)

	static 
	{
		for (int year = 0, index = 0; year <= 9999; year++) 
		{
			if (index == 7)
				index = 0;

			// Exception for leap years
			if (year != 0 && year % 4 == 0) {
				index++;

				// Exception from exception in the rule above
				if (year % 100 == 0 && year % 400 != 0)
					index--;

			}
			years.put(year, index);
			index++;
		}
	}

	static String getDayOfWeek(String month, int day, int year) 
	{
		// Operations with String to access data in map
		String monthCorrected = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

		// January and February are exception from the rule as well as years
		// that divides on 100 with remainder 0 and divides on 400 with
		// remainder not equals 0
		if (year % 4 == 0 && (monthCorrected.equals("January") || monthCorrected.equals("February"))
				&& !((year % 100 == 0 && year % 400 != 0)))
			return days.get(((months.get(monthCorrected) - 1) + day + years.get(year)) % 7);

		return days.get((months.get(monthCorrected) + day + years.get(year)) % 7);
	}
}
