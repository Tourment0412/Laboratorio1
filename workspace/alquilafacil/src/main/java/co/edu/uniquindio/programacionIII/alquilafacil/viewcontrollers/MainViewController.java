package co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.aplication.App;
import co.edu.uniquindio.programacionIII.alquilafacil.controllers.MenuController;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainViewController {
	private static MainViewController instance;

	private MainViewController() {
	}

	public static MainViewController getInstance() {
		if (instance == null)
			instance = new MainViewController();
		return instance;
	}

	public void cambiarVista(Vista vista) { // TODO logger
		try {
			MenuController.getInstance().cambiarCentro(loadFXML(vista.getFxml()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Parent loadMainMenu() throws IOException {
		return loadFXML("menuPrincipal");
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/programacionIII/alquilafacil/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
