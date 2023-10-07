package co.edu.uniquindio.programacionIII.alquilafacil.controllers;


import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CreacionAlquilerHandler;
import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;

public class ModelFactoryController {

	private static ModelFactoryController instance;

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public Double getTotalGanadoAlquileres() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().getTotalGanadoAlquileres();
	}

	public String getMarcaMasAlquilada() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws PersiscenciaDesconocidaException, ListaVaciaException {
		return DataService.getInstance().listarVehiculosRangoFechas(fechaInicial, fechaFinal);
	}

	public List<Vehiculo> listarVehiculosAlquilados() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarVehiculosAlquilados();
	}

	public boolean estaDisponible(Vehiculo vehiculo) throws PersiscenciaDesconocidaException {
		return DataService.getInstance().estaDisponible(vehiculo);
	}

	public void agregarCliente(String cedula, String nombre, String numeroTel, String email, String ciudad,
			String direccion) throws ObjetoYaExisteException, PersiscenciaDesconocidaException, CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, cedula, "La cedula no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, numeroTel, "El numero de telefono no puede estar vacio");
		requerirCampoString(sb, email, "El email no puede estar vacio");
		requerirCampoString(sb, ciudad, "La ciudad no puede estar vacia");
		requerirCampoString(sb, direccion, "La direccion no puede estar vacia");

		lanzarCampoInvalido(sb);

		Cliente cliente = Cliente.builder().cedula(cedula).nombre(nombre).numeroTel(numeroTel).email(email)
				.ciudad(ciudad).direccion(direccion).build();
		DataService.getInstance().agregarCliente(cliente);
	}

	public void agregarVehiculo(String placa, String nombre, String marca, String modeloString, String rutaImg,
			String transmisionString, String kilometrajeString, String precioAlquilerDiaString, String numSillasString)
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, ImagenNoObtenidaException,
			CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, placa, "La placa no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, marca, "La marca no puede estar vacia");
		requerirCampoString(sb, rutaImg, "Verifica la imagen del vehiculo");

		Transmision transmision = obtenerTransmisionThrow(sb, transmisionString, "La transmision no puede estar vacia");
		Double precioAlquilerDia = obtenerDoubleThrow(sb, precioAlquilerDiaString,
				"El precio de alquiler por dia es invalido");
		Integer modelo = obtenerIntThrow(sb, modeloString, "El modelo es invalido");
		Integer kilometraje = obtenerIntThrow(sb, modeloString, "El kilometraje es invalido");
		Integer numSillas = obtenerIntThrow(sb, numSillasString, "El numero de sillas es invalido");

		lanzarCampoInvalido(sb);

		Vehiculo vehiculo = Vehiculo.builder().placa(placa).nombre(nombre).marca(marca).modelo(modelo).rutaImg(rutaImg)
				.transmision(transmision).kilometraje(kilometraje).precioAlquilerDia(precioAlquilerDia)
				.numSillas(numSillas).build();
		DataService.getInstance().agregarVehiculo(vehiculo);
	}

	private Transmision obtenerTransmisionThrow(StringBuilder sb, String transmisionString, String msg)
			throws CampoInvalidoException {
		if (transmisionString == null || transmisionString.isBlank()) {

			sb.append("La transmision es invalida");
			sb.append('\n');
			return null;
		}
		Transmision transmision = Transmision.valueByText(transmisionString);

		if (transmision == null) {
			sb.append("La transmision es invalida");
			sb.append('\n');
		}
		return transmision;
	}

	private Double obtenerDoubleThrow(StringBuilder sb, String precioAlquilerDiaString, String msg) {
		if (precioAlquilerDiaString == null || precioAlquilerDiaString.isBlank()) {
			sb.append(msg);
			sb.append('\n');
			return null;
		}
		Double precioAlquilerDia = null;
		try {
			precioAlquilerDia = Double.parseDouble(precioAlquilerDiaString);
		} catch (Exception e) {
			sb.append(msg);
			sb.append('\n');
			return null;
		}
		if (precioAlquilerDia < 0) {
			sb.append(msg);
			sb.append('\n');
		}

		return precioAlquilerDia;
	}

	private Integer obtenerIntThrow(StringBuilder sb, String numString, String msg) {
		if (numString == null || numString.isBlank()) {
			sb.append(msg);
			sb.append('\n');
			return null;
		}
		Integer num = null;
		try {
			num = Integer.parseInt(numString);
		} catch (Exception e) {
			sb.append(msg);
			sb.append('\n');
			return null;
		}
		if (num < 0) {
			sb.append(msg);
			sb.append('\n');
		}

		return num;
	}

	public List<Alquiler> listarAlquileres() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarAlquileres();
	}

	public List<Vehiculo> listarVehiculos() throws PersiscenciaDesconocidaException, ListaVaciaException {
		return DataService.getInstance().listarVehiculos();
	}

	public List<Cliente> listarClientes() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarClientes();
	}

	public Alquiler agregarAlquiler() throws VehiculoNoDisponibleException, ObjetoYaExisteException,
			PersiscenciaDesconocidaException, ObjetoNoEncontradoException, CampoInvalidoException {
		CreacionAlquilerHandler creacionInstance = CreacionAlquilerHandler.getInstance();
		creacionInstance.validarCampos();
		Alquiler alquiler = Alquiler.builder().cliente(creacionInstance.getCliente())
				.vehiculo(creacionInstance.getVehiculo()).fechaAlquiler(creacionInstance.getFechaAlquiler())
				.fechaRegreso(creacionInstance.getFechaRegreso()).build();
		return DataService.getInstance().agregarAlquiler(alquiler);
	}

	private void lanzarCampoInvalido(StringBuilder sb) throws CampoInvalidoException {
		if (!sb.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
			throw new CampoInvalidoException(sb.toString());
		}
	}

	private void requerirCampoString(StringBuilder sb, String cadena, String msg) throws CampoInvalidoException {
		if (cadena == null || cadena.isBlank()) {
			sb.append(msg);
			sb.append("\n");
		}
	}

}
