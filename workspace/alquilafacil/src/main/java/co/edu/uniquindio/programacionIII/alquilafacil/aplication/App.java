package co.edu.uniquindio.programacionIII.alquilafacil.aplication;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.services.ViewServices;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		stage.setScene(new Scene(ViewServices.getInstance().loadMainMenu(), 640, 480));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}