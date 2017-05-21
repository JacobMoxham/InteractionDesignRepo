import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SplashScreenApp extends Application {
	
		 private Stage primaryStage;
		 private BorderPane rootLayout;
		 private boolean by3Hours;
		 
		 private List<List<WeatherObject>> by3HoursData;
		 private List<WeatherObject> byDayData;
		 private WeatherObject currentWeatherData;
		 
		@FXML
		ImageView imageView;
		 
		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			 this.primaryStage = primaryStage;
		     this.primaryStage.setTitle("Ergs Don't FLoat");
		
		     initRootLayout();     
		     showBasicFrame();
			//initialise data lists
		     by3HoursData = WeatherDataReader.getNextFiveDaysHourly();
		     byDayData = WeatherDataReader.getDayForecasts();
		     currentWeatherData = WeatherDataReader.getDataForNow();
		}
		
		private void initRootLayout(){
			 try {
		            // Load root layout from fxml file.
		            FXMLLoader loader = new FXMLLoader();
		            loader.setLocation(SplashScreenApp.class.getResource("RootLayout.fxml"));
		            rootLayout = (BorderPane) loader.load();
		
		            // Show the scene containing the root layout.
		            Scene scene = new Scene(rootLayout);
		            primaryStage.setScene(scene);
		            primaryStage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		public void showFlagsInfo() throws IOException{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SplashScreenApp.class.getResource("FlagsInfoScreen.fxml"));
			AnchorPane basicView = (AnchorPane) loader.load();
			rootLayout.setCenter(basicView);
			FlagsInfoScreenController ssc = (FlagsInfoScreenController) loader.getController();
			ssc.setMainApp(this);
		}
		public void showBasicFrame() throws IOException {
		    // Load splash screen
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SplashScreenApp.class.getResource("SplashScreen.fxml"));
			AnchorPane basicView = (AnchorPane) loader.load();
			rootLayout.setCenter(basicView);
			SplashScreenController ssc = (SplashScreenController) loader.getController();
			ssc.setMainApp(this);
		}
		public AnchorPane showBy3Hours() throws IOException {
			by3Hours = true;
			System.out.println(Boolean.toString(by3Hours));
		
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(MainApp.class.getResource("BasicFrame.fxml"));
		    AnchorPane basicView = (AnchorPane) loader.load();
		
		    // Set basic view into the center of root layout.
		    rootLayout.setCenter(basicView);
		    
		    
		    //Give controller access to the main app
		    By3HoursController controller = (By3HoursController) loader.getController();
		    controller.setMainApp(this);
		    return basicView;
		}
		public AnchorPane showByDay() throws IOException {
			by3Hours = false;
			System.out.println(Boolean.toString(by3Hours));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("ByDay.fxml"));
			AnchorPane basicView = (AnchorPane) loader.load();
		
			// Set basic view into the center of root layout.
			rootLayout.setCenter(basicView);
		   
		   
			//Give controller access to the main app
		 		ByDayController controller = (ByDayController) loader.getController();
		 		controller.setMainApp(this);
		
		 		return basicView;
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
		
		public boolean isBy3Hours() {
			return by3Hours;
		}
		
		public void setBy3Hours(boolean by3Hours) {
			this.by3Hours = by3Hours;
		}

		public List<List<WeatherObject>> getBy3HoursData() {
			return by3HoursData;
		}

		public void setBy3HoursData(List<List<WeatherObject>> by3HoursData) {
			this.by3HoursData = by3HoursData;
		}

		public List<WeatherObject> getByDayData() {
			return byDayData;
		}

		public void setByDayData(List<WeatherObject> byDayData) {
			this.byDayData = byDayData;
		}

		public WeatherObject getCurrentWeatherData() {
			return currentWeatherData;
		}

		public void setCurrentWeatherData(WeatherObject currentWeatherData) {
			this.currentWeatherData = currentWeatherData;
		}

}
