package co.edu.uniquindio.programacionIII.alquilafacil.utils;

public enum Vista {
	MAIN(""), ADD_CLIENT("crearCliente"), ADD_VEHICLE("crearVehiculo"), ADD_RENT("crearAlquiler"),
	FUNCTEXT("mostrarFuncionalidades"), FUNCTEXT1("filtrarPorPrecio"), FUNCTEXT2("mostrarVehiRentDia"),
	FACTURA("pdfFactura"),FUNCTEXT3("mostrarTotalGanado");

	private String fxml;

	private Vista(String fxml) {
		this.fxml = fxml;

	}

	public String getFxml() {
		return fxml;
	}
}
