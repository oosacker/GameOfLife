import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * User interface class showing employing JavaFX and fetching data from backend. 
 * 
 * 13Aug19
 * @author Lock, Samuel; Bollineni Dharani, 
 * @contact locksamu@myvuw.ac.nz;dharanich1985@gmail.com
 * @create 13Aug19 --	
 */



//TODO Add favicon
//TODO Create Array object. Animate updating of array object.  Create rectangles based on true or flag cell data. if cell is alive, create red rect; if fase, create white.
//TODO Display rectangles.
//TODO CLEAR ALL RECTANGLES, then crceate new rectnalges based on current position. 



public class UserInterface extends Application {

	/**
	 * Defines the speed of the starting animation
	 */
	private int worldSpeed = 16;
	/**
	 * Controls whether world is running or stopped.
	 */
	private boolean startStop = false;
	
	
	
	
	public void start(Stage mainStage) throws Exception {
		
		// INITIAL ONSCREEN DISPLAY
		Text welcomeText = new Text(); //TODO Do we need this for anything???!
		welcomeText.setText("Click grid to set square");
		HBox menuBox = new HBox();
		createMenu(menuBox); 
		
		GridPane lifeGrid = new GridPane();
		createGrid(lifeGrid);
		
		VBox bottomPane = new VBox(); // CreAteS MAIN VERTICAL BOX
		bottomPane.getChildren().add(menuBox); // ADDS MENU TO MAIN VERTICAL BOX
		bottomPane.getChildren().add(lifeGrid); // Adds lifegrid to bottompane beneath menu box
		Scene mainScene = new Scene(bottomPane); // ADDS VBOX TO SCENE
		mainStage.setWidth(700);
		mainStage.setHeight(700);
		
		mainStage.setScene(mainScene); //ADDS ENTIRE SCENE TO MAINSTAGE
		
		// START MENU BUTTONS
		Button startBtn = new Button("Start");
		menuBox.getChildren().add(startBtn);
		
		// SETS SPEED OF ANIMATION
		KeyFrame frame = new KeyFrame(Duration.millis(getWorldSpeed()), new EventHandler<ActionEvent>() {
		
			public void handle(ActionEvent event) {
				
				//TODO UPDATE 2D Array
				//TODO CreateGrid(); // REDRAW GRID, How do you call from inside a hidden method?????
				
				
			}
		});
		
		Timeline tl = TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build();
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				if (startStop == false) {
					tl.play();
					startStop = true;
					System.out.println("Click Start");
				} else if(startStop == true) {
					tl.pause();
					startStop = false;
					System.out.println("Click Pause");
				}
			}
			
		});
		
		
		
		
		mainStage.show(); // SHOWS MAINSTAGE ON SCREEN
	}
	
	
	

	/**
	 * Controls visual features of Menu Box, padding etc.
	 * @param menuBox - passes HBox from inside stage.
	 */
	
	public void createMenu(HBox menuBox) {
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(20);
		
		//TODO SET SPACING PADDING, BUTTON WIDTH
	}
	
	/**
	 * 
	 * Creates the features of the gridpane that displays the Cells and their current position.
	 * @param lifeGrid - passes the grid we are using to display living cells 
	 */
	public void createGrid(GridPane lifeGrid) {
		//TODO Fetch Array and display items in grid at current position.
		
		
	}


	/**
	 * Launches JavaFX
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	/**
	 * Returns animation speed for timelinebuilder
	 * @return worldSpeed for Timeline Builder
	 */
	public int getWorldSpeed() {
		return worldSpeed;
	}
	
	/**
	 * Sets animation speed for timelinebuilder
	 * @param int new worldSpeed
	 */
	public void setWorldSpeed(int worldSpeed) {
		this.worldSpeed = worldSpeed;
	}

}
