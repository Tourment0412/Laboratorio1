package co.edu.uniquindio.programacionIII.alquilafacil.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.SecureRandom;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ArchivoNoEncontradoException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Utils {

	private static Utils instance;

	public static Utils getInstance() {
		if (instance == null)
			instance = new Utils();
		return instance;
	}

	public Image cargarImagenArchivo() throws ArchivoNoEncontradoException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Archivos de Imagen", "*.png"));
		File archivoImagen = fileChooser.showOpenDialog(null);
		if (archivoImagen == null)
			throw new ArchivoNoEncontradoException("El archivo no pudo ser encontrado");

		try {
			FileInputStream fis = new FileInputStream(archivoImagen);
			return new Image(fis);
		} catch (FileNotFoundException e) {
			throw new ArchivoNoEncontradoException("El archivo no pudo ser encontrado");
		}
	}

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

	public String crearCodigoRandomAquiler() {
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			final int num = secureRandom.nextInt('Z' - 'A' + 1);
			builder.append(String.valueOf((char) ('A' + num - 10)));
		}
		for (int i = 0; i < 4; i++) {
			final int num = secureRandom.nextInt(10);
			builder.append(num);
		}
		return builder.toString();
	}

}
