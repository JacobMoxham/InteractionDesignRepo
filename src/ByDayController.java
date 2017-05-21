import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
		try {
			//Populate VBOX
			ObservableList<Node> forecasts = FXCollections.observableArrayList();
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Blade.fxml"));
			
			//By day data
			int day = 0;
			boolean clickable=true;
			for(WeatherObject w: mainApp.getByDayData()){
				//--Less efficient version of the hack around below--
				//FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Blade.fxml"));
				//loader.setController(new BladeController(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate()));
				//Node thisBlade = (Node) loader.load();
				
				if(day>4){
					clickable = false;
				}
				
				Node thisBlade = (Node) loader.load();
				BladeController cont = (BladeController) loader.getController();
				cont.setMainApp(mainApp);
				cont.instantiate(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate(),clickable);
				
				forecasts.add(thisBlade);
				
				//Allows us to read in another instance
				loader.setRoot(null);
				loader.setController(null);
				day++;
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
