package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CreacionAlquilerHandler;
import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;
import javafx.scene.image.Image;

public class ModelFactoryController {

	private static ModelFactoryController instance;

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public Double getTotalGanadoAlquileres() {
		return DataService.getInstance().getTotalGanadoAlquileres();
	}

	public String getMarcaMasAlquilada() throws ElementoNoEncontradoException {
		return DataService.getInstance().getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws ListaVaciaException, CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		if (fechaInicial == null || fechaFinal == null) {
			sb.append("Recuerda seleccionar ambas fechas");
			sb.append('\n');
		} else if (fechaFinal.isBefore(fechaInicial)) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		}
		lanzarExceptionCampoInvalido(sb);
		return DataService.getInstance().listarVehiculosRangoFechas(fechaInicial, fechaFinal);
	}
	
	public List<Vehiculo> listarVehiculosFecha(LocalDate fecha) throws ListaVaciaException, CampoInvalidoException{
		StringBuilder sb= new StringBuilder();
		if(fecha==null) {
			sb.append("No se ha seleccionado una fecha");
			sb.append("\n");
		}
		lanzarExceptionCampoInvalido(sb);
		return DataService.getInstance().listarVehiculosFecha(fecha);
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		return DataService.getInstance().listarVehiculosAlquilados();
	}

	public boolean vehiculoEstaDisponible(String placa) {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, placa, "La placa no puede estar vacia");
		return DataService.getInstance().estaDisponible(placa);
	}

	public void agregarCliente(String cedula, String nombre, String numeroTel, String email, String ciudad,
			String direccion) throws ObjetoYaExisteException, CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, cedula, "La cedula no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, numeroTel, "El numero de telefono no puede estar vacio");
		requerirCampoString(sb, email, "El email no puede estar vacio");
		requerirCampoString(sb, ciudad, "La ciudad no puede estar vacia");
		requerirCampoString(sb, direccion, "La direccion no puede estar vacia");

		lanzarExceptionCampoInvalido(sb);

		Cliente cliente = Cliente.builder().cedula(cedula).nombre(nombre).numeroTel(numeroTel).email(email)
				.ciudad(ciudad).direccion(direccion).build();
		DataService.getInstance().agregarCliente(cliente);
	}

	public void agregarVehiculo(String placa, String nombre, String marca, String modeloString, Image image,
			String transmisionString, String kilometrajeString, String precioAlquilerDiaString, String numSillasString)
			throws CampoInvalidoException, ObjetoYaExisteException, ImagenNoObtenidaException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, placa, "La placa no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, marca, "La marca no puede estar vacia");

		Transmision transmision = obtenerTransmisionThrow(sb, transmisionString, "La transmision no puede estar vacia");
		Double precioAlquilerDia = obtenerDoubleThrow(sb, precioAlquilerDiaString,
				"El precio de alquiler por dia es invalido");
		Integer modelo = obtenerIntThrow(sb, modeloString, "El modelo es invalido");
		Integer kilometraje = obtenerIntThrow(sb, modeloString, "El kilometraje es invalido");
		Integer numSillas = obtenerIntThrow(sb, numSillasString, "El numero de sillas es invalido");

		if (image == null)
			sb.append("Verifica la imagen del vehiculo\n");
		lanzarExceptionCampoInvalido(sb);

		Vehiculo vehiculo = Vehiculo.builder().placa(placa).nombre(nombre).marca(marca).modelo(modelo).image(image)
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

	public List<Alquiler> listarAlquileres() {
		return DataService.getInstance().listarAlquileres();
	}

	public List<Vehiculo> listarVehiculos() throws ListaVaciaException {
		return DataService.getInstance().listarVehiculos();
	}

	public List<Cliente> listarClientes() {
		return DataService.getInstance().listarClientes();
	}

	public Alquiler agregarAlquiler() throws VehiculoNoDisponibleException, ObjetoYaExisteException,
			ObjetoNoEncontradoException, CampoInvalidoException {
		CreacionAlquilerHandler creacionInstance = CreacionAlquilerHandler.getInstance();
		creacionInstance.validarCampos();
		Alquiler alquiler = Alquiler.builder().cliente(creacionInstance.getCliente())
				.vehiculo(creacionInstance.getVehiculo()).fechaAlquiler(creacionInstance.getFechaAlquiler())
				.fechaRegreso(creacionInstance.getFechaRegreso()).build();
		return DataService.getInstance().agregarAlquiler(alquiler);
	}

	private void lanzarExceptionCampoInvalido(StringBuilder sb) throws CampoInvalidoException {
		if (!sb.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
			throw new CampoInvalidoException(sb.toString());
		}
	}

	private void requerirCampoString(StringBuilder sb, String cadena, String msg) {
		if (cadena == null || cadena.isBlank()) {
			sb.append(msg);
			sb.append("\n");
		}
	}

	public Vehiculo obtenerVehiculo(String placa) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, placa, "El vehiculo no fue encontrado");
		lanzarExceptionCampoInvalido(sb);
		return DataService.getInstance().buscarVehiculo(placa);
	}

	public void actualizarCliente(String cedula, String nombre, String numeroTel, String email, String ciudad,
			String direccion) throws CampoInvalidoException, ObjetoNoEncontradoException {
		Cliente cliente = obtenerCliente(cedula);
		if (nombre != null)
			cliente.setNombre(nombre);
		if (numeroTel != null)
			cliente.setNumeroTel(numeroTel);
		if (email != null)
			cliente.setEmail(email);
		if (ciudad != null)
			cliente.setCiudad(ciudad);
		if (direccion != null)
			cliente.setDireccion(direccion);
		DataService.getInstance().actualizarCliente(cliente);
	}

	public Cliente obtenerCliente(String cedula) throws CampoInvalidoException, ObjetoNoEncontradoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, cedula, "La cedula es invalida");
		lanzarExceptionCampoInvalido(sb);
		return DataService.getInstance().obtenerClienteThrow(cedula);
	}

	public Double getGanadoAlquileres(LocalDate fechaInicial, LocalDate fechaFinal) throws CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		if (fechaInicial == null || fechaFinal == null) {
			sb.append("Recuerda seleccionar ambas fechas");
			sb.append('\n');
		} else if (fechaFinal.isBefore(fechaInicial)) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		}
		lanzarExceptionCampoInvalido(sb);
		return DataService.getInstance().getGanadoAlquileres(fechaInicial, fechaFinal);
	}

}
