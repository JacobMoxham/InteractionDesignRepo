import javafx.fxml.FXML;
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
		weatherForecasts.getChildren().addAll(MainApp.class.getResource("Blade.fxml"));
	}
}
