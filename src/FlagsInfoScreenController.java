import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FlagsInfoScreenController implements Initializable {
	@FXML
	private Button buttonGoBackToSplashScreen;
	
	private static SplashScreenApp mainApp;
	
	public void  setMainApp(SplashScreenApp mainApp){
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {

	     if(event.getSource() == buttonGoBackToSplashScreen){
	    	 if(pScreen.getPrevious().equals("splash"))
	    	mainApp.showBasicFrame();
	    	 else{
	    		 mainApp.showBy3Hours();
	    	 }
	     }else{
	    	 throw new IOException("Fail at clicking button");
	     }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}


	
	
}
