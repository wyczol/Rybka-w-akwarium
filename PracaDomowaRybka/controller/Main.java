package controller;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private Stage primaryStage;
	// tworzymy go tu bo dostep do primatyStage bedzie potrzebny w wielu miejscach
	// nie tylko w metodzie start

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindowView.fxml"));// stworzenie opisu
																									// okna
			AnchorPane pane = loader.load();// ladowanie pane
			primaryStage.setMinWidth(450);
			primaryStage.setMinHeight(482);

			Scene scene = new Scene(pane);
			MainController mvc = loader.getController();// ladujemy informacje z pliku fxml
			mvc.setMain(this, primaryStage);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Autor. Marcin Świderski Java EE");
			primaryStage.show();// powiazanie sceny i stage
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
