package climateChange;

import java.util.ArrayList;

interface IWeatherIO {
	
	public ArrayList<ITemperature>readDataFromFile(String fileName); //read all data from the weatherdata file
	public void writeSubjectHeaderInFile(String filename, String subject);
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList);
	

	
}
