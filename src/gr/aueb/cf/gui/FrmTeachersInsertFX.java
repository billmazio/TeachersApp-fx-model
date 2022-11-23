package gr.aueb.cf.gui;

import gr.aueb.cf.application.TeachersAppFX;
import gr.aueb.cf.model.Teacher;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FrmTeachersInsertFX {
	private static Scene scene;
	private static GridPane gp;
	private static Label idLbl, sNameLbl, fNameLbl;
	private static TextField idTxt, sNameTxt, fNameTxt;
	private static Button insertButton, closeButton;
	private static HBox hb1;
	private static AnchorPane ap;
	private static VBox vb;
	
	
	public static Scene getFrmTeachersInsertScene() {
		TeachersAppFX.getPrimaryStage().setTitle("Insert");
		gp = new GridPane();
		//gp.setGridLinesVisible(true);
		
		idLbl = new Label("Teacher ID");
		GridPane.setConstraints(idLbl,  0,  0);
		idLbl.setStyle("-fx-font-name:Arial; -fx-font-size:18;"
				+ " -fx-font-weight:bold; -fx-text-fill:Maroon");
		sNameLbl = new Label("Surname");
		GridPane.setConstraints(sNameLbl,  0,  1);
		sNameLbl.setStyle("-fx-font-name:Arial; -fx-font-size:18;"
				+ " -fx-font-weight:bold; -fx-text-fill:Maroon");
		fNameLbl = new Label("Firstname");
		GridPane.setConstraints(fNameLbl,  0,  2);
		fNameLbl.setStyle("-fx-font-name:Arial; -fx-font-size:18;"
				+ " -fx-font-weight:bold; -fx-text-fill:Maroon");
		
		idTxt = new TextField();
		idTxt.setPromptText("Teacher ID");
		GridPane.setConstraints(idTxt,  1,  0);
		sNameTxt = new TextField();
		GridPane.setConstraints(sNameTxt,  1,  1);
		fNameTxt = new TextField();
		GridPane.setConstraints(fNameTxt,  1,  2);
		
		insertButton = new Button("Insert");
		//insertButton.setMinWidth(200);
		//insertButton.setMinHeight(50);
		GridPane.setConstraints(insertButton, 1, 3);
		insertButton.setOnAction(e -> handleInsert());
		
		closeButton = new Button("Back");
		//closeButton.setMinWidth(200);
		//closeButton.setMinHeight(50);
		GridPane.setConstraints(closeButton, 1, 4);
		closeButton.setOnAction(e -> handleClose());
		
		ap = new AnchorPane();
		//AnchorPane.setBottomAnchor(insertButton, 30.0);
		AnchorPane.setRightAnchor(insertButton, 100.0);
		//AnchorPane.setBottomAnchor(closeButton, 30.0);
		AnchorPane.setRightAnchor(closeButton, 30.0);
		
		//ap.setAlignment(Pos.CENTER);
		ap.getChildren().addAll(insertButton, closeButton);
		
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.getChildren().addAll(idLbl, sNameLbl, fNameLbl, 
				idTxt, sNameTxt, fNameTxt);
		hb1 = new HBox();
		hb1.setAlignment(Pos.CENTER);
		hb1.getChildren().add(gp);
		
		vb = new VBox(10);
		vb.setPadding(new Insets(0, 10, 10, 10));
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(hb1, ap);
		
		
		scene = new Scene(vb, 370, 200);
		
		return scene;
	}
	
	private static void handleInsert() {
		String sid = idTxt.getText();
		String sName = sNameTxt.getText();
		String fName = fNameTxt.getText();
		int id=0;
		
		if (isInt(sid)) id = Integer.parseInt(sid);
		else {
			JOptionPane.showMessageDialog(null, "No record to insert", "Warning", JOptionPane.PLAIN_MESSAGE);
			idTxt.setText("");
			sNameTxt.setText("");
			fNameTxt.setText("");
			return;
		}
		
		Teacher t = new Teacher();
		t.setId(id);
		t.setFName(fName);
		t.setSName(sName);
		
		t.insert();
			
		idTxt.setText("");
		sNameTxt.setText("");
		fNameTxt.setText("");
	}
	
	private static void handleClose() {
		TeachersAppFX.getPrimaryStage().setScene(SearchSceneFX.getSearchSceneFX());
	}
	
	private static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}

}
