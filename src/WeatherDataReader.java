import java.io.IOException;
import java.net.MalformedURLException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

public class WeatherDataReader {
	
	//declaration object of "OpenWeatherMap" class
	private static final OpenWeatherMap owm = new OpenWeatherMap("905f034cce04a8e662d05167ca595882");
	
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
			//Gets data for Cam
			cwd = owm.currentWeatherByCityName("Cambridge");
			
			//Populates variables for this time
			
			temp = Float.toString(cwd.getMainInstance().getTemperature());
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
	//For testing only
	/*
	public static void main(String args[]){
		WeatherObject wdo = WeatherDataReader.getDataForNow();
		
	}
	*/
}
