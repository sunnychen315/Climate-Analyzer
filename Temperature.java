package climateChange;

public class Temperature implements ITemperature, Comparable<ITemperature>{

	private String country;
	private String countryCode;
	private String month;
	private int year;
	private double temperature;
	//constructor 
	public Temperature(String country, String countryCode, String month, int year, double temperature) {
		this.country = country;
		this.countryCode = countryCode;
		this.month = month;
		this.year = year;
		this.temperature = temperature;
		
	}

	public String getCountry() {

		return country;
	}

	public String getCountry3LetterCode() {

		return countryCode;
	}

	public String getMonth() {
		return month;
	}

	public int getYear() {

		return year;
	}
	

	public double getTemperature(boolean getFahrenheit) {
		//if true, convert to fahrenheit 
		if(getFahrenheit == true) {
			double tempInFahrenheit = (9.0/5.0)*this.temperature+32;
			return tempInFahrenheit;
		}
		return temperature;
	}
	

	//compareTo method 
	public int compareTo(ITemperature that) {
		
		//if temperatures are equal
		if(Double.compare(this.getTemperature(false), that.getTemperature(false)) == 0) {
			
			//compare country name
			//if countries are the same
			if(this.getCountry().compareTo(that.getCountry()) == 0) {
				
				//compare year
				//if year is the same
				if(this.getYear() == that.getYear()) {
					
					//compare month
					//if months are equal, then objects are the same 
					if(ClimateAnalyzer.getMonthNumber(this.getMonth()) == ClimateAnalyzer.getMonthNumber(that.getMonth())) {
						return 0;
					}
					else
						return ClimateAnalyzer.getMonthNumber(this.getMonth()) - ClimateAnalyzer.getMonthNumber(that.getMonth());
				}
				else
					return this.getYear() - that.getYear();
			}
			else 
				return this.getCountry().compareTo(that.getCountry());
		}
		else
			return Double.compare(this.getTemperature(false), that.getTemperature(false));
	}

}
