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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class By3HoursController {
	
	@FXML
	private VBox weatherForecasts;
	
	@FXML
	private ScrollPane flagScroll;
	
	//Reference to application
	private SplashScreenApp mainApp;
	
	@FXML
	private void initialise(){
	}
	
	public void setMainApp(SplashScreenApp splashScreenApp){
		this.mainApp = splashScreenApp;	
		try {
		
			//Populate VBOX
			ObservableList<Node> forecasts = FXCollections.observableArrayList();
			FXMLLoader bladeLoader = new FXMLLoader(MainApp.class.getResource("Blade.fxml"));
			FXMLLoader miniflagLoader = new FXMLLoader(MainApp.class.getResource("MiniFlag.fxml"));
			
			//Add miniflag to top
			try{
				Node miniflag = (Node) miniflagLoader.load();
				MiniFlagController miniflagController = (MiniFlagController) miniflagLoader.getController();
				Flag flag = GetFlag.FlagColour();
												
				miniflagController.instantiate(flag);
				forecasts.add(miniflag);
			}catch (FlagNotFoundException f){
				System.out.println("Flag not found");
				f.printStackTrace();
			}
			
			//Current weather
			//"Today"
			Label todayLabel = new Label("Today");
			todayLabel.setAlignment(Pos.CENTER);
			todayLabel.setFont(new Font("Arial", 24.0));
			forecasts.add(todayLabel);
			
			//Blade with current weather
			WeatherObject currentWeather = WeatherDataReader.getDataForNow();
			Node thisBlade = (Node) bladeLoader.load();
			BladeController todayController = (BladeController) bladeLoader.getController();
			todayController.setMainApp(mainApp);
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date timeNow = new Date();
			String now = dateFormat.format(timeNow);
			todayController.instantiate(currentWeather.getTemp(),now,currentWeather.getIconURL(),currentWeather.getWindDegree(),currentWeather.getWindSpeed(),currentWeather.getDate());
			
			forecasts.add(thisBlade);
			
			//Allows us to read in another instance
			bladeLoader.setRoot(null);
			bladeLoader.setController(null);
			
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
					
					
					thisBlade = (Node) bladeLoader.load();
					todayController = (BladeController) bladeLoader.getController();
					todayController.setMainApp(mainApp);
					todayController.instantiate(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate());
					
					forecasts.add(thisBlade);
					
					//Allows us to read in another instance
					bladeLoader.setRoot(null);
					bladeLoader.setController(null);
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


