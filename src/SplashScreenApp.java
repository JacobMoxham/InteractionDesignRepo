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
	 
	@FXML
	ImageView imageView;
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 this.primaryStage = primaryStage;
	     this.primaryStage.setTitle("Ergs Don't FLoat");

	     initRootLayout();     
	     showBasicFrame();
		
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
	 		 FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BasicFrame.fxml"));
            AnchorPane basicView = (AnchorPane) loader.load();

            // Set basic view into the center of root layout.
            rootLayout.setCenter(basicView);
            
            
            //Give controller access to the main app
            By3HoursController controller = loader.getController();
            controller.setMainApp(this);
            return basicView;
	    }
	 	public AnchorPane showByDay() throws IOException {
	 		by3Hours = false;
	 		FXMLLoader loader = new FXMLLoader();
	 		loader.setLocation(MainApp.class.getResource("ByDay.fxml"));
	 		AnchorPane basicView = (AnchorPane) loader.load();

	 		// Set basic view into the center of root layout.
	 		rootLayout.setCenter(basicView);
           
           
	 		//Give controller access to the main app
	 		ByDayController controller = loader.getController();
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

}
