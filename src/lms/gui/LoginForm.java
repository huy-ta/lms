package lms.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginForm extends Application {
	
	private double xOffset = 0, yOffset = 0;
	public BorderPane root;
	public Scene scene;
	
	// ============== Left Grid Components ==============
	public GridPane leftGrid;
	public Text welcomeTitle,
				 welcomeCaption,
				 welcomeDescription;
	public Image logo;
	public ImageView logoContainer;
	// ==================================================

	// ============== Main Grid Components ==============
	public GridPane mainGrid;
	public final int mainGridCols = 20,
					   mainGridRows = 5;
	
	public ToolBar toolBar;
	public Text mainGridTitle;
	public Text mainGridCaption;
	
		// -------------- Login Grid Components --------------
		public GridPane loginGrid;
		public Text loginTitle;
		public TextField userNameField;
		public PasswordField pwBox;
		public Button loginBtn;
		public HBox loginBtnContainer;
		
		private BooleanProperty firstTime;
	
		public Text actionText;
		public Timeline flasher;
		public PseudoClass flashHighlight;
		
		public Image loading;
		public ImageView loadingView;
		
		public Text titleSeperator;
		
		public Text signUpTitle;
		// ---------------------------------------------------
		
	// ==================================================

	
	// ============== Toolbar Setup ============== 
    class WindowButtons extends HBox {

    	public WindowButtons() {
            Button closeBtn = new Button("x");
            closeBtn.setId("close-button");
            closeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            this.getChildren().add(closeBtn);
        }
    	
    }
	// ==================================================
    
    // ================ Left Grid Setup =================
    public void setUpLeftGrid() throws FileNotFoundException {
        leftGrid = new GridPane();
		leftGrid.setAlignment(Pos.TOP_CENTER);
		leftGrid.setHgap(10);
		leftGrid.setVgap(5);
		leftGrid.setPadding(new Insets(50, 50, 10, 50));
		leftGrid.prefHeight(600);
		leftGrid.setMinHeight(600);
		leftGrid.prefWidth(450);
		leftGrid.setMinWidth(450);
		leftGrid.setId("left-grid");
		
		logo = new Image(new FileInputStream("/home/huytq/prj1code/LMS/img/precise-logo.png"));
		logoContainer = new ImageView(logo);
		logoContainer.setFitHeight(120);
		logoContainer.setFitWidth(120);
		logoContainer.setPreserveRatio(true);
		GridPane.setHalignment(logoContainer, HPos.CENTER);
		leftGrid.add(logoContainer, 0, 0, 10, 1);
		
		welcomeTitle = new Text("Welcome to Precise!");
		welcomeTitle.setFill(Color.WHITE);
		welcomeTitle.getStyleClass().add("title-text");
		GridPane.setHalignment(welcomeTitle, HPos.CENTER);
		leftGrid.add(welcomeTitle, 0, 1, 10, 1);
		
		welcomeCaption = new Text("Ta Quang Buu Library LMS");
		welcomeCaption.setFill(Color.rgb(200, 200, 200, 0.9));
		GridPane.setHalignment(welcomeCaption, HPos.CENTER);
		welcomeCaption.getStyleClass().add("small-text");
		leftGrid.add(welcomeCaption, 0, 2, 10, 1);
		
		welcomeDescription = new Text("Welcome to Ta Quang Buu Library of Hanoi University of Science and Technology,"
				+ " one of the largest libraries in Vietnam. With a rich source of technical documents, the library"
				+ " currently has a bewildering variety of collections, serving a large number of readers."
				+ "\n\nIn order to access the library's resources, you have to use the Online Library Catalog, which is part of"
				+ " Precise, the Library Management System."
				+ "\n\nShould any questions arise, contact us at:"
				+ "\n✉ Email: bklib@mail.hust.edu.vn"
				+ "\n✆ Hotline: (84-4) 8692243");
		welcomeDescription.setFill(Color.rgb(200, 200, 200, 0.9));
		GridPane.setHalignment(welcomeCaption, HPos.CENTER);
		welcomeDescription.getStyleClass().add("small-text");
		leftGrid.add(welcomeDescription, 0, 5, 10, 1);
		welcomeDescription.setWrappingWidth(340);
		welcomeDescription.setTextAlignment(TextAlignment.JUSTIFY);
		GridPane.setMargin(welcomeDescription, new Insets(30, 0, 20, 0));
    }
	// ==================================================

    // ================ Main Grid Setup =================
    public void setUpMainGrid() throws FileNotFoundException {
		mainGrid = new GridPane(); 
		mainGrid.setAlignment(Pos.TOP_CENTER);
		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		int mainGridWidth = 750, mainGridHeight = 600;
		mainGrid.setMinWidth(mainGridWidth);
		mainGrid.setPrefWidth(mainGridWidth);
		mainGrid.setMinHeight(mainGridHeight);
		mainGrid.setPrefHeight(mainGridHeight);
		mainGrid.setPadding(new Insets(-10, 21, 25, 25));
        RowConstraints rowConst1 = new RowConstraints(50);
        mainGrid.getRowConstraints().add(rowConst1); 
        for (int i = 0; i < mainGridCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / mainGridCols);
            mainGrid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < mainGridRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / mainGridRows);
            mainGrid.getRowConstraints().add(rowConst);         
        }
		
        toolBar = new ToolBar();
        int height = 25;
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        toolBar.setStyle(null);
        toolBar.getItems().add(new WindowButtons());
        toolBar.setId("tool-bar");
        mainGrid.add(toolBar, 20, 0);

		mainGridTitle = new Text("Precise");
		GridPane.setHalignment(mainGridTitle, HPos.CENTER);
		GridPane.setValignment(mainGridTitle, VPos.TOP);
		mainGridTitle.setTextAlignment(TextAlignment.CENTER);
		mainGridTitle.getStyleClass().add("title-text");
		mainGridTitle.setId("brand-name");
		mainGridTitle.setFill(Color.rgb(100, 111, 152));
		mainGrid.add(mainGridTitle, 0, 1, 21, 1);
		
		mainGridCaption = new Text("Library Management System");
		GridPane.setHalignment(mainGridCaption, HPos.CENTER);
		GridPane.setValignment(mainGridCaption, VPos.BOTTOM);
		mainGridCaption.setTextAlignment(TextAlignment.CENTER);
		mainGridCaption.getStyleClass().add("medium-text");
		mainGridCaption.setFill(Color.rgb(130, 137, 163));
		mainGrid.add(mainGridCaption, 0, 1, 21, 1);
		
		//mainGrid.setGridLinesVisible(true);
        
        setUpLoginGrid();
		mainGrid.add(loginGrid, 0, 3, 21, 1);
    }
    	// --------------- Login Grid Setup -------------
    	public void setUpLoginGrid() throws FileNotFoundException {
            firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    		loginGrid = new GridPane(); 
    		loginGrid.setAlignment(Pos.BOTTOM_CENTER);
    		loginGrid.setHgap(2);
    		loginGrid.setVgap(10);
    		loginGrid.setMinHeight(300);
    		loginGrid.setPrefHeight(300);
    		loginGrid.setMinWidth(750);
    		loginGrid.setPrefWidth(750);
            final int loginNumCols = 20;
            final int loginNumRows = 5;
            for (int i = 0; i < loginNumCols; i++) {
                ColumnConstraints colConst = new ColumnConstraints();
                colConst.setPercentWidth(100.0 / loginNumCols);
                loginGrid.getColumnConstraints().add(colConst);
            }
            for (int i = 0; i < loginNumRows; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(100.0 / loginNumRows);
                loginGrid.getRowConstraints().add(rowConst);         
            }
            
            loginTitle = new Text("Login");
    		loginTitle.setFill(Color.rgb(100, 111, 152));
    		loginTitle.getStyleClass().add("title-text");
    		GridPane.setHalignment(loginTitle, HPos.LEFT);
    		loginGrid.add(loginTitle, 5, 0, 2, 1);
    		
    		titleSeperator = new Text("|");
    		titleSeperator.setFill(Color.rgb(180, 187, 200));
    		titleSeperator.getStyleClass().add("title-text");
    		GridPane.setHalignment(titleSeperator, HPos.CENTER);
    		loginGrid.add(titleSeperator, 7, 0, 1, 1);

    		signUpTitle = new Text("Sign up");
    		signUpTitle.setFill(Color.rgb(180, 187, 200));
    		signUpTitle.getStyleClass().add("title-text");
    		GridPane.setHalignment(loginTitle, HPos.LEFT);
    		loginGrid.add(signUpTitle, 8, 0, 3, 1);
    		signUpTitle.getStyleClass().add("clickable");

    		userNameField = new TextField();
    		userNameField.setPromptText("Please enter your email here");
    		loginGrid.add(userNameField, 5, 1, 10, 1);
            userNameField.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
                if(newValue && firstTime.get()){
                    root.requestFocus(); // Delegate the focus to root
                    firstTime.setValue(false); // Variable value changed for future references
                }
            });
            userNameField.setId("username");

    		pwBox = new PasswordField();
    		pwBox.setPromptText("Please enter your password here");
    		loginGrid.add(pwBox, 5, 2, 10, 1);
    		pwBox.setId("password");
    		    		
    		loginBtn = new Button("");
    		loginBtnContainer = new HBox(30);
    		loginBtnContainer.setPrefSize(70, 70);
    		loginBtn.setPrefSize(70, 70);
    		loginBtnContainer.setAlignment(Pos.BOTTOM_CENTER);
    		loginBtnContainer.getChildren().add(loginBtn);
    		loginBtn.setId("login-button");
    		loginGrid.add(loginBtnContainer, 13, 3, 3, 1);
    		
    		actionText = new Text();
            actionText.setFill(Color.rgb(100, 111, 152));
            actionText.setVisible(false);
            loginGrid.add(actionText, 5, 3, 10, 1);
    		GridPane.setHalignment(actionText, HPos.CENTER);
            
    		loading = new Image(new FileInputStream("/home/huytq/prj1code/LMS/img/loading.gif"));
    		loadingView = new ImageView(loading);
    		loadingView.setFitHeight(100);
    		loadingView.setFitWidth(100);
    		loadingView.setPreserveRatio(true);
    		loadingView.setVisible(false);
    		GridPane.setHalignment(loadingView, HPos.CENTER);
    		loginGrid.add(loadingView, 5, 4, 10, 1);
            
            loginBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    userNameField.setDisable(true);
                    pwBox.setDisable(true);
                    loginBtn.setVisible(false);
                    
                    actionText.setVisible(true);
                    actionText.setText("Processing your request. Please wait...");
                    flasher.play();
                    loadingView.setVisible(true);
                }
            });
            
            //loginGrid.setGridLinesVisible(true);
    	}
    	// ----------------------------------------------

	// ==================================================

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        root = new BorderPane();
        root.setId("border-pane");
        
        setUpLeftGrid();
        root.setLeft(leftGrid);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.requestFocus();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        setUpMainGrid();
        root.setCenter(mainGrid);
        
        flashHighlight = PseudoClass.getPseudoClass("flash-highlight");
        flasher = new Timeline(
        	new KeyFrame(Duration.seconds(0.2), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.8));
        	}),
        	new KeyFrame(Duration.seconds(0.3), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.6));
        	}),
        	new KeyFrame(Duration.seconds(0.4), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.4));
        	}),
        	new KeyFrame(Duration.seconds(0.5), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.2));
        	}),
        	new KeyFrame(Duration.seconds(0.6), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0));
        	}),
        	new KeyFrame(Duration.seconds(0.7), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.2));
        	}),
        	new KeyFrame(Duration.seconds(0.8), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.4));
        	}),
        	new KeyFrame(Duration.seconds(0.9), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 0.7));
        	}),
        	new KeyFrame(Duration.seconds(1.0), e -> {
        		actionText.setFill(Color.rgb(100, 111, 152, 1));
        	})
        );
        flasher.setCycleCount(Animation.INDEFINITE);
        
		scene = new Scene(root, 1200, 600);
		Font.loadFont(LoginForm.class.getResource("Righteous-Regular.ttf").toExternalForm(), 10);
		scene.getStylesheets().add(LoginForm.class.getResource("LoginForm.css").toExternalForm());
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Josefin+Sans:400,700");
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Precise LMS");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}