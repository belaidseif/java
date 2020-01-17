package application;
	

import java.util.ArrayList;

import classe.Classe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void gererDep(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/departement/departement.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void gererMat(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/matiere/matiere.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gererClasse(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/classe/classe.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gererEns(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/enseignant/enseignant.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generer(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/generation/generation.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			stage2.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
