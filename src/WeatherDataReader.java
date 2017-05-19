import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.HourlyForecast;
import net.aksingh.owmjapis.HourlyForecast.Forecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

public class WeatherDataReader {
	
	//declaration object of "OpenWeatherMap" class
	private static final OpenWeatherMap owm = new OpenWeatherMap("905f034cce04a8e662d05167ca595882");
	
	
	//Const for degrees conversion
	private final static double farenheightProportionality = 5.0/9.0;
	
	public static WeatherObject getDataForNow() {
       
		String temp;
		String time;
		String windSpeed;
		String date;
		String iconURL;
		String windDegree;
		
        // get Weather data for now in Cambridge
        CurrentWeather cwd;
		try {
			//Gets data for Cambridge
			cwd = owm.currentWeatherByCityName("Cambridge");
			
			//Populates variables for this time
			
			temp = Double.toString((cwd.getMainInstance().getTemperature() - 32.0)*farenheightProportionality);
			date = cwd.getDateTime().toString();
			time = String.valueOf(cwd.getDateTime()).split("\\s")[3];
			windSpeed = String.valueOf(cwd.getWindInstance().getWindSpeed());
			windDegree = String.valueOf(cwd.getWindInstance().getWindDegree());
			iconURL = "http://openweathermap.org/img/w/" + cwd.getWeatherInstance(0).getWeatherIconName() +".png";
			
			//Creates weather object for weather at this time
			WeatherObject weatherNow = new WeatherObject(date,time,temp,iconURL,windSpeed,windDegree);
			return weatherNow;
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
		
		return null;
	}
	public static List<List<WeatherObject>> getNextFiveDaysHourly(){
		String temp;
		String time;
		String windSpeed;
		String date;
		String iconURL;
		String windDegree;
		
		ArrayList<List<WeatherObject>> days = new ArrayList<>();
		
        // get Weather data for next few days in Cambridge
        HourlyForecast hwd;
		try {
			//Constant
			final double farenheightProportionality = 5.0/9.0;
			//Gets data for Cambridge
			hwd = owm.hourlyForecastByCityName("Cambridge");
			int n = hwd.getForecastCount();
			ArrayList<WeatherObject> dayForecast = new ArrayList<>();
			Date lastDay = hwd.getForecastInstance(0).getDateTime();
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			for(int i =0; i < n; i++){
				//Gets the next forecast
				Forecast forecast = hwd.getForecastInstance(i);
				
				//populates the variables for this forecast 
				temp = Double.toString((forecast.getMainInstance().getTemperature() - 32.0)*farenheightProportionality);
				date = forecast.getDateTime().toString();
				time = String.valueOf(forecast.getDateTime()).split("\\s")[3];
				windSpeed = String.valueOf(forecast.getWindInstance().getWindSpeed());
				windDegree = String.valueOf(forecast.getWindInstance().getWindDegree());
				iconURL = "http://openweathermap.org/img/w/" + forecast.getWeatherInstance(0).getWeatherIconName() +".png";
				//Creates weather object for weather at this time
				WeatherObject weatherNow = new WeatherObject(date,time,temp,iconURL,windSpeed,windDegree);
				
				//Checks if this forecast is on the same day as the last one
				cal1.setTime(lastDay);
				cal2.setTime(forecast.getDateTime());
				boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
				
				//Sets lastDay to this day
				lastDay = forecast.getDateTime();
				
				if(!sameDay){
					//Add this day to days and start a new day
					days.add(dayForecast);
					dayForecast = new ArrayList<WeatherObject>();
					
				}
				dayForecast.add(weatherNow);
				
			}
	
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Test code
		/*for(List<WeatherObject> ws: days){
			for(WeatherObject w: ws){
				System.out.println(w.getDate());
			}
			System.out.println();
		}*/
		
		//Returns a list of forecasts for each of the next 5 days in order
		return days;
	}
	public static List<WeatherObject> getDayForecasts(){
		String temp;
		String time;
		String windSpeed;
		String date;
		String iconURL;
		String windDegree;
		
		ArrayList<WeatherObject> daysForecast = new ArrayList<>();
		
        DailyForecast dwd;
		try {
			//Gets data for Cambridge
			dwd = owm.dailyForecastByCityName("Cambridge", (byte) 15);
			int n = dwd.getForecastCount();
			
			for(int i =0; i < n; i++){
				//Gets the next forecast
				net.aksingh.owmjapis.DailyForecast.Forecast forecast = dwd.getForecastInstance(i);
				
				//populates the variables for this forecast 
				temp = Double.toString((forecast.getTemperatureInstance().getDayTemperature() -32.0)*farenheightProportionality);
				date = forecast.getDateTime().toString();
				time = String.valueOf(forecast.getDateTime()).split("\\s")[3];
				windSpeed = String.valueOf(forecast.getWindSpeed());
				windDegree = String.valueOf(forecast.getWindDegree());
				iconURL = "http://openweathermap.org/img/w/" + forecast.getWeatherInstance(0).getWeatherIconName() +".png";
				//Creates weather object for weather at this time
				WeatherObject weatherNow = new WeatherObject(date,time,temp,iconURL,windSpeed,windDegree);
				
				
				
				daysForecast.add(weatherNow);
				
			}
	
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Test code
		/*for(WeatherObject w: daysForecast){
			System.out.println(w.getDate());
		}*/
		
		//Returns a list of forecasts for each of the next 5 days in order
		return daysForecast;
	}
	//For testing only
	
	/*public static void main(String args[]){
		List<WeatherObject> wd1 = WeatherDataReader.getDayForecasts();
		List<List<WeatherObject>> wd2 = WeatherDataReader.getNextFiveDaysHourly();
		
	}*/
	
}
