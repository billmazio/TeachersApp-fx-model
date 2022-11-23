package gr.aueb.cf.gui;

import java.io.IOException;

import gr.aueb.cf.controllers.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VersionFX {
	private static Label titleLbl;
	private static Button bt;
	private static VBox vb;
	private static Scene scene;
	
	public static Scene getVersionForm() {
		titleLbl = new Label("Version 0.1");
		titleLbl.setStyle("-fx-font-name:Arial; -fx-font-size:34;"
				+ " -fx-font-weight:bold; -fx-text-fill:Maroon");
		bt = new Button("Back");
		bt.setMinWidth(150);
		bt.setMinHeight(50);
		
		bt.setOnAction(e -> goToPreviousForm());
		
		vb = new VBox(40);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(titleLbl, bt);
		scene = new Scene(vb, 400, 300);
		return scene;
	}
	
	private static void goToPreviousForm() {
		
		try {
			// TeachersAppFX.getPrimaryStage().setScene(MainWindowFX.getMainWindow());
			//TeachersAppFX.getPrimaryStage().setScene(MainWindowFXController.createMainScene());
			MainWindowFXController.createMainScene();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
