import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
		for(List<WeatherObject> l : WeatherDataReader.getNextFiveDaysHourly()){
			weatherForecasts.getChildren().addAll(new Button("Click Me"));
			//TODO: Add label
		}
		
	}
}
