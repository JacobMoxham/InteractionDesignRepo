import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.TimeStringConverter;

public class BladeController {
	
	private boolean clickable;
	//Tracks swipe starts
	private double lastX;
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
	private Button bladeButton;
	@FXML
	private ImageView bladeImage;
	
	@FXML
	private StringProperty windDegreeString = new SimpleStringProperty();
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
	@FXML
	private StringProperty bladeString = new SimpleStringProperty();
	
	//Reference to application
	private SplashScreenApp mainApp;
	
	public void initialize(){
		
	}
	public BladeController(){
		
	}
	/*
	 * Method for instantiation changed for efficiency
	 * 
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date,Boolean clickable){
		//Set string variables
		timeString.set(time);
		weatherImage.set(iconURL);
		degreesString.set(temp);
		dayString.set(date);
		windText.set(windSpeed);
		windDegreeString.set(windDegree);
		this.clickable = clickable;
		if (clickable){
			bladeString.set("500px-Rowing_Tan.png");
		}else{
			bladeString.set("500px-Rowing_Blade_Cambridge Blue.png");
		}
	}
	
	public BladeController(String temp,String time,String iconURL,String windDegree,String windSpeed,String date){
		this(temp,time,iconURL,windDegree,windSpeed,date,false);
	}*/
	public void instantiate(String temp,String time,String iconURL,String windDegree,String windSpeed,String date,Boolean clickable){
		//Set string variables
		timeString.set(time);
		weatherImage.set(iconURL);
		degreesString.set(temp);
		dayString.set(date);
		windText.set(windSpeed);
		windDegreeString.set(windDegree);
		this.clickable = clickable;
		if (clickable){
			bladeString.set("500px-Rowing_Tan.png");
		}else{
			bladeString.set("500px-Rowing_Blade_Cambridge Blue.png");
		}
		
		//Set the components based on the string objects
		this.time.setText(timeString.get());
		this.windTextObj.setText(windText.get().split("\\.")[0]);
		this.weather.setImage(new Image(weatherImage.get()));
		this.day.setText(dayString.get().split("\\s")[0]);
		this.degrees.setText(degreesString.get()+"\u00b0"+"C");
		this.windImageObj.setImage(new Image("winddirection.png"));
		//Rotates the wind image based on the wind degree
		this.windImageObj.setRotate(Double.parseDouble(windDegreeString.get()));
		this.bladeImage.setImage(new Image(bladeString.get()));
	}
	public void instantiate(String temp,String time,String iconURL,String windDegree,String windSpeed,String date){
		instantiate(temp,time,iconURL,windDegree,windSpeed,date,false);
	}
	
	// CHANGES THESE
	private final double BIG_PICTURE_HEIGHT = 25.0;
	private final double BLADE_HEIGHT = 9.1;
	private final double LABEL_HEIGHT = 3.0;
	
	@FXML
	public void bladePress() throws IOException {
		if(clickable){
			// get blade day
			String dayText = day.getText();

			// special case -- today (WARNING: UNREADABLE LINE OF CODE AHEAD :)))
			dayText = dayText.toUpperCase().equals(LocalDate.now().getDayOfWeek().toString().substring(0, 3)) ? "Tod" : dayText;
			
			// switch to by 3 hours page
			AnchorPane basePane = mainApp.showBy3Hours();
			
			// get flag scroll object
			ObservableList<Node> childNodes = basePane.getChildren();
			ScrollPane flagScroll = (ScrollPane) childNodes.get(1);
			
			// get vbox (where flags and labels are stored
			VBox vbox = (VBox) ((AnchorPane) flagScroll.getContent()).getChildren().get(0);
			
			double y = BIG_PICTURE_HEIGHT;
			
			// calculate correct y position
			for (Node node : vbox.getChildren()) {
				if (node.getClass() == ImageView.class) {

				}
				else if (node.getClass() == Label.class) {
					// stop when at correct day
					if (((Label) node).getText().substring(0, 3).equals(dayText)) break;
					System.out.println(((Label) node).getText().substring(0, 3));
					System.out.println(dayText);
					
					y += LABEL_HEIGHT;
				} else {
					System.out.println("flag encountered");
					y += BLADE_HEIGHT;
				}
			}

	        // scroll to correct location (scrolling values range from 0 to 1)
	        double height = flagScroll.getContent().getBoundsInLocal().getHeight();
	        flagScroll.setVvalue(y/height);

	        // just for usability
	        flagScroll.requestFocus();
		}
	}
	
	public void setMainApp(SplashScreenApp mainApp){
		this.mainApp = mainApp;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	//Handles swipes (drags)
	@FXML
	private void handleDragAction(MouseEvent event) throws IOException {
		
		// ARBITRARY -- CHANGE IF REQUIRED
		double minDragDistance = 50;
		
		// record x on start of drag
		if (event.getEventType().equals(MouseDragEvent.MOUSE_PRESSED)) {
			lastX = event.getScreenX();
		} else
		// determine whether to change screen or not on end of drag
		if (event.getEventType().equals(MouseDragEvent.MOUSE_RELEASED)) {
			// calculate change in x over mouse drag
			double deltaX = event.getScreenX() - lastX;
			
			// ignore if less than threshold
			if (Math.abs(deltaX) < minDragDistance) return;
			
			// determine if user is scrolling right (+ve)
			boolean isScrollLeft = (deltaX >= 0);
		     
		    System.out.println(""); 
	    	if (isScrollLeft){
	    		if (mainApp.isBy3Hours()){
	    			mainApp.showBasicFrame();
	    		}else{
	    			mainApp.showBy3Hours();
	    		}
	    		
	    	}else{
	    		if (mainApp.isBy3Hours()){
	    			mainApp.showByDay();
	    		}else{
	    			return;
	    		}
	    	}

           
		}
		
	}
}
