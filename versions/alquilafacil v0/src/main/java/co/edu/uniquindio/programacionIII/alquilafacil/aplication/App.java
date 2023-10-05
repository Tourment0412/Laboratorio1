package co.edu.uniquindio.programacionIII.alquilafacil.aplication;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		stage.setScene(new Scene(MainViewController.getInstance().loadMainMenu(), 640, 480));
		stage.setMinHeight(600);
		stage.setMinWidth(1000);
		stage.setTitle("Alquila Facil");
		stage.getIcons().add(new Image("/co/edu/uniquindio/programacionIII/alquilafacil/sources/logo.png"));
		stage.show();
	}

	public static void main(String[] args) {
		new Thread(() -> DataService.getInstance().load()).start();
		launch();
	}
}