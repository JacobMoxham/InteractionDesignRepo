import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class By3HoursController {
	@FXML
	private VBox weatherForecasts;
	
	//Reference to application
	private MainApp mainApp;
	
	@FXML
	private void initialise(){
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;	
		try {
			//Populate VBOX
			ObservableList<Node> forecasts = FXCollections.observableArrayList();
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Blade.fxml"));

			//Current weather
			Label todayLabel = new Label("Today");
			todayLabel.setAlignment(Pos.CENTER);
			todayLabel.setFont(new Font("Arial", 24.0));
			forecasts.add(todayLabel);
			WeatherObject currentWeather = WeatherDataReader.getDataForNow();
			Node thisBlade = (Node) loader.load();
			BladeController cont = (BladeController) loader.getController();
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date timeNow = new Date();
			String now = dateFormat.format(timeNow);
			cont.instantiate(currentWeather.getTemp(),now,currentWeather.getIconURL(),currentWeather.getWindDegree(),currentWeather.getWindSpeed(),currentWeather.getDate());
			
			forecasts.add(thisBlade);
			
			//Allows us to read in another instance
			loader.setRoot(null);
			loader.setController(null);
			
			//By 3 hours data
			boolean notFirst = false;
			
			//test value
			//int total = 0;
			for(List<WeatherObject> l : WeatherDataReader.getNextFiveDaysHourly()){
				//Adds the correct week Day label
				if (notFirst){
					Label label = new Label(l.get(0).getDate().split("\\s")[0]);
					label.setAlignment(Pos.CENTER);
					label.setFont(new Font("Arial", 24.0));
					forecasts.add(label);
				}
				//Ensures no weekday label for today
				notFirst = true;

				for(WeatherObject w: l){
					//--Less efficient version of the hack around below--
					//FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Blade.fxml"));
					//loader.setController(new BladeController(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate()));
					//Node thisBlade = (Node) loader.load();
					
					
					thisBlade = (Node) loader.load();
					cont = (BladeController) loader.getController();
					cont.instantiate(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate());
					
					forecasts.add(thisBlade);
					
					//Allows us to read in another instance
					loader.setRoot(null);
					loader.setController(null);
					//total++;
				}
			}
			
			//test
			//System.out.println("No Blades:" + total);
			
			weatherForecasts.getChildren().addAll(forecasts);
			
			//test
			//System.out.println("No children of VBox: "+weatherForecasts.getChildren().size());
			//System.out.println();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
}
