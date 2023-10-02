package co.edu.uniquindio.programacionIII.alquilafacil.services;

public enum Vista {
	MAIN(""), ADD_CLIENT("crearCliente"), ADD_VEHICLE("crearVehiculo");

	private String fxml;

	private Vista(String fxml) {
		this.fxml = fxml;

	}

	public String getFxml() {
		return fxml;
	}
}
