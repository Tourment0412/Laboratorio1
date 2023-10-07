package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.AlquilaFacil;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;

public class DataService {

	private static DataService instance;

	private AlquilaFacil alquilaFacil;

	public static DataService getInstance() {
		if (instance == null)
			instance = new DataService();
		return instance;
	}

	private DataService() {
		this.alquilaFacil = new AlquilaFacil();
	}

	public Double getTotalGanadoAlquileres() {
		leerListaAlquileres();
		return alquilaFacil.getTotalGanadoAlquileres();
	}

	private void leerListaAlquileres() {// TODO
		alquilaFacil.setListaAlquileres(null);
	}

	private void leerListaClientes() {
		// TODO
	}

	private void leerListaVehiculos() {
		// TODO
	}

	private void guardarListaClientes(List<Cliente> listaClientes) {
		// TODO
	}

	private void guardarListaVehiculos(List<Vehiculo> listaVehiculos) {
		// TODO
	}

	private void guardarListaAlquileres(List<Alquiler> listaAlquileres) {
		// TODO
	}

	public String getMarcaMasAlquilada() {
		leerListaAlquileres();
		return alquilaFacil.getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws ListaVaciaException {
		leerListaAlquileres();
		leerListaVehiculos();
		return alquilaFacil.listarVehiculosRangoFechas(fechaInicial, fechaFinal);
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		leerListaAlquileres();
		return alquilaFacil.listarVehiculosAlquilados();
	}

	public boolean estaDisponible(Vehiculo vehiculo) {
		leerListaAlquileres();
		return alquilaFacil.estaDisponible(vehiculo);
	}

	public Cliente agregarCliente(Cliente cliente) throws ObjetoYaExisteException {
		leerListaClientes();
		alquilaFacil.agregarCliente(cliente);
		guardarListaClientes(alquilaFacil.getListaClientes());
		return cliente;
	}

	public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException {
		leerListaVehiculos();
		alquilaFacil.agregarVehiculo(vehiculo);
		guardarListaVehiculos(alquilaFacil.getListaVehiculos());
		return vehiculo;
	}

	public Alquiler agregarAlquiler(Alquiler alquiler) throws VehiculoNoDisponibleException {
		leerListaAlquileres();
		leerListaVehiculos();
		alquilaFacil.agregarAlquiler(alquiler);
		guardarListaAlquileres(alquilaFacil.getListaAlquileres());
		return alquiler;
	}

	public List<Alquiler> listarAlquileres() {
		leerListaAlquileres();
		return alquilaFacil.getListaAlquileres();
	}

	public List<Vehiculo> listarVehiculos() {
		leerListaVehiculos();
		return alquilaFacil.getListaVehiculos();
	}

	public List<Cliente> listarClientes() {
		leerListaClientes();
		return alquilaFacil.getListaClientes();
	}
}
