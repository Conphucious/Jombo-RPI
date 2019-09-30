package system.internal;

import driver.JLauncher;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class MenuCtrl {
	
	public void setMainText(String title) {
		JLauncher.getText().setText(title);
		JLauncher.getText().setFill(Color.WHITE);
		JLauncher.getText().setFont(new Font(150));
	}
	
	public void setHeader(String title) {
		JLauncher.getSecondaryText().setText(title);
		JLauncher.getSecondaryText().setFill(Color.DARKGREY);
		JLauncher.getSecondaryText().setFont(new Font(12));
	}
	
	public void setStatus(String title) {
		JLauncher.getStatusText().setText(title);
		JLauncher.getStatusText().setFill(Color.gray(.3));
		JLauncher.getStatusText().setFont(new Font(10));
	}
	
	// custom setters with custom options
	
	public void setMainText(String title, Color fill, Font font) {
		JLauncher.getText().setText(title);
		JLauncher.getText().setFill(fill);
		JLauncher.getText().setFont(font);
	}
	
	public void setHeader(String title, Color fill, Font font) {
		JLauncher.getSecondaryText().setText(title);
		JLauncher.getSecondaryText().setFill(fill);
		JLauncher.getSecondaryText().setFont(font);
	}
	
	public void setStatus(String title, Color fill, Font font) {
		JLauncher.getStatusText().setText(title);
		JLauncher.getStatusText().setFill(fill);
		JLauncher.getStatusText().setFont(font);
	}
	
	// only text and font
	public void setMainText(String title, Font font) {
		JLauncher.getText().setText(title);
		JLauncher.getText().setFill(Color.WHITE);
		JLauncher.getText().setFont(font);
	}
	
	public abstract void gpioEventHandler();

}
