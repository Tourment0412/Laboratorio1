package co.edu.uniquindio.programacionIII.alquilafacil.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {

	public static void mostrarAlerta(String title, String message) {
		mostrarAlerta(title, message, AlertType.CONFIRMATION);
	}

	public static void mostrarAlerta(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setHeaderText(title);
		alert.setTitle(title);
		alert.show();
	}

}
