package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	private Main main;
	private Stage primaryStage;

	public void setMain(Main main, Stage primaryStage) {
		this.main = main;
		this.primaryStage = primaryStage;
	}

	public void animationWindow(int animationNo) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/AnimationWindowView.fxml"));
			AnchorPane animationPane = loader.load();

			Stage animationWindowStage = new Stage();
			animationWindowStage.setTitle("Animacja");
			animationWindowStage.initModality(Modality.WINDOW_MODAL);
			animationWindowStage.initOwner(primaryStage);
			animationWindowStage.setMinWidth(500.0);
			animationWindowStage.setMinHeight(500.0);
			Scene scene = new Scene(animationPane);
			animationWindowStage.setScene(scene);

			AnimationWindowController animationWindowController = loader.getController();
			animationWindowController.setAnimationWindowStage(animationWindowStage);
			animationWindowController.setAnimationNo(animationNo);

			animationWindowStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void closeApplication() {
		primaryStage.close();
	}

	public void wyswietl_1() {
		animationWindow(1);
	}

}
