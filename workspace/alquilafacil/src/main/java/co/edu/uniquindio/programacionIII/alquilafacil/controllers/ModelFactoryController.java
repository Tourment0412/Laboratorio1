package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;
import javafx.scene.image.Image;

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
			throws PersiscenciaDesconocidaException {
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

	public void agregarVehiculo(String placa, String nombre, String marca, Integer modelo, Image image,
			Transmision transmision, Integer kilometraje, Double precioAlquilerDia, Integer numSillas)
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, ImagenNoObtenidaException,
			CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, placa, "La placa no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, marca, "La marca no puede estar vacia");
		if (modelo == null || modelo < 0) {
			sb.append("El modelo es invalido");
			sb.append('\n');
		}
		if (transmision == null) {
			sb.append("La transmision es invalida");
			sb.append('\n');
		}
		if (precioAlquilerDia == null || precioAlquilerDia < 0) {
			sb.append("El precio de alquiler por dia es invalido");
			sb.append('\n');
		}
		if (numSillas == null || numSillas < 0) {
			sb.append("El numero de sillas es invalido");
			sb.append('\n');
		}
		if (image == null) {
			sb.append("La imagen es invalida");
			sb.append('\n');
		}
		if (kilometraje == null || kilometraje < 0) {
			sb.append("El kilometraje es invalido");
			sb.append('\n');
		}
		lanzarCampoInvalido(sb);

		Vehiculo vehiculo = Vehiculo.builder().placa(placa).nombre(nombre).marca(marca).modelo(modelo).image(image)
				.transmision(transmision).kilometraje(kilometraje).precioAlquilerDia(precioAlquilerDia)
				.numSillas(numSillas).build();
		DataService.getInstance().agregarVehiculo(vehiculo);
	}

	public List<Alquiler> listarAlquileres() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarAlquileres();
	}

	public List<Vehiculo> listarVehiculos() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarVehiculos();
	}

	public List<Cliente> listarClientes() throws PersiscenciaDesconocidaException {
		return DataService.getInstance().listarClientes();
	}

	public Alquiler agregarAlquiler(String cedulaCliente, String placaVehiculo, LocalDate fechaAlquiler,
			LocalDate fechaRegreso) throws VehiculoNoDisponibleException, ObjetoYaExisteException,
			PersiscenciaDesconocidaException, ObjetoNoEncontradoException, CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, cedulaCliente, "La cedula no puede estar vacia");
		requerirCampoString(sb, placaVehiculo, "La placa no puede estar vacia");
		if (fechaRegreso == null || fechaAlquiler == null) {
			sb.append("Ninguna fecha puede ser null");
			sb.append('\n');
		}
		if (fechaRegreso.isBefore(fechaAlquiler)) {
			sb.append("El rango de fechas es inválido");
			sb.append('\n');
		}
		if (fechaAlquiler.isEqual(fechaRegreso)) {
			sb.append("Las fechas no pueden ser las mismas");
			sb.append('\n');
		}
		lanzarCampoInvalido(sb);
		return DataService.getInstance().agregarAlquiler(cedulaCliente, placaVehiculo, fechaAlquiler, fechaRegreso);
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
