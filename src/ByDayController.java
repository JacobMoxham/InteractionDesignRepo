import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ByDayController {
	@FXML
	private VBox weatherForecasts;
	
	//Reference to application
	private SplashScreenApp mainApp;
	
	@FXML
	private void initialise(){
	}
	
	public void setMainApp(SplashScreenApp mainApp){
		this.mainApp = mainApp;	
		
		//populate VBox
		weatherForecasts.getChildren().addAll(mainApp.getByDayForecasts());
			
		
	}

}
