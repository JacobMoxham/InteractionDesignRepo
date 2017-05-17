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
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AidenTestController implements Initializable {
	
	@FXML
	private Button button1;
	
	@FXML
	private Button button2;
	
	@FXML 
	private AnchorPane anchor1;
	
	@FXML
	private AnchorPane anchor2;
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		
	     Stage stage; 
	     Parent root;
	     if(event.getSource() == button1){
	    	 
	        //get reference to the button's stage         
	        stage=(Stage) button1.getScene().getWindow();
	        
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("AidenTest2.fxml"));
	        
	     }
	     else {
	    	 
	       stage=(Stage) button2.getScene().getWindow();
	       root = FXMLLoader.load(getClass().getResource("AidenTest.fxml"));
	       
	     }
	     
	     // create a new scene with root and set the stage
	     Scene scene = new Scene(root);
	     stage.setScene(scene);
	     stage.show();
	     
	}
	
	@FXML
	private void handleSwipeAction(ActionEvent event) throws IOException {
		
		System.out.println("SWIPE EVENT OCCURED.");
		
	     Stage stage; 
	     Parent root;
	     if(event.getSource() == anchor1){
	    	 
	    	 // only go to screen 2 if swiping right
	    	 if (!event.getEventType().equals(SwipeEvent.SWIPE_RIGHT)) return;
	    		 
	 	     //get reference to the button's stage         
	 	     stage=(Stage) anchor1.getScene().getWindow();
	 	        
	 	     //load up OTHER FXML document
	 	     root = FXMLLoader.load(getClass().getResource("AidenTest2.fxml"));
	     }
	     else {
	    	 
	       // only go to screen 1 if swiping left
	       if (!event.getEventType().equals(SwipeEvent.SWIPE_LEFT)) return;
	    
	       stage=(Stage) anchor2.getScene().getWindow();
	       root = FXMLLoader.load(getClass().getResource("AidenTest.fxml"));
	       
	     }
	     
	     // create a new scene with root and set the stage
	     Scene scene = new Scene(root);
	     stage.setScene(scene);
	     stage.show();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
