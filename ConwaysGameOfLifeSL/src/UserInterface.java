import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;

/**
 * User interface class showing employing JavaFX and fetching data from backend.
 * 
 * 
 * @author Lock, Samuel; Bollineni, Dharani; Hasegawa, Natsuki; Shi, Zong. 
 * @version: 1.0
 */

public class UserInterface extends Application {

	/**
	 * Defines the speed of the starting animation
	 */
	private int worldSpeed = 200;
	/**
	 * Controls whether world is running or stopped.
	 */
	private boolean startStop = false;
	/**
	 * Initialises dynamic array object.
	 */
	private DynamicArray dy;

	public void start(Stage mainStage) throws Exception {

		mainStage.setTitle("Conway's Game of Life");
		// INITIAL ONSCREEN DISPLAY
		Text welcomeText = new Text("Conway's Game of Life"); 
		welcomeText.setStyle("-fx-font: 30 ariel;");
		
		
		VBox menuBox = new VBox();
		createMenu(menuBox);

		GridPane lifeGrid = new GridPane();
		createGridPane(lifeGrid);

		VBox bottomPane = new VBox(); // CREAteS MAIN VERTICAL BOX
		
		
		bottomPane.getChildren().add(welcomeText);
		bottomPane.setAlignment(Pos.CENTER);
		
		HBox secondPane = new HBox(); // Creates HBOX to hold menu and gridpane.
		createSecondLayer(secondPane);
		secondPane.getChildren().add(menuBox); // ADDS MENU TO MAIN VERTICAL BOX
		secondPane.getChildren().add(lifeGrid); // Adds lifegrid to bottompane beneath menu box
		
		
		bottomPane.getChildren().add(secondPane);
		Scene mainScene = new Scene(bottomPane); // ADDS VBOX TO SCENE
		mainStage.setWidth(900);
		mainStage.setHeight(800);

		mainStage.setScene(mainScene); // ADDS ENTIRE SCENE TO MAINSTAGE

		// START MENU BUTTONS
		Button startBtn = new Button("Start");
		buttonStyle(startBtn, menuBox);
		Button restartBtn = new Button("Restart");
		buttonStyle(restartBtn, menuBox);
		Button repeatBtn = new Button("Repeat Pattern");
		buttonStyle(repeatBtn, menuBox);
		Button toadBtn = new Button("Toad Pattern");
		buttonStyle(toadBtn, menuBox);
		Button germBtn = new Button("Germs Pattern");
		buttonStyle(germBtn, menuBox);
		Button spaceshipBtn = new Button("Spaceship Pattern");
		buttonStyle(spaceshipBtn, menuBox);
		Button closeBtn = new Button("Close Program");
		buttonStyle(closeBtn, menuBox);
		
		
		
		menuBox.getChildren().add(startBtn);
		//menuBox.getChildren().add(restartBtn);
		menuBox.getChildren().add(repeatBtn);
		menuBox.getChildren().add(toadBtn);
		menuBox.getChildren().add(germBtn);
		menuBox.getChildren().add(spaceshipBtn);
		menuBox.getChildren().add(closeBtn);
		dy = new DynamicArray();
		
		
		// SETS SPEED OF ANIMATION update
		KeyFrame frame = new KeyFrame(Duration.millis(worldSpeed), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				lifeGrid.getChildren().clear();
				
				int maxSize = 68;
					if (maxSize > dy.getArr().length) {
							maxSize = dy.getArr().length;
						}
					
				for(int i=0;i<maxSize;i++)
				{
					for(int j=0;j<maxSize;j++)
					{
			
							lifeGrid.add(dy.getArr()[i][j].getRect(), i ,j);
						
					}
				}
				
					dy.extendsArray();
			
				
				
				dy.updateStatus();
				dy.swapArr();


				if(dy.getNum() < dy.getArr().length)
		        {
		           dy.shrinkArray();
		        }
				
				

			}
		});

		lifeGrid.getChildren().removeAll();
		Timeline tl = TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame)
				.build();

		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				if (startStop == false) {
					tl.play();
					startStop = true;
					startBtn.setText("Pause");
					
					lifeGrid.getChildren().removeAll();
				} else if (startStop == true) {
					tl.pause();
					startBtn.setText("Start");
					startStop = false;
				}
			}

		});
		
		repeatBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				dy.arrIni[3][4].setCurrentStatus(true);
				dy.arrIni[4][4].setCurrentStatus(true);
				dy.arrIni[5][4].setCurrentStatus(true);	
			}

		});
		
		toadBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				dy.arrIni[2][2].setCurrentStatus(true);
			    dy.arrIni[2][3].setCurrentStatus(true);
			    dy.arrIni[2][4].setCurrentStatus(true);
			    dy.arrIni[3][1].setCurrentStatus(true);
			    dy.arrIni[3][2].setCurrentStatus(true);
			    dy.arrIni[3][2].setCurrentStatus(true);
			    dy.arrIni[3][3].setCurrentStatus(true);

			}

		});
		
		germBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				dy.arrIni[3][1].setCurrentStatus(true);
                dy.arrIni[4][1].setCurrentStatus(true);
                dy.arrIni[5][1].setCurrentStatus(true);    
                dy.arrIni[3][2].setCurrentStatus(true);
                dy.arrIni[2][3].setCurrentStatus(true);
                dy.arrIni[2][4].setCurrentStatus(true);
                dy.arrIni[2][5].setCurrentStatus(true);
                dy.arrIni[4][4].setCurrentStatus(true);
                dy.arrIni[5][4].setCurrentStatus(true);
                dy.arrIni[6][4].setCurrentStatus(true);
                dy.arrIni[5][6].setCurrentStatus(true);
                dy.arrIni[1][7].setCurrentStatus(true);
                dy.arrIni[2][7].setCurrentStatus(true);
                dy.arrIni[3][7].setCurrentStatus(true);


			}

		});
		
		
		
		spaceshipBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
		
		        dy.arrIni[3][3].setCurrentStatus(true);
		        dy.arrIni[3][4].setCurrentStatus(true);
		        dy.arrIni[3][5].setCurrentStatus(true);
		        dy.arrIni[3][6].setCurrentStatus(true);
		        dy.arrIni[3][7].setCurrentStatus(true);
		        dy.arrIni[3][8].setCurrentStatus(true);
		        dy.arrIni[4][2].setCurrentStatus(true);
		        dy.arrIni[4][8].setCurrentStatus(true);
		        dy.arrIni[5][8].setCurrentStatus(true);
		        dy.arrIni[6][2].setCurrentStatus(true);
		        dy.arrIni[6][7].setCurrentStatus(true);
		        dy.arrIni[7][4].setCurrentStatus(true);
		        dy.arrIni[7][5].setCurrentStatus(true);

			}

		});
		
		
		
	
		
		
		
		
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				mainStage.close();

			}

		});
		
		restartBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	
                lifeGrid.getChildren().removeAll();
                //dy=null;
                
                //dy= new DynamicArray();
                
                bottomPane.getChildren().clear();
                mainStage.close();
                Platform.runLater( () -> {
                    try {
                        new UserInterface().start( new Stage() );
                    } catch (Exception e) {
                        // 
                        e.printStackTrace();
                    
                    
                	}
                   
        		});
		
		}
		});
           
		

		mainStage.show(); // SHOWS MAINSTAGE ON SCREEN
	}

	/**
	 * Controls visual features of Menu Box, padding etc.
	 * 
	 * @param menuBox
	 *            - passes HBox from inside stage.
	 */

	
	/**
	 * Stylises menu vBox; amends children.
	 * @param menuBox create menuBox.
	 */
	public void createMenu(VBox menuBox) {
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(20);
		menuBox.prefHeight(700);
		menuBox.setMinHeight(700);
		menuBox.setMinWidth(80);
		menuBox.prefWidth(80);
		menuBox.setPadding(new Insets (10));
	}
	
	/**
	 * 
	 * Stylises buttons when used.
	 * 
	 * @param butt - button object
	 * @param menu - menubox. 
	 */
	public void buttonStyle(Button butt, VBox menu) {
		butt.setPrefWidth(100);
	}
	
	
	/**
	 * Sets padding on object to ensure no side by sides.
	 * @param s variable of HBox.
	 */
	public void createSecondLayer(HBox s) {
		s.setPadding( new Insets(15));
	}
	
	/**
	 * Stylises gridpane, sets alignment.
	 * @param lifeGrid lifeGrid.
	 */

	public void createGridPane(GridPane lifeGrid) {
		lifeGrid.setMinSize(700, 700);
		lifeGrid.setAlignment(Pos.CENTER);
		
	}
	
	
	/**
	 * Restarts program on request. 
	 * 
	 * @param lifeGrid restart lifeGrid.
	 * @param bottomPane restart bottomPane.
	 * @param mainStage restart mainStage.
	 */
	public void restart(GridPane lifeGrid, VBox bottomPane, Stage mainStage) {
		
                
                lifeGrid.getChildren().removeAll();
        
                
                bottomPane.getChildren().clear();
                mainStage.close();
                Platform.runLater( () -> {
                    try {
                        new UserInterface().start( new Stage() );
                    } catch (Exception e) {
                        // 
                        e.printStackTrace();
                    }
                } );
	}
	
	/**
	 * Launches JavaFX
	 * 
	 * @param args main method for lunching.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Returns animation speed for timelinebuilder
	 *
	 * @return worldSpeed for Timeline Builder.
	 */
	public int getWorldSpeed() {
		return worldSpeed;
	}

	/**
	 * Sets animation speed for timelinebuilder
	 * 
	 * @param worldSpeed the new worldSpeed.
	 */
	public void setWorldSpeed(int worldSpeed) {
		this.worldSpeed = worldSpeed;
	}

}

/** GLIDER CASE
 		arrIni[3][2].setCurrentStatus(true);
		arrIni[4][3].setCurrentStatus(true);
		arrIni[4][4].setCurrentStatus(true);
		arrIni[5][2].setCurrentStatus(true);
		arrIni[5][3].setCurrentStatus(true);
		
		*/
