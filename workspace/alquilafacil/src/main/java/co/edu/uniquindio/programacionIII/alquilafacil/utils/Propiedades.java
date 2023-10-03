package co.edu.uniquindio.programacionIII.alquilafacil.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.beans.property.SimpleObjectProperty;

public class Propiedades {
	private static final String RUTA = "/co/edu/uniquindio/programacionIII/alquilafacil/properties/textos";

	private final SimpleObjectProperty<ResourceBundle> bundleProperty = new SimpleObjectProperty<>();

	private static Propiedades instance;

	private Propiedades() {
		bundleProperty.setValue(ResourceBundle.getBundle(RUTA));
	}

	public void setLanguage(Locale locale) {
		bundleProperty.setValue(ResourceBundle.getBundle(RUTA, locale));
	}

	public void addListener(Consumer<ResourceBundle> listener) {
		listener.accept(bundleProperty.getValue());
		bundleProperty.addListener((observable, oldValue, newValue) -> listener.accept(newValue));
	}

	public static Propiedades getInstance() {
		if (instance == null)
			instance = new Propiedades();
		return instance;
	}
}
