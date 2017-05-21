import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SplashScreenApp extends Application {
	
		 private Stage primaryStage;
		 private BorderPane rootLayout;
		 private boolean by3Hours;
		 private ObservableList<Node> dayForecasts;
		 private ObservableList<Node> hourlyForecasts;
		 
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
		     calculatebyDayBlades();
		     calculateby3HoursBlades();
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
		    loader.setLocation(SplashScreenApp.class.getResource("BasicFrame.fxml"));
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
			loader.setLocation(SplashScreenApp.class.getResource("ByDay.fxml"));
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
		
		public void calculateby3HoursBlades(){
			try {
				
				//Calculate values to populate VBox
				hourlyForecasts = FXCollections.observableArrayList();
				FXMLLoader bladeLoader = new FXMLLoader(SplashScreenApp.class.getResource("Blade.fxml"));
				FXMLLoader miniflagLoader = new FXMLLoader(SplashScreenApp.class.getResource("MiniFlag.fxml"));
				
				//Add miniflag to top
				try{
					Node miniflag = (Node) miniflagLoader.load();
					MiniFlagController miniflagController = (MiniFlagController) miniflagLoader.getController();
					miniflagController.setMainApp(this);
					Flag flag = GetFlag.FlagColour();
													
					miniflagController.instantiate(flag);
					hourlyForecasts.add(miniflag);
				}catch (FlagNotFoundException f){
					System.out.println("Flag not found");
					f.printStackTrace();
				}
				
				//Current weather
				//"Today"
				Label todayLabel = new Label("Today");
				todayLabel.setAlignment(Pos.CENTER);
				todayLabel.setFont(new Font("Arial", 24.0));
				hourlyForecasts.add(todayLabel);
				
				//Blade with current weather
				WeatherObject currentWeather = this.getCurrentWeatherData();
				Node thisBlade = (Node) bladeLoader.load();
				BladeController todayController = (BladeController) bladeLoader.getController();
				todayController.setMainApp(this);
				DateFormat dateFormat = new SimpleDateFormat("HH:mm");
				Date timeNow = new Date();
				String now = dateFormat.format(timeNow);
				todayController.instantiate(currentWeather.getTemp(),now,currentWeather.getIconURL(),currentWeather.getWindDegree(),currentWeather.getWindSpeed(),currentWeather.getDate());
				
				hourlyForecasts.add(thisBlade);
				
				//Allows us to read in another instance
				bladeLoader.setRoot(null);
				bladeLoader.setController(null);
				
				//By 3 hours data
				boolean notFirst = false;
				
				//test value
				//int total = 0;
				for(List<WeatherObject> l : this.getBy3HoursData()){
					//Adds the correct week Day label
					if (notFirst){
						Label label = new Label(l.get(0).getDate().split("\\s")[0]);
						label.setAlignment(Pos.CENTER);
						label.setFont(new Font("Arial", 24.0));
						hourlyForecasts.add(label);
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
						todayController.setMainApp(this);
						todayController.instantiate(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate());
						
						hourlyForecasts.add(thisBlade);
						
						//Allows us to read in another instance
						bladeLoader.setRoot(null);
						bladeLoader.setController(null);
						//total++;
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public ObservableList<Node> get3HourlyForecasts(){
			return hourlyForecasts;
		}
		public void calculatebyDayBlades(){
			try {
				//Calculate values for by day VBox
				dayForecasts = FXCollections.observableArrayList();
				FXMLLoader loader = new FXMLLoader(SplashScreenApp.class.getResource("Blade.fxml"));
				
				//By day data
				int day = 0;
				boolean clickable=true;
				for(WeatherObject w: this.getByDayData()){
					
					if(day>4){
						clickable = false;
					}
					
					Node thisBlade = (Node) loader.load();
					BladeController cont = (BladeController) loader.getController();
					cont.setMainApp(this);
					cont.instantiate(w.getTemp(),w.getTime(),w.getIconURL(),w.getWindDegree(),w.getWindSpeed(),w.getDate(),clickable);
					
					dayForecasts.add(thisBlade);
					
					//Allows us to read in another instance
					loader.setRoot(null);
					loader.setController(null);
					day++;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public ObservableList<Node> getByDayForecasts(){
			return dayForecasts;
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
