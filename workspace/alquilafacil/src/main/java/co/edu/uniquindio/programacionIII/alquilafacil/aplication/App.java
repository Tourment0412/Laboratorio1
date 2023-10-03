package co.edu.uniquindio.programacionIII.alquilafacil.aplication;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		stage.setScene(new Scene(MainViewController.getInstance().loadMainMenu(), 640, 480));
		stage.setMinHeight(600);
		stage.setMinWidth(1000);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}