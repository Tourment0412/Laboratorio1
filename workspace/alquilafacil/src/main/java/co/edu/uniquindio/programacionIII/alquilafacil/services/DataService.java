package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
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

	private void leerListaAlquileres() {
		alquilaFacil.setListaAlquileres(AlquileresDao.getInstance().loadData());
	}

	private void leerListaClientes() {
		try {
			alquilaFacil.setListaClientes(ClientesDao.getInstance().leerArchivoScanner());
		} catch (IOException e) {

			LogHandler.getInstance().logSevere("La ruta no ha sido encontrada");
		}
	}

	private void leerListaVehiculos() {
		try {
			alquilaFacil.setListaVehiculos(VehiculosDao.getInstance().leerArchivoScanner());
		} catch (IOException e) {
			LogHandler.getInstance().logSevere("La ruta no ha sido encontrada");
		}
	}

	public String getMarcaMasAlquilada() throws ElementoNoEncontradoException {
		leerListaAlquileres();
		return alquilaFacil.getMarcaMasAlquilada();
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws ListaVaciaException {
		leerListaAlquileres();
		leerListaVehiculos();
		return alquilaFacil.listarVehiculosDisponiblesRangoFechas(fechaInicial, fechaFinal);
	}
	
	public List<Vehiculo> listarVehiculosFecha(LocalDate fecha) throws ListaVaciaException{
		leerListaAlquileres();
		leerListaVehiculos();
		return alquilaFacil.listarVehiculosAlquiladosFecha(fecha);
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		leerListaAlquileres();
		return alquilaFacil.listarVehiculosAlquilados();
	}

	public boolean estaDisponible(String placa) {
		leerListaAlquileres();
		return alquilaFacil.estaDisponible(placa);
	}

	public Cliente agregarCliente(Cliente cliente) throws ObjetoYaExisteException {
		leerListaClientes();
		alquilaFacil.agregarCliente(cliente);
		try {
			ClientesDao.getInstance().escribirArchivoFormatter(cliente);
		} catch (IOException e) {
			LogHandler.getInstance().logSevere("La ruta no ha sido encontrada");
		}
		return cliente;
	}

	public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException {
		leerListaVehiculos();
		alquilaFacil.agregarVehiculo(vehiculo);
		try {
			VehiculosDao.getInstance().escribirArchivoFormatter(vehiculo);
		} catch (IOException e) {
			LogHandler.getInstance().logSevere("La ruta no ha sido encontrada");
		}
		return vehiculo;
	}

	public Alquiler agregarAlquiler(Alquiler alquiler) throws VehiculoNoDisponibleException {
		leerListaAlquileres();
		leerListaVehiculos();
		alquilaFacil.agregarAlquiler(alquiler);
		AlquileresDao.getInstance().saveData(alquilaFacil.getListaAlquileres());
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

	public Vehiculo buscarVehiculo(String placa) {
		leerListaVehiculos();
		return alquilaFacil.buscarVehiculo(placa);
	}

	public Cliente buscarCliente(String cedula) {
		leerListaClientes();
		return alquilaFacil.buscarCliente(cedula);
	}

	public Cliente obtenerClienteThrow(String cedula) throws ObjetoNoEncontradoException {
		Cliente cliente = buscarCliente(cedula);
		if (cliente == null)
			throw new ObjetoNoEncontradoException("El cliente no fue encontrado");
		return cliente;
	}

	public void actualizarCliente(Cliente cliente) throws ObjetoNoEncontradoException {
		leerListaClientes();
		alquilaFacil.actualizarCliente(cliente);
	}

	public Double getGanadoAlquileres(LocalDate fechaInicial, LocalDate fechaFinal) {
		leerListaAlquileres();
		return alquilaFacil.getGanadoAlquileres(fechaInicial, fechaFinal);
	}
}
