import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MiniFlagController {
	@FXML
	private ImageView flagObj;
	@FXML
	private Text flagTextObj;
	@FXML
	private Button miniFlagInfoButton;
	
	@FXML
	private StringProperty flagText = new SimpleStringProperty();
	@FXML
	private StringProperty flagString = new SimpleStringProperty();

	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
	     Stage stage=null; 
	     Parent root=null;
	     if(event.getSource() == miniFlagInfoButton){   
	    	 stage=(Stage) miniFlagInfoButton.getScene().getWindow();
	    	 root = FXMLLoader.load(getClass().getResource("FlagsInfoScreen.fxml"));
		     Scene scene = new Scene(root);
		     stage.setScene(scene);
		     stage.show();
	     }
	     else
	     {
	    	 throw new IOException("Button wasn't clicked inf SplashScreen");
	     }
	}
	
	//reference to application
	private SplashScreenApp mainApp;
	
	public void initialise(){
		
	}
	
	public MiniFlagController(){
		
	}
	
	public void instantiate(Flag flag){
		Image flagImg=null;
		
		switch (flag) {
			case GREEN: {
				flagString.set("flaggreensmall.png");
				flagText.set("Everyone can row.");
				break;
			}
			case YELLOW: {
				flagString.set("flagyellowsmall.png");
				flagText.set("Top crews only.");
				break;
			}
			case REDYELLOW: {
				flagString.set("flagyellowredsmall.png");
				flagText.set("Crews with permission only.");
				break;
			}
			case RED: {
				flagString.set("flagredsmall.png");
				flagText.set("No one can row.");
				break;
			}
			case NOTOPERATIONAL: {
				//TODO: handle this case
				break;
			}
		}
		
		flagTextObj.setText(flagText.get());
		flagObj.setImage(new Image(flagString.get()));
	}
	
	public void setMainApp(SplashScreenApp mainApp){
		this.mainApp = mainApp;
	}
}
