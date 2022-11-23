package gr.aueb.cf.controllers;

import java.io.IOException;

import gr.aueb.cf.application.TeachersAppFX;
import gr.aueb.cf.gui.SearchSceneFX;
import gr.aueb.cf.gui.VersionFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindowFXController {
	private static Scene scene;
	
	@FXML private VBox vb;
	@FXML private HBox hb;
	@FXML private GridPane gp;
	@FXML private Label titleLbl, teachersLbl, versionLbl;
	@FXML private Button teachersBtn, versionBtn;
	
	
	public static void createMainScene() throws IOException {
		Parent root = FXMLLoader.load(MainWindowFXController.class.getResource("../view/MainWindowFX.fxml"));
		scene = new Scene(root, 500, 300);
		scene.getStylesheets().add(MainWindowFXController.class.getResource("/resources/application.css").toExternalForm());
		TeachersAppFX.getPrimaryStage().setScene(scene);
	}
	
	@FXML
	private void openTeachersMenu() {
		TeachersAppFX.getPrimaryStage().setScene(SearchSceneFX.getSearchSceneFX());
	}
	
	@FXML
	private void openVersionForm() {
		TeachersAppFX.getPrimaryStage().setScene(VersionFX.getVersionForm());
	}
}








//titleLbl = new Label("Quality in Education");
		/*
		 * titleLbl.setStyle("-fx-font-name:Arial; -fx-font-size:34;" +
		 * " -fx-font-weight:bold; -fx-text-fill:Maroon");
		 */
		//teachersBtn.setOnAction(e -> openTeachersMenu());
		//versionBtn.setOnAction(e -> openVersionForm());
		
		/*
		 * hb = new HBox(); hb.setPadding(new Insets(20, 0, 0, 0));
		 * hb.setAlignment(Pos.CENTER); hb.getChildren().add(titleLbl);
		 */
		/*
		 * teachersBtn = new Button(""); teachersBtn.setMinWidth(50);
		 * teachersBtn.setMinHeight(50); teachersBtn.setOnAction(e ->
		 * openTeachersMenu()); GridPane.setConstraints(teachersBtn, 0, 0);
		 * 
		 * versionBtn = new Button(""); versionBtn.setMinWidth(50);
		 * versionBtn.setMinHeight(50); versionBtn.setOnAction(e -> openVersionForm());
		 * GridPane.setConstraints(versionBtn, 0, 1);
		 */
		/*
		 * teachersLbl = new Label("Teachers");
		 * teachersLbl.setStyle("-fx-font-name:Arial; -fx-font-size:22;" +
		 * " -fx-font-weight:bold; -fx-text-fill:Blue");
		 * GridPane.setConstraints(teachersLbl, 1, 0);
		 * 
		 * versionLbl = new Label("Version");
		 * versionLbl.setStyle("-fx-font-name:Arial; -fx-font-size:22;" +
		 * " -fx-font-weight:bold; -fx-text-fill:Blue");
		 * GridPane.setConstraints(versionLbl, 1, 1);
		 */
		/*
		 * gp = new GridPane(); gp.setPadding(new Insets(20, 10, 10, 20));
		 * gp.setHgap(10); gp.setVgap(15); gp.getChildren().addAll(teachersBtn,
		 * teachersLbl, versionBtn, versionLbl);
		 * 
		 * 
		 * vb = new VBox(10); vb.setAlignment(Pos.TOP_CENTER);
		 * vb.getChildren().addAll(hb, gp);
		 */
		
		
		//scene = new Scene(vb, 500, 300);
		//return scene;