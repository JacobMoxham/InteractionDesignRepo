import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.converter.TimeStringConverter;

public class BladeController {
	@FXML
	private ImageView windImageObj;
	@FXML
	private Text windTextObj;
	@FXML
	private ImageView weather;
	@FXML
	private Text degrees;
	@FXML
	private Text time;
	@FXML
	private Text day;
	
	@FXML
	private StringProperty windImage = new SimpleStringProperty();
	@FXML
	private StringProperty windText = new SimpleStringProperty();
	@FXML
	private StringProperty weatherImage= new SimpleStringProperty();
	@FXML
	private StringProperty degreesString= new SimpleStringProperty();
	@FXML
	private StringProperty timeString= new SimpleStringProperty();
	@FXML
	private StringProperty dayString= new SimpleStringProperty();
	
	//Reference to application
	private MainApp mainApp;
	
	public void initialize(){
		this.time.setText(timeString.get());
		this.windTextObj.setText(windText.get().split("\\.")[0]);
		this.weather.setImage(new Image(weatherImage.get()));
		this.day.setText(dayString.get().split("\\s")[0]);
		this.degrees.setText(degreesString.get());
		
		// aidens addition - windDegrees and setWindImage
		//this.windImageObj = 
		
	}
	
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date,Boolean isClickable){
		
	}
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date){
		timeString.set(time);
		weatherImage.set(iconURL);
		degreesString.set(temp);
		//TODO: set wind image
		dayString.set(date);
		windText.set(windSpeed);
		
		// aidens addition - rotate according to degrees
		windImageObj.setRotate(Double.parseDouble(windDegree));
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}
