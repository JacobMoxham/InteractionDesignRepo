import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class By3HoursController {
	
	@FXML
	private VBox weatherForecasts;
	
	//Reference to application
	private SplashScreenApp mainApp;
	
	@FXML
	private void initialise(){
	}
	
	public void setMainApp(SplashScreenApp splashScreenApp){
		//Copy reference to main application
		this.mainApp = splashScreenApp;	
		//Populate VBOX with retrieved 3 hour forecasts (put into blade objects)
		weatherForecasts.getChildren().addAll(mainApp.get3HourlyForecasts());
	}

}


