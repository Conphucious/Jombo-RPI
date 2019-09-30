package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OverlayTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Text text1 = new Text("\n\nasd");
		Text text2 = new Text("\n\n\ngfd");
		
		StackPane sp = new StackPane();
		Button b1 = new Button("Hello");
		
		sp.getChildren().addAll(text1, text2, b1);
		
		b1.setOnAction(event -> {
			text1.setText("\n\nhgdadg");
		});
		
		stage.setScene(new Scene(sp, 300, 500));
		stage.show();
	}

}
