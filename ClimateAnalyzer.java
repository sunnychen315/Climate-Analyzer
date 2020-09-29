package climateChange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClimateAnalyzer implements IClimateAnalyzer{

	private static final String INPUT_FILE = "data/world_temp_2000-2016.csv";
	private ArrayList<ITemperature> data;
	private IWeatherIO weatherIO;

	public ClimateAnalyzer() {

		weatherIO = new WeatherIO();
		data = weatherIO.readDataFromFile(INPUT_FILE);

	}

	//method to get the month name given the number
	public static String getMonthName(int month) {
		if(month == 1) {
			return "Jan";
		}
		if(month == 2) {
			return "Feb";
		}
		if(month == 3) {
			return "Mar";
		}
		if(month == 4) {
			return "Apr";
		}
		if(month == 5) {
			return "May";
		}
		if(month == 6) {
			return "Jun";
		}
		if(month == 7) {
			return "Jul";
		}
		if(month == 8) {
			return "Aug";
		}
		if(month == 9) {
			return "Sep";
		}
		if(month == 10) {
			return "Oct";
		}
		if(month == 11) {
			return "Nov";
		}
		if(month == 12) {
			return "Dec";
		}

		return "";
	}

	//method to get the month number given the name 
	public static int getMonthNumber(String month) {
		if( month.equalsIgnoreCase("Jan"))
			return 1;

		if( month.equalsIgnoreCase("Feb"))
			return 2;

		if( month.equalsIgnoreCase("Mar"))
			return 3;

		if( month.equalsIgnoreCase("Apr"))
			return 4;

		if( month.equalsIgnoreCase("May"))
			return 5;

		if( month.equalsIgnoreCase("Jun"))
			return 6;

		if( month.equalsIgnoreCase("Jul"))
			return 7;

		if( month.equalsIgnoreCase("Aug"))
			return 8;

		if( month.equalsIgnoreCase("Sep"))
			return 9;

		if( month.equalsIgnoreCase("Oct"))
			return 10;

		if( month.equalsIgnoreCase("Nov"))
			return 11;

		if( month.equalsIgnoreCase("Dec"))
			return 12;

		return 0;
	}


	//Task A1
	// for all data that matches the specified month, get the lowest temperature reading 
	public ITemperature getLowestTempByMonth(String country, int month) {
		ITemperature lowest = null;
		for(ITemperature temperature : data) {

			//if country and month match 
			if(temperature.getCountry().equals(country) && temperature.getMonth().equalsIgnoreCase(getMonthName(month))) {
				if(lowest == null) {
					lowest = temperature;
				}
				//getting the lowest temp
				else if(lowest.getTemperature(false) > temperature.getTemperature(false)) {
					lowest = temperature;
				}

				//if temperatures are equal
				else if(lowest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare years (earlier year wins)
					if(lowest.getYear() > temperature.getYear()) {
						lowest = temperature;

					}

				}
			}
		}
		return lowest;

	}

	// TASK A-1
	// for all data that matches the specified month, get the highest temperature reading
	public ITemperature getHighestTempByMonth(String country, int month) {

		ITemperature highest = null;

		for(ITemperature temperature : data) {

			//if country and month match 
			if(temperature.getCountry().equals(country) && temperature.getMonth().equalsIgnoreCase(getMonthName(month))) {
				if(highest == null) {
					highest = temperature;
				}
				//getting highest temp
				else if(highest.getTemperature(false) < temperature.getTemperature(false)) {
					highest = temperature;
				}

				//if temperatures are equal
				else if(highest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare years
					if(highest.getYear() > temperature.getYear()) {
						highest = temperature;
					}

				}

			}

		}
		return highest;

	}

	// TASK A-2
	// for all data that matches the specified year, get the lowest temperature reading
	public ITemperature getLowestTempByYear(String country, int year) {
		ITemperature lowest = null;
		for(ITemperature temperature : data) {

			//if country and year match 
			if(temperature.getCountry().equals(country) && temperature.getYear() == year) {
				if(lowest == null) {
					lowest = temperature;
				}
				//getting lowest
				else if(lowest.getTemperature(false) > temperature.getTemperature(false)) {
					lowest = temperature;
				}

				//if temperatures are equal
				else if(lowest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare months
					if(getMonthNumber(lowest.getMonth()) > getMonthNumber(temperature.getMonth())){
						lowest = temperature;

					}

				}
			}
		}
		return lowest;
	}

	// TASK A-2
	// for all data that matches the specified year, get the highest temperature reading
	public ITemperature getHighestTempByYear(String country, int year) {
		ITemperature highest = null;

		for(ITemperature temperature : data) {

			//if country and year match 
			if(temperature.getCountry().equals(country) && temperature.getYear() == year) {
				if(highest == null) {
					highest = temperature;
				}
				//getting highest
				else if(highest.getTemperature(false) < temperature.getTemperature(false)) {
					highest = temperature;
				}

				//if temperatures are equal
				else if(highest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare months 
					//go with lower month 
					if(getMonthNumber(highest.getMonth()) > getMonthNumber(temperature.getMonth()) ) {
						highest = temperature;
					}

				}
			}

		}
		return highest;
	}

	// TASK A-3
	// get all temperature data that fall within the given temperature range
	// the set is sorted from lowest to highest temperature
	// input parameter values are in Celsius (getTemperature(false))
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp) {

		TreeSet<ITemperature> tempsInRange = new TreeSet<ITemperature>();

		for(ITemperature temperature : data) {

			//if countries match 
			if(temperature.getCountry().equals(country)){

				//temperatures in celsius must be greater than or equal to lower 
				//bound and less than or equal to upper bound 
				if(temperature.getTemperature(false) >= rangeLowTemp && temperature.getTemperature(false) <= rangeHighTemp){
					tempsInRange.add(temperature);
				}

			}
		}

		return tempsInRange;
	}

	// TASK A-4
	// 1. get the lowest temperature reading amongst all data for that country
	public ITemperature getLowestTempYearByCountry(String country) {

		ITemperature lowest = null;
		for(ITemperature temperature : data) {

			//if countries match
			if(temperature.getCountry().equals(country)) {

				if(lowest == null) {
					lowest = temperature;
				}

				//compare temperatures
				else if(lowest.getTemperature(false) > temperature.getTemperature(false)) {
					lowest = temperature;
				}

				//if temperatures match
				else if(lowest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare year
					if(lowest.getYear() > temperature.getYear()) {
						lowest = temperature;
					}

					//if year are the same 
					else if(lowest.getYear() == temperature.getYear()) {

						//compare months 
						if(getMonthNumber(lowest.getMonth()) > getMonthNumber(temperature.getMonth())) {
							lowest = temperature;
						}
					}
				}
			}
		}

		return lowest;

	}

	// TASK A-4
	// 1. get the highest temperature reading amongst all data for that country
	public ITemperature getHighestTempYearByCountry(String country) {

		ITemperature highest = null;

		for(ITemperature temperature : data) {

			//if countries match
			if(temperature.getCountry().equals(country)) {

				if(highest == null) {
					highest = temperature;
				}

				//compare temperatures
				else if(highest.getTemperature(false) < temperature.getTemperature(false)) {
					highest = temperature;
				}

				//if temperatures match
				else if(highest.getTemperature(false) == temperature.getTemperature(false)) {

					//compare year
					if(highest.getYear() > temperature.getYear()) {
						highest = temperature;
					}

					//if year are the same 
					else if(highest.getYear() == temperature.getYear()) {

						//compare months 
						if(getMonthNumber(highest.getMonth()) > getMonthNumber(temperature.getMonth())) {
							highest = temperature;
						}
					}
				}
			}
		}

		return highest;
	}

	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) {

		TreeMap<String, ITemperature> map = new TreeMap<String, ITemperature>();
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();
		ArrayList<ITemperature> top10Low = new ArrayList<ITemperature>();
		for(ITemperature temperature : data) {
			
			//if months match 
			if(getMonthNumber(temperature.getMonth()) == month) {
				String countryKey = temperature.getCountry();
				//if it is a new country
				if(map.get(countryKey) == null) {
					//add to treemap
					map.put(countryKey, temperature);
				}
				//if its not new
				else {
					if(map.containsKey(countryKey)) {
						//replace with lower temp for that country 
						if(temperature.getTemperature(false) < map.get(countryKey).getTemperature(false)) {
							map.put(countryKey, temperature);
						}
						
					}
				}
			}
		}
		
		//add every ITemperature in the map to a treeset 
		for(ITemperature temperature : map.values()) {
			ordered.add(temperature);
		}
		
		//add the sorted values back to arraylist
		top10Low.addAll(ordered);
		// if the size is less than or equal to 10
				if( top10Low.size() <= 10 ) 
				{
					// return the list as it is
					return top10Low;
				}
				// else trim the list
				else
				{
					// remove the elements apart from top 10
					top10Low.subList(10, top10Low.size()).clear();
					
					// return the top ten list
					return top10Low;
				}
				
	}

	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) {

		TreeMap<String, ITemperature> map = new TreeMap<String, ITemperature>();
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();
		ArrayList<ITemperature> top10High = new ArrayList<ITemperature>();
		
		for(ITemperature temperature : data) {
			//if moths match
			if(getMonthNumber(temperature.getMonth()) == month) {
				String countryKey = temperature.getCountry();
				//if it is a new country
				if(map.get(countryKey) == null) {
					//add to treemap
					map.put(countryKey, temperature);
				}
				//if its not new
				else {
					if(map.containsKey(countryKey)) {
						//replace with higher temp for that country 
						if(temperature.getTemperature(false) > map.get(countryKey).getTemperature(false)) {
							map.put(countryKey, temperature);
						}
						
					}
				}
			}
		}
		//add every ITemperature in the map to a treeset 
		for(ITemperature temperature : map.values()) {
			ordered.add(temperature);
		}
		//add the sorted values back to arraylist
		top10High.addAll(ordered);
		//reverse the arraylist top get top 10
		Collections.reverse(top10High);
		// if the size is less than or equal to 10
				if( top10High.size() <= 10 ) 
				{
					// return the list as it is
					return top10High;
				}
				// else trim the list
				else
				{
					// get the last 10 elements 
					top10High.subList(10, top10High.size()).clear();
					
					//reverse again to put in ascending order
					Collections.reverse(top10High);
					
					// return the top ten list
					return top10High;
				}
		
	}

	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp() {
		
		TreeMap<String, ITemperature> map = new TreeMap<String, ITemperature>();
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();
		ArrayList<ITemperature> top10Low = new ArrayList<ITemperature>();
		
		for(ITemperature temperature : data) {
				String countryKey = temperature.getCountry();
				//if it is a new country
				if(map.get(countryKey) == null) {
					//add to treemap
					map.put(countryKey, temperature);
				}
				//if its not new, replace it with the smaller temp for the country
				else {
					if(map.containsKey(countryKey)) {
						if(temperature.getTemperature(false) < map.get(countryKey).getTemperature(false)) {
							map.put(countryKey, temperature);
						}
						
					}
				}
		}
		//add every ITemperature in the map to a treeset 
		for(ITemperature temperature : map.values()) {
			ordered.add(temperature);
		}
		//add the sorted values back to arraylist
		top10Low.addAll(ordered);
		// if the size is less than or equal to 10
				if( top10Low.size() <= 10 ) 
				{
					// return the list as it is
					return top10Low;
				}
				// else trim the list
				else
				{
					// remove the elements apart from top 10
					top10Low.subList(10, top10Low.size()).clear();
					
					// return the top ten list
					return top10Low;
				}

	}

	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp() {
		TreeMap<String, ITemperature> map = new TreeMap<String, ITemperature>();
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();
		ArrayList<ITemperature> top10High = new ArrayList<ITemperature>();
		
		for(ITemperature temperature : data) {
				String countryKey = temperature.getCountry();
				//if it is a new country
				if(map.get(countryKey) == null) {
					//add to treemap
					map.put(countryKey, temperature);
				}
				//if its not new, replace wth higher temp
				else {
					if(map.containsKey(countryKey)) {
						if(temperature.getTemperature(false) > map.get(countryKey).getTemperature(false)) {
							map.put(countryKey, temperature);
						}
						
					}
				}
		}
		//add every ITemperature in the map to a treeset 
		for(ITemperature temperature : map.values()) {
			ordered.add(temperature);
		}
		//add the sorted values back to arraylist
		top10High.addAll(ordered);
		//reverse array to get top 10
		Collections.reverse(top10High);
		// if the size is less than or equal to 10
				if( top10High.size() <= 10 ) 
				{
					// return the list as it is
					return top10High;
				}
				// else trim the list
				else
				{
					// get the last 10 elements 
					top10High.subList(10, top10High.size()).clear();
					//reverse again to put in ascending order
					Collections.reverse(top10High);
					
					// return the top ten list
					return top10High;
				}
	}

	// TASK B-3
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp) {

		ArrayList<ITemperature> tempsInRange = new ArrayList<ITemperature>();
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();

		for(ITemperature temperature : data) {

			//if the temperatures are within the range
			if(temperature.getTemperature(false) >= lowRangeTemp && temperature.getTemperature(false) <= highRangeTemp) {

				//add it to the treeset
				ordered.add(temperature);
			}
		}
		
		//add it back to arraylist
		tempsInRange.addAll(ordered);

		
		return tempsInRange;
	}

	// TASK C-1
	// 1. the countries with the largest temperature differences(absolute value)of the same month between 2 given years.
	// 2. the return list is sorted from lowest to highest temperature delta
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2) {
		
		TreeSet<ITemperature> ordered = new TreeSet<ITemperature>();
		ArrayList<ITemperature> top10Delta = new ArrayList<ITemperature>();
		ArrayList<ITemperature> tempOfYear1 = new ArrayList<ITemperature>();
		ArrayList<ITemperature> tempOfYear2= new ArrayList<ITemperature>();
		

		for(ITemperature temperature : data) {
			//if the months match 
			if(getMonthNumber(temperature.getMonth()) == month) {
				
				
				//getting temperature from same month for year 1
				if(temperature.getYear() == year1) {
					//all ITemperature with same month with year
					tempOfYear1.add(temperature);

				}
				//getting temperature from same month with  year 2
				if(temperature.getYear() == year2) {
					//all ITemperature from same month with year2
					tempOfYear2.add(temperature);
				}
			}
			
		}
		//for every element in tempOfYear1
		for(int i = 0; i < tempOfYear1.size(); i++) {
			//if the arrays are the same length
			if(tempOfYear1.size() == tempOfYear2.size()) {
				//if countries match
				if(tempOfYear1.get(i).getCountry().equals(tempOfYear2.get(i).getCountry())){
					//get delta 
					double delta = Math.abs(tempOfYear1.get(i).getTemperature(false) - tempOfYear2.get(i).getTemperature(false));
					//create new ITemperature, change temp to delta and year to difference of 2 years
					ITemperature deltaTemps = new Temperature(tempOfYear1.get(i).getCountry(), tempOfYear1.get(i).getCountry3LetterCode(), 
							tempOfYear1.get(i).getMonth(), Math.abs(tempOfYear1.get(i).getYear()-tempOfYear2.get(i).getYear()), delta);
					//add it to treeset to order 
					ordered.add(deltaTemps);
				}
			}
		}
		
		//add data from treeset to 
		top10Delta.addAll(ordered);
		//reverse to get highest to lowest
		Collections.reverse(top10Delta);
		if(top10Delta.size() <= 10) {
			return top10Delta;
		}
		else {
			// get the last 10 elements 
			top10Delta.subList(10, top10Delta.size()).clear();
			//reverse again to put in ascending order
			Collections.reverse(top10Delta);
			
			// return the top ten list
			return top10Delta;
		}
	}
	
	//checks if a country entered is a valid country in the data file 
	public boolean isValidCountry(String country) {
		
		for(ITemperature temperature : data) {
			if(temperature.getCountry().equals(country))
				return true;
		}
		return false;
		
	}
	

	// 1. This method starts the climate-change task activities
	// 2. The ClimateChange methods must be called in the order as listed in the[description section], (first with the Task A 
	//  methods, second are the Task B methods, and third are the Task C methods)
	// 3. For each of the ClimateChange methods that require input parameters, this method must ask the user to
	//  enter the required information for each of the tasks.
	// 4. Each ClimateAnalyzer method returns data, so the data results must be written to data files 
	public void runClimateAnalyzer() {
		
		System.out.println("Start");

		Scanner scanner = new Scanner(System.in);
		String country;
		int month;
		int year;
		double lowerRange;
		double upperRange;
		int year1;
		int year2;
		
		/*
		 * TASK A1
		 */

		System.out.println("Task A1\n");

		//ask for country
		//while the country entered is incorrect, ask again 
		do {
			System.out.println("Enter the name of the country to find the lowest and highest temp by month : ");
			country = scanner.nextLine();
		}while(!(isValidCountry(country)));
		
		
		//ask for month
		//while the month entered is incorrect, ask again 
		do {
			System.out.println("Enter the numeric value of the month : ");
			month = scanner.nextInt();
			scanner.nextLine();
		}
		while(!(month >= 1 && month <= 12));
		

		//getting lowest temp by month
		ITemperature tempA1Lowest = getLowestTempByMonth(country, month);

		//getting highest temp by month
		ITemperature tempA1Highest = getHighestTempByMonth(country, month);

		ArrayList<ITemperature> result = new ArrayList<ITemperature>();
	
				// clear the result
				result.clear();
				//adding results to list 
				result.add(tempA1Lowest);
				result.add(tempA1Highest);
				//creating file
				weatherIO.writeSubjectHeaderInFile("data/taskA1_climate_info.csv", "Task A1: Lowest/Highest temperature for " + country + " in the month of " + getMonthName(month) + ".");
				weatherIO.writeDataToFile("data/taskA1_climate_info.csv", "Temperature,Year,Month,Country,Country Code",
						result);
	    /*
	     * TASK A2
	     */
		System.out.println("Task A2\n");
		//ask for year
		do {
			System.out.println("Enter the name of the country to find the lowest and highest temp by year : ");
			country = scanner.nextLine();
		}while(!(isValidCountry(country)));
		//ask for year 
		do {
			System.out.println("Enter the numeric value of the year : ");
			year = scanner.nextInt();
			scanner.nextLine();
		}while(!(year >= 2001 && year <= 2016));

		
		// get the lowest temperature by year
		ITemperature temperatureA2Lowest = getLowestTempByYear(country, year);
		// get the highest temperature by year
		ITemperature temperatureA2Highest = getHighestTempByYear(country, year);
		
		result.clear();
		//adding data to results 
		result.add(temperatureA2Lowest);
		result.add(temperatureA2Highest);
		weatherIO.writeSubjectHeaderInFile("data/taskA2_climate_info.csv", "Task A2: Lowest/Highest temperature for " + country + " in the year of " + year + ".");
		weatherIO.writeDataToFile("data/taskA2_climate_info.csv","Temperature,Year,Month,Country,Country Code",
				result);
		
		/*
		 * TASK A3
		 */
	
		System.out.println("Task A3\n");
		//ask for country 
		do {
			System.out.println("Enter the name of the country to find the temperatures within range : ");
			country = scanner.nextLine();
		}while(!(isValidCountry(country)));
		//ask for lower range
		do {
			System.out.println("Enter the lower range : ");
			lowerRange = scanner.nextDouble();
			scanner.nextLine();
		}while(!(lowerRange >= -29.664 && lowerRange <= 38.5669));
		//ask for upper range 
		do {
			System.out.println("Enter the upper range : ");
			upperRange = scanner.nextDouble();
			scanner.nextLine();
		}while(!(upperRange >= -29.664 && upperRange <= 38.5669));
		//get data
		TreeSet<ITemperature> temperaturesInRangeA3 = getTempWithinRange(country, lowerRange, upperRange);
		//creating files
		weatherIO.writeSubjectHeaderInFile("data/taskA3_climate_info.csv", "Task A3: Range of temperatures for " + country + " within " + lowerRange + " C and " + upperRange + "C.");
		weatherIO.writeDataToFile("data/taskA3_climate_info.csv","Temperature,Year,Month,Country,Country Code",
				new ArrayList<ITemperature>(temperaturesInRangeA3));
		
		/*
		 * TASK A4
		 */
		System.out.println("Task A4\n");
		//ask for country
		do {
			System.out.println("Enter the name of the country to find the lowest/highest temp by country : ");
			country = scanner.nextLine();
		}while(!(isValidCountry(country)));
		//get data
		ITemperature temperatureA4Lowest = getLowestTempYearByCountry(country);
		ITemperature temperatureA4Highest = getHighestTempYearByCountry(country);
		
		result.clear();
		//adding data
		result.add(temperatureA4Lowest);
		result.add(temperatureA4Highest);
		weatherIO.writeSubjectHeaderInFile("data/taskA4_climate_info.csv","Task A4: Lowest/Highest temperature for " + country + ".");
		weatherIO.writeDataToFile("data/taskA4_climate_info.csv", "Temperature,Year,Month,Country,Country Code",
				result);
		
		/*
		 * TASK B1
		 */
		System.out.println("Task B1\n");
		//get input
		//while the month emteres is incorrect, ask again
		do {
			System.out.println("Enter the month number to get the top 10 lowest/highest: ");
			month = scanner.nextInt();
			scanner.nextLine();
		}while(!(month >= 1 && month <= 12));
		
		//get data
		ArrayList<ITemperature> temperaturesTop10Lowest = allCountriesGetTop10LowestTemp(month);
		ArrayList<ITemperature> temperaturesTop10Highest = allCountriesGetTop10HighestTemp(month);
		
		result.clear();
		//adding data
		result.addAll(temperaturesTop10Lowest);
		result.addAll(temperaturesTop10Highest);
		weatherIO.writeSubjectHeaderInFile("data/taskB1_climate_info.csv","Task B1: The top 10 lowest/highest temperatures amongst all countries for the month of " + getMonthName(month) + ".");
		weatherIO.writeDataToFile("data/taskB1_climate_info.csv", 
				"Temperature,Year,Month,Country,Country Code", 
				result);
		/*
		 * TASK B2
		 */
		System.out.println("Task B2");
		System.out.println("No input needed for task B2\n");
		
		//getting data
		ArrayList<ITemperature> allCountriesTop10LowestTemp = allCountriesGetTop10LowestTemp();
		ArrayList<ITemperature> allCountriesTop10HighestTemp = allCountriesGetTop10HighestTemp();
		
		result.clear();
		//adding results 
		result.addAll(allCountriesTop10LowestTemp);
		result.addAll(allCountriesTop10HighestTemp);
	
		weatherIO.writeSubjectHeaderInFile("data/taskB2_climate_info.csv","Task B2: The top 10 lowest and highest temperatures amongst all countries.");
		weatherIO.writeDataToFile("data/taskB2_climate_info.csv", 
				"Temperature,Year,Month,Country,Country Code", 
				result);
		/*
		 * TASK B3
		 */
		
		System.out.println("Task B3\n");
		//ask for lower range
		do {
			System.out.println("Enter the lower range : ");
			lowerRange = scanner.nextDouble();
			scanner.nextLine();
		}while(!(lowerRange >= -29.664 && lowerRange <= 38.5669));
		//ask for upper range
		do {
			System.out.println("Enter the upper range : ");
			upperRange = scanner.nextDouble();
			scanner.nextLine();
		}while(!(upperRange >= -29.664 && upperRange <= 38.5669));
		
		//getting data 
		ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange = allCountriesGetAllDataWithinTempRange(lowerRange, upperRange);
		result.clear();
		//adding data
		result.addAll(allCountriesGetAllDataWithinTempRange);
		
		weatherIO.writeSubjectHeaderInFile("data/taskB3_climate_info.csv","Task B3: All countries with temperatures between "+lowerRange+"C and "+upperRange+"C");
		weatherIO.writeDataToFile("data/taskB3_climate_info.csv", 
				"Temperature,Year,Month,Country,Country Code", 
				result);
		/*
		 * TASK C1
		 */
		
		System.out.println("Task C1\n");
		//ask for month
		do {
			System.out.println("Enter the month number to get the top 10 delta : ");
			month = scanner.nextInt();
			scanner.nextLine();
		}while(!(month >= 1 && month <= 12));
		//ask for first year
		do {
			System.out.println("Enter the first year (lower range) to find the top 10 delta : ");
			year1 = scanner.nextInt();
			scanner.nextLine();
		}while(!(year1 >= 2001 && year1 <= 2016));
		//ask for second year
		do {
			System.out.println("Enter the second year (upper range) to find the top 10 delta : ");
			year2 = scanner.nextInt();
			scanner.nextLine();
		}while(!(year2 >= 2001 && year2 <= 2016));
		
		//get data
		ArrayList<ITemperature> top10Delta = allCountriesTop10TempDelta(month, year1, year2);
		result.clear();
		//adding data
		result.addAll(top10Delta);
		
		weatherIO.writeSubjectHeaderInFile("data/taskC1_climate_info.csv","Task C1: the countries with the largest temperature differences between "+ year1+ " and "+year2+" in the month "+getMonthName(month));
		weatherIO.writeDataToFile("data/taskC1_climate_info.csv", 
				"Temperature,Year,Month,Country,Country Code", 
				result);
		
		System.out.println("Finished\n");
		System.out.println("Refresh Data Folder");
		
	}
	
	 public static void main(String[] args)
	{
		IClimateAnalyzer climateAnalyzer = new ClimateAnalyzer();
		
		// run 
		climateAnalyzer.runClimateAnalyzer();
	}

}
