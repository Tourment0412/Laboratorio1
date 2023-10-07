package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.time.LocalDate;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreacionAlquilerHandler {

	private LocalDate fechaAlquiler;

	private LocalDate fechaRegreso;

	private Cliente cliente;

	private Vehiculo vehiculo;

	private CreacionAlquilerHandler() {
	}

	public void selectVehiculo(Vehiculo vehiculo) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el vehículo ");
		if (vehiculo == null)
			lanzarExceptionCampoInvalido(sb);
		this.vehiculo = vehiculo;
	}

	public void selectCliente(Cliente cliente) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder("Recuerda seleccionar el cliente ");
		if (cliente == null)
			lanzarExceptionCampoInvalido(sb);
		this.cliente = cliente;
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
		if (cliente == null) {
			sb.append("El cliente es inválido");
			sb.append('\n');
		}
		if (vehiculo == null) {
			sb.append("El vehículo es inválido");
			sb.append('\n');
		}
		if (fechaRegreso == null || fechaAlquiler == null) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		}
		lanzarExceptionCampoInvalido(sb);
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
