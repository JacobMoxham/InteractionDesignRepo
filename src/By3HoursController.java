import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
		
		
		
		//TODO: Populate VBOX
		
			
		try {
			ObservableList<Node> forecasts = FXCollections.observableArrayList();
			
			for(List<WeatherObject> l : WeatherDataReader.getNextFiveDaysHourly()){
				for(WeatherObject w: l){
					FXMLLoader loader = new FXMLLoader();
					loader.setController(new BladeController(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate()));
					loader.setLocation(MainApp.class.getResource("Blade.fxml"));
					Node thisBlade = (Node) loader.load();
					forecasts.add(thisBlade);
				}
				//TODO: Add label
				Label label = new Label("This is a day label");
				forecasts.add(label);
			}
			weatherForecasts.getChildren().addAll(forecasts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
}
