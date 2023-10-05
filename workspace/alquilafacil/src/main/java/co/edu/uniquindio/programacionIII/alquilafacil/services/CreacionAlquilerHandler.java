package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.time.LocalDate;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import lombok.ToString;

@ToString
public class CreacionAlquilerHandler {
	private LocalDate fechaAlquiler;
	private LocalDate fechaRegreso;
	private String cedula;
	private String placa;

	private CreacionAlquilerHandler() {
	}

	public void selectVehiculo(Vehiculo vehiculo) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el vehículo ");
		if (vehiculo == null)
			lanzarExceptionCampoInvalido(sb);
		this.placa = vehiculo.getPlaca();
	}

	public void selectVehiculo(String placa) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el vehículo ");
		if (placa == null || placa.isBlank())
			lanzarExceptionCampoInvalido(sb);
		this.placa = placa;
	}

	public void selectCliente(Cliente cliente) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el cliente ");
		if (cliente == null)
			lanzarExceptionCampoInvalido(sb);
		this.cedula = cliente.getCedula();
	}

	public void selectCliente(String cedula) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el cliente ");
		if (cedula == null || cedula.isBlank())
			lanzarExceptionCampoInvalido(sb);
		this.cedula = cedula;
	}

	public void selectDates(LocalDate fechaAlquiler, LocalDate fechaRegreso) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		if (fechaRegreso == null || fechaAlquiler == null) {
			sb.append("Recuerda seleccionar ambas fechas");
			sb.append('\n');
		} else if (fechaRegreso.isBefore(fechaAlquiler)) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		} else if (fechaAlquiler.isEqual(fechaRegreso)) {
			sb.append("Las fechas no pueden ser las mismas");
			sb.append('\n');
		} else if (!fechaAlquiler.isAfter(LocalDate.now())) {
			sb.append("La fecha de alquiler no puede ser antes que la fecha actual");
			sb.append('\n');
		}
		lanzarExceptionCampoInvalido(sb);
		this.fechaAlquiler = fechaAlquiler;
		this.fechaRegreso = fechaRegreso;
	}

	public void validarCampos() throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		if (cedula == null) {
			sb.append("La cédula del cliente es inválida");
			sb.append('\n');
		}
		if (placa == null) {
			sb.append("La placa del vehículo es inválida");
			sb.append('\n');
		}
		if (fechaRegreso == null || fechaAlquiler == null) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		}
		lanzarExceptionCampoInvalido(sb);
	}

	public String getCedula() {
		return cedula;
	}

	public String getPlaca() {
		return placa;
	}

	public LocalDate getFechaRegreso() {
		return fechaRegreso;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void lanzarExceptionCampoInvalido(StringBuilder sb) throws CampoInvalidoException {
		if (!sb.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
			throw new CampoInvalidoException(sb.toString());
		}
	}

	private static CreacionAlquilerHandler instance;

	public static CreacionAlquilerHandler getInstance() {
		if (instance == null)
			instance = new CreacionAlquilerHandler();
		System.out.println(instance);
		return instance;
	}

	public void clearData() {
		instance = new CreacionAlquilerHandler();
	}

}
