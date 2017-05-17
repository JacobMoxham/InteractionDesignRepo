import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	 private Stage primaryStage;
	 private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 this.primaryStage = primaryStage;
	     this.primaryStage.setTitle("Ergs Don't FLoat");

	     initRootLayout();

	     
	     List<List<WeatherObject>> by3Hours = WeatherDataReader.getNextFiveDaysHourly();
	     List<WeatherObject> byDay = WeatherDataReader.getDayForecasts();
	     WeatherObject now = WeatherDataReader.getDataForNow();
	     
	     
	     
	     
	     showBasicFrame();
		
	}
	 
	
	private void initRootLayout(){
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	 public void showBasicFrame() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("BasicFrame.fxml"));
	            AnchorPane basicView = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(basicView);
	            
	            
	            //Give controller access to the main app
	            By3HoursController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }

}
