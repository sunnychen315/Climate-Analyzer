package climateChange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherIO implements IWeatherIO{



	public ArrayList<ITemperature> readDataFromFile(String fileName) {

		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			
			//skip header line 
			scanner.nextLine();
			ArrayList<ITemperature> fileData = new ArrayList<ITemperature>();
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				
				//splitting data whenever there is a comma 
				String sections [] = data.split(","); 
				
				//creating an instance of temperature using the different sections of the csv
				ITemperature temperature = new Temperature(sections[3].trim(), sections[4].trim(), 
						sections[2].trim(), Integer.parseInt(sections[1].trim()), Double.parseDouble(sections[0].trim()));
				
				//adding each line, which is an instance of temperature into arrayList
				fileData.add(temperature); 

			}
			scanner.close();
			return fileData;
		} 
		catch (FileNotFoundException e) {
			System.out.println("Cannot open file");
			return null;
		}



	}

	// 1. write the subject header before dumping data returned from each ClimateAnalyzer method
	// 2. a subject header is to be written for each ClimateAnalyzer method call
	public void writeSubjectHeaderInFile(String fileName, String subject) {

		try{
			PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName)));
			//writing heading
			pw.println(subject); 
			pw.close();
		}  
		catch (Exception e) {
			System.out.println("Cannot open file");
		}


	}
	// 1. file name should be called “taskXX_climate_info.csv”where XX will be replaced by the task id: A1, A2, etc
	// 2. use this method to store the temperature info(for each ClimateAnalyzer task)
	// a) one row for each temperature data object (i.e. all fields in one row (each comma delimited))
	//  b) similar to the original input data file)
	// 3. temperature value should be formatted to use a maximum of 2 decimal places
	// 4.temperature field should also show the Fahrenheit value (using decimal rules above)
	// a) the temperature field should look like i.e. 21.34(C) 70.42(F)

	public void writeDataToFile(String fileName, String topic, ArrayList<ITemperature> theWeatherList) {
		

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName), true));
			pw.println(topic);
			for(ITemperature temp : theWeatherList) {

				double temperatureF = temp.getTemperature(true);
				double temperatureC = temp.getTemperature(false);
				//rounding temperatures to 2 decimal points 
				temperatureF = roundDown2(temperatureF);
				temperatureC = roundDown2(temperatureC);
				
				//printing each line 
				pw.println(temperatureC + "(C) ," + 
						temperatureF + "(F) ," + 
						temp.getYear() + ", " + 
						temp.getMonth() + ", " + 
						temp.getCountry() + ", " + 
						temp.getCountry3LetterCode());

			}
			
			pw.close();	
			
		} 
		catch (Exception e) {
			System.out.println("Cannot open file");
		}

	}

	//method to round down to 2 numbers x
	public static double roundDown2(double d) {
		return Math.floor(d * 1e2) / 1e2;
	}

}
