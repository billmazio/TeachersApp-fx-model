package gr.aueb.cf.gui;


import java.io.IOException;

import gr.aueb.cf.application.TeachersAppFX;
import gr.aueb.cf.controllers.MainWindowFXController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


public class SearchSceneFX {
	private static Scene scene;
	private static VBox vb;
	private static HBox hb;
	private static Button insertBtn, updateBtn, backBtn;
	private static Label titleLbl;
	
	public static Scene getSearchSceneFX() {
		titleLbl = new Label("Teachers' Main Menu");
		titleLbl.setStyle("-fx-font-name:Arial; -fx-font-size:24;"
				+ " -fx-font-weight:bold; -fx-text-fill:Maroon");
		
		insertBtn = new Button("Insert");
		insertBtn.setMinWidth(200);
		insertBtn.setMinHeight(50);
		insertBtn.setStyle("-fx-text-fill:Maroon");
		insertBtn.setOnAction(e -> handleInsert());
		
		updateBtn = new Button("Search");
		updateBtn.setMinWidth(200);
		updateBtn.setMinHeight(50);
		updateBtn.setStyle("-fx-text-fill:Maroon");
		updateBtn.setOnAction(e -> handleUpdate());
		
		backBtn = new Button("Back");
		backBtn.setMinWidth(60);
		//backBtn.setMinHeight(50);
		backBtn.setStyle("-fx-text-fill:blue");
		backBtn.setOnAction(e -> goToPreviousForm());
		
		hb = new HBox();
		hb.setPadding(new Insets(10, 50, 10, 0));
		hb.setAlignment(Pos.CENTER_RIGHT);
		hb.getChildren().add(backBtn);
			
		Separator sp = new Separator(Orientation.HORIZONTAL);
		
		vb = new VBox(20);
		vb.setPadding(new Insets(20, 10, 0, 0));
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-border-color: black; -fx-border-width: 1");
		vb.getChildren().addAll(titleLbl, sp, insertBtn, updateBtn, hb);
		
		scene = new Scene(vb, 500, 300);
		TeachersAppFX.getPrimaryStage().setTitle("Teachers' Main Menu");
		return scene;
	}
	
	private static void goToPreviousForm() {
		try {
			MainWindowFXController.createMainScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void handleInsert() {
		TeachersAppFX.getPrimaryStage().setScene(FrmTeachersInsertFX.getFrmTeachersInsertScene());
	}
	
	private static void handleUpdate() {
		TeachersAppFX.getPrimaryStage().setScene(FrmTeachersUpdateFX.getFrmTeachersUpdateScene());
	}
}
