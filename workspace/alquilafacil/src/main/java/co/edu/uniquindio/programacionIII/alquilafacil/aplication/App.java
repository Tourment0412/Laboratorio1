package co.edu.uniquindio.programacionIII.alquilafacil.aplication;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.services.ViewServices;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(ViewServices.getInstance().loadMainMenu(), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}