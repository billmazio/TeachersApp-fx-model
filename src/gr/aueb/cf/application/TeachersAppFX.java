package gr.aueb.cf.application;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import gr.aueb.cf.controllers.MainWindowFXController;

import java.io.IOException;

public class TeachersAppFX extends Application {
	private static Stage mainStage;
	
	//private static Scene mainScene; 
	private static Connection connection;
	
	@Override
	public void init() {
		String url = "jdbc:mysql://localhost:3306/teachers?serverTimezone=UTC&useSSL=false"; // Give your DB name
 		String username = "thanos2";  // Give your username
		String password = "Thanos12";  // Give your password

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("Can not conenct to DB");
			//ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		mainStage.setTitle("Coding Factory's Teachers App");
		MainWindowFXController.createMainScene();
		mainStage.show();
	}
	
	public static Stage getPrimaryStage() {
		return mainStage;
	}

	public static void main(String[] args) {
		launch(args);
	}	
}
