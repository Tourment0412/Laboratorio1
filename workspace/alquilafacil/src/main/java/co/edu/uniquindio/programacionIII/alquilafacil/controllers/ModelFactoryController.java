package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
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

	public Double getTotalGanadoAlquileres() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().getTotalGanadoAlquileres();
	}

	public String getMarcaMasAlquilada() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws NullPointerException, PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().listarVehiculosRangoFechas(fechaInicial, fechaFinal);
	}

	public List<Vehiculo> listarVehiculosAlquilados() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().listarVehiculosAlquilados();
	}

	public boolean estaDisponible(Vehiculo vehiculo) throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().estaDisponible(vehiculo);
	}

	public void agregarCliente(Cliente cliente) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().agregarCliente(cliente);
	}

	public void agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().agregarVehiculo(vehiculo);
	}

	public List<Alquiler> listarAlquileres() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().listarAlquileres();
	}

	public List<Vehiculo> listarVehiculos() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().listarVehiculos();
	}

	public List<Cliente> listarClientes() throws PersiscenciaDesconocidaException {
		return CommunicationService.getInstance().listarClientes();
	}

	public void agregarAlquiler(String cedulaCliente, String placaVehiculo, LocalDate fechaAlquiler,
			LocalDate fechaRegreso) throws VehiculoNoDisponibleException, ObjetoYaExisteException,
			PersiscenciaDesconocidaException, NullPointerException, ObjetoNoEncontradoException {
		CommunicationService.getInstance().agregarAlquiler(cedulaCliente, placaVehiculo, fechaAlquiler, fechaRegreso);
	}

}
