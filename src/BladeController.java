import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.converter.TimeStringConverter;

public class BladeController {
	@FXML
	private ImageView wind;
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
		
	}
	
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date,Boolean isClickable){
		
	}
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date){
		timeString.set(time);
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}
