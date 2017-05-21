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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {
	
	@FXML
	private Button splashScreenInfoButton;
	
	@FXML
    private ImageView imageView;
	
	@FXML
    private ImageView windImageView;
	
	@FXML
    private ImageView weatherImageView;
	
	@FXML
	private Label timeLabel;
	
	@FXML
	private Label tempLabel;
	
	@FXML 
	private Label speedWindLabel;
	
	Flag currentFlag;
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
	     Stage stage=null; 
	     Parent root=null;
	     if(event.getSource() == splashScreenInfoButton){   
	        stage=(Stage) splashScreenInfoButton.getScene().getWindow();
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
	

	private void setFlag(){

		Image FlagImagePath=null;
		try 
		{
		
			currentFlag = GetFlag.FlagColour();
		
		switch (currentFlag) {
			case GREEN:
				FlagImagePath = new Image("flaggreen.png");
				break;
			case YELLOW:
				FlagImagePath = new Image("flagyellow.png");
				break;
			case REDYELLOW:
				FlagImagePath = new Image("flagyellowred.png");
				break;
			case RED:
				FlagImagePath = new Image("flagred.png");
				break;
			case NOTOPERATIONAL:
				//TODO: handle this case
				break;
				
		
		}
		} catch (FlagNotFoundException e) {
			e.printStackTrace();
		}
		
		imageView.setImage(FlagImagePath);
	}
	
	private void setData() {
		
		WeatherObject wo = WeatherDataReader.getDataForNow();
		
		timeLabel.setText(wo.getTime());
		String temp = wo.getTemp() + "\u00b0"+"C";
		tempLabel.setText(temp);
		
		weatherImageView.setImage(new Image(wo.getIconURL()));
		
		windImageView.setImage(new Image("winddirection.png"));
		speedWindLabel.setText(wo.getWindSpeed().split("\\.")[0]);
		windImageView.setRotate(Double.parseDouble(wo.getWindDegree()));	
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try //Set Flag in Splash screen if flag changed
		{
			if(currentFlag != GetFlag.FlagColour()){
				setFlag();
			}
		} 
		catch (FlagNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setData(); // Set Data in splash screen
	}



	
	
}
