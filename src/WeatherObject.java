import java.util.Arrays;

public class WeatherObject {
	private String date;
	private String time;
	private String temp;
	private String iconURL;
	private String windSpeed;
	private String windDegree;
	private boolean clickable;
	
	//Constructor setting all private state
	public WeatherObject(String date, String time, String temp, String iconURL,String windSpeed, String windDegree){
		//Sets all state to passed values
		this(date,time,temp,iconURL,windSpeed,windDegree,false);

	}
	public WeatherObject(String date, String time, String temp, String iconURL,String windSpeed, String windDegree, boolean clickable){
		//Sets all state to passed values
		this.date = date;
		
		//trims time
		String[] timeArray = time.split(":");
		time = timeArray[0] +":"+timeArray[1];
		
		this.time = time;
		this.temp = temp.split("\\.")[0];
		this.iconURL = iconURL;
		this.windSpeed = windSpeed;
		this.windDegree = windDegree;
		this.clickable = clickable;

	}

	//Getters and setters for private state
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

	
	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDegree() {
		return windDegree;
	}

	public void setWindDegree(String windDegree) {
		this.windDegree = windDegree;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
}
