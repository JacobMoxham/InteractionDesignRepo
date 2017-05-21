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
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
	     Stage stage=null; 
	     Parent root=null;

	     if(event.getSource() == buttonGoBackToSplashScreen){
	    	 
	    	 stage=(Stage) buttonGoBackToSplashScreen.getScene().getWindow();
		     root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
		  // create a new scene with root and set the stage
			 Scene scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
	     }else{
	    	 throw new IOException("Fail at clicking button");
	     }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	
}
