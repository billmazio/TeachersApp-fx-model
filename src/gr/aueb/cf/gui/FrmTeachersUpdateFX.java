package gr.aueb.cf.gui;

import gr.aueb.cf.application.TeachersAppFX;
import gr.aueb.cf.model.Teacher;

import java.sql.SQLException;

//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FrmTeachersUpdateFX {
	private static Scene scene;
	private static GridPane gp;
	private static Label idLbl, sNameLbl, fNameLbl;
	private static TextField idTxt, sNameTxt, fNameTxt;
	private static Button updateButton, deleteButton, closeButton;
	private static TextField searchNameTxt;
	
	private static TableView<Teacher> table;
	private static TableColumn<Teacher, Integer> idColumn;
	private static TableColumn<Teacher, String> fNameColumn, sNameColumn;
	
	private static HBox mainHb, inHb;
	private static VBox vb;
	
	private static Label searchLbl;
	private static Button searchBtn;
	
	private static ObservableList<Teacher> teachersObsList = FXCollections.observableArrayList();
	private static int i;

	
	
	public static Scene getFrmTeachersUpdateScene() {
		TeachersAppFX.getPrimaryStage().setTitle("Teacher Update/Delete");
		gp = new GridPane();
		
		idColumn = new TableColumn<>("Teacher ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("id"));
		
		fNameColumn = new TableColumn<>("Firstname");
		fNameColumn.setMinWidth(150);
		fNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("fName"));
		
		sNameColumn = new TableColumn<>("SurName");
		sNameColumn.setMinWidth(150);
		sNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("sName"));
		
		table = new TableView<>();
		table.getColumns().add(idColumn);
		table.getColumns().add(fNameColumn);
		table.getColumns().add(sNameColumn);
		
		table.getSelectionModel().selectedIndexProperty().addListener((v, oldVal, newVal) -> {
			Teacher teacher = table.getSelectionModel().getSelectedItem();
			if (teacher != null){
				idTxt.setText(Integer.toString(teacher.getId()));
				sNameTxt.setText(teacher.getSName());
				fNameTxt.setText(teacher.getFName());
			}
		});
				
				/*new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//table.getSelectionModel().select(i);
				Teacher teacher = table.getSelectionModel().getSelectedItem();
				//System.out.println(teacher);
				if (teacher != null){
					idTxt.setText(Integer.toString(teacher.getId()));
					sNameTxt.setText(teacher.getSName());
					fNameTxt.setText(teacher.getFName());
				}
			}
		});*/
		
		mainHb = new HBox(20);
		mainHb.setPadding(new Insets(10, 10, 10, 10));
		
		vb = new VBox(20);
		
		inHb = new HBox(10);
		inHb.setPadding(new Insets(10, 10, 10, 10));
		
		searchLbl = new Label("Surname");
		searchNameTxt = new TextField();
		searchBtn = new Button("Search");
		searchBtn.setOnAction(e -> handleSearch());
		
		
		inHb.getChildren().addAll(searchLbl, searchNameTxt, searchBtn);
	
		vb.getChildren().addAll(inHb, table);
		
				
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
		idTxt.setEditable(false);
		idTxt.setStyle("-fx-control-inner-background:#F9E79F");
		//idTxt.setForeground("GREEN");
		GridPane.setConstraints(idTxt,  1,  0);
		sNameTxt = new TextField();
		GridPane.setConstraints(sNameTxt,  1,  1);
		fNameTxt = new TextField();
		GridPane.setConstraints(fNameTxt,  1,  2);
		
		updateButton = new Button("Update");
		//updateButton.setMinWidth(200);
		//updateButton.setMinHeight(50);
		GridPane.setConstraints(updateButton, 0, 3);
		updateButton.setOnAction(e -> handleUpdate());
		
		deleteButton = new Button("Delete");
		//delteButton.setMinWidth(200);
		//deleteButton.setMinHeight(50);
		GridPane.setConstraints(deleteButton, 1, 3);
		deleteButton.setOnAction(e -> handleDelete());
		
		closeButton = new Button("Back");
		closeButton.setMinWidth(100);
		//deleteButton.setMinHeight(50);
		GridPane.setConstraints(closeButton, 0, 4);
		closeButton.setOnAction(e -> handleClose());
		
		
		gp.setPadding(new Insets(70, 10, 10, 10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.getChildren().addAll(idLbl, sNameLbl, fNameLbl, 
				idTxt, sNameTxt, fNameTxt, updateButton, deleteButton, closeButton);
		//gp.getChildren().add(closeButton);
		
		mainHb = new HBox(20);
		mainHb.setPadding(new Insets(10, 10, 10, 10));
		mainHb.getChildren().addAll(vb, gp);
		
		scene = new Scene(mainHb, 750, 500);
		
		return scene;
	}
	
		
	private static void handleUpdate() {
		i = table.getSelectionModel().getSelectedIndex();
		String sid = idTxt.getText();
		String sName = sNameTxt.getText();
		String fName = fNameTxt.getText();
		//int id=0;
		
		//if (isInt(sid)) 
		// No need to validate for int since it has been mapped from DB/Model
		int id = Integer.parseInt(sid);
		
		//table.getSelectionModel().getSelectedItem();
		Teacher t = new Teacher();
		t.setId(id);
		t.setFName(fName);
		t.setSName(sName);
		
		try{	
			t.update();
			
			// change the observable list and the table will automatically be updated
			// magic! -- that's way we call it observable
			// because it is observed by the table
			teachersObsList.set(i, t);
			table.getSelectionModel().select(i);
			
		}
		catch (SQLException e) {
			System.out.println(e);	
		}
	}
	
	private static void handleDelete() {
		i = table.getSelectionModel().getSelectedIndex();
		String sid = idTxt.getText();
		
		int id = Integer.parseInt(sid);
		
		Teacher t = new Teacher();
		t.setId(id);
				
		t.delete();
		
		// change the observable list and the table will automatically be updated
		// magic! -- that's way we call it observable
		// because it is observed by the table
		if (Teacher.numberOfDeletedRowsAffected > 0)  teachersObsList.remove(i);
		//table.getSelectionModel().select(0);
	}
	
	private static void handleSearch() {
		if (table.getItems() != null) table.getItems().clear();
		teachersObsList = Teacher.getTeachers(searchNameTxt);
		table.setItems(teachersObsList);
	}
	
	private static void handleClose() {
		TeachersAppFX.getPrimaryStage().setScene(SearchSceneFX.getSearchSceneFX());
	}
	
}
