import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class MiniFlagController {
	@FXML
	private ImageView flagObj;
	@FXML
	private Text flagTextObj;
	
	@FXML
	private StringProperty flagText = new SimpleStringProperty();
	@FXML
	private StringProperty flagString = new SimpleStringProperty();

	
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
