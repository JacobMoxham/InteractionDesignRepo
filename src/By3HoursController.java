import java.io.IOException;
import java.util.List;

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
				//Ensures not weekday label for today
				notFirst = true;
				for(WeatherObject w: l){
					FXMLLoader loader = new FXMLLoader();
					loader.setController(new BladeController(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate()));
					loader.setLocation(MainApp.class.getResource("Blade.fxml"));
					Node thisBlade = (Node) loader.load();
					forecasts.add(thisBlade);
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
