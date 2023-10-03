package co.edu.uniquindio.programacionIII.alquilafacil.utils;

public enum Vista {
	MAIN(""), ADD_CLIENT("crearCliente"), ADD_VEHICLE("crearVehiculo"), RENT_INI("seleccionarCliente"),RENT_SEC("seleccionarVehiculo")
	,RENT_FIN("concretarAlquiler");


	private String fxml;

	private Vista(String fxml) {
		this.fxml = fxml;

	}

	public String getFxml() {
		return fxml;
	}
}
