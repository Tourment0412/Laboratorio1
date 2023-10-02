package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.aplication.App;
import co.edu.uniquindio.programacionIII.alquilafacil.controllers.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewServices {
	private static ViewServices instance;

	private ViewServices() {
	}

	public static ViewServices getInstance() {
		if (instance == null)
			instance = new ViewServices();
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
