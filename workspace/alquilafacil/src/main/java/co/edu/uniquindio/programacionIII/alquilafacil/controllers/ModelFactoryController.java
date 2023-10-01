package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CommunicationService;

public class ModelFactoryController {

	private static ModelFactoryController instance;

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public Double getTotalGanadoAlquileres() {
		return CommunicationService.getInstance().getTotalGanadoAlquileres();
	}

	public String getMarcaMasAlquilada() {
		return CommunicationService.getInstance().getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws NullPointerException {
		return CommunicationService.getInstance().listarVehiculosRangoFechas(fechaInicial, fechaFinal);
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		return CommunicationService.getInstance().listarVehiculosAlquilados();
	}

	public boolean estaDisponible(Vehiculo vehiculo) {
		return CommunicationService.getInstance().estaDisponible(vehiculo);
	}

	public void agregarCliente(Cliente cliente) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().agregarCliente(cliente);
	}

	public void agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().agregarVehiculo(vehiculo);
	}

	public void agregarAlquiler(Alquiler alquiler)
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, VehiculoNoDisponibleException {
		CommunicationService.getInstance().agregarAlquiler(alquiler);
	}

	public List<Alquiler> listarAlquileres() {
		return CommunicationService.getInstance().listarAlquileres();
	}

	public List<Vehiculo> listarVehiculos() {
		return CommunicationService.getInstance().listarVehiculos();
	}

	public List<Cliente> listarClientes() {
		return CommunicationService.getInstance().listarClientes();
	}

	public void agregarAlquiler(String cedulaCliente, String placaVehiculo, LocalDate fechaAlquiler,
			LocalDate fechaRegreso)
			throws VehiculoNoDisponibleException, ObjetoYaExisteException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().agregarAlquiler(cedulaCliente, placaVehiculo, fechaAlquiler, fechaRegreso);
	}

}
