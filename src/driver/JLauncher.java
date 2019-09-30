package driver;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import menu.base.MainMenu;
import system.anim.EmoteEngine;
import system.internal.Controller;
import test.Configuration;

public class JLauncher extends Application {

	private static StackPane box;
	private static Text primaryText;
	private static Text secondaryText;
	private static Text statusText;
	private static EmoteEngine emote;
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		initialize();
		new MainMenu();
		setProperties(stage);
	}

	public void initialize() {
		box = new StackPane();
		primaryText = new Text();
		secondaryText = new Text();
		statusText = new Text();
		emote = new EmoteEngine(primaryText);
		scene = new Scene(box, 480, 320);
	}

	public void setProperties(Stage primaryStage) {
		box.getChildren().addAll(primaryText, secondaryText, statusText);
		box.requestFocus();

		scene.setFill(Color.BLACK);
		//scene.setCursor(Cursor.NONE);
		
		StackPane.setAlignment(primaryText, Pos.CENTER);
		StackPane.setAlignment(statusText, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(secondaryText, Pos.BOTTOM_LEFT);

		primaryStage.setScene(scene);
		primaryStage.show();
		//primaryStage.setFullScreen(true);
	}
	
	public static StackPane getBox() {
		return box;
	}

	public static Text getText() {
		return primaryText;
	}
	
	public static Text getSecondaryText() {
		return secondaryText;
	}
	
	public static Text getStatusText() {
		return statusText;
	}
	
	public static EmoteEngine getEmote() {
		return emote;
	}

}
