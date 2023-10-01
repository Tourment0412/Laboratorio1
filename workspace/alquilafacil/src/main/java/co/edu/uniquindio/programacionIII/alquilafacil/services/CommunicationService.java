package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.dao.AlquilerDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;

public class CommunicationService {
	private static CommunicationService instance;

	private CommunicationService() {
	}

	public static CommunicationService getInstance() {
		if (instance == null)
			instance = new CommunicationService();
		return instance;
	}

	public Double getTotalGanadoAlquileres() {
		return listarAlquileres().stream().map(Alquiler::obtenerCostoTotal).mapToDouble(d -> d).sum();
	}

	public String getMarcaMasAlquilada() {
		List<String> list = listarAlquileres().stream().map(alquiler -> alquiler.getVehiculo().getMarca()).toList();
		if (list.size() == 0)
			return null;
		Map<String, Integer> mapeo = new HashMap<>();
		for (String string : list) {
			mapeo.put(string, mapeo.getOrDefault(mapeo, 0) + 1);
		}
		String marcaMax = "";
		int max = 0;
		for (Map.Entry<String, Integer> entry : mapeo.entrySet()) {
			if (entry.getValue() > max) {
				marcaMax = entry.getKey();
				max = entry.getValue();
			}
		}
		return marcaMax;
	}

	public List<Vehiculo> listarVehiculosRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws NullPointerException {
		Objects.requireNonNull(fechaInicial, "La fecha inicial no puede ser null");
		Objects.requireNonNull(fechaFinal, "La fecha final no puede ser null");
		EntityManager em = UtilsJPA.getEntityManager();
		List<Vehiculo> result = listarVehiculos(em).stream()
				.filter(v -> v.fueCreadoAntesDe(fechaFinal)
						&& vehiculoEstaDisplonibleRangoFechas(listarAlquileres(em), v, fechaInicial, fechaFinal))
				.toList();
		em.close();
		return result;
	}

	private boolean vehiculoEstaDisplonibleRangoFechas(List<Alquiler> alquileres, Vehiculo v, LocalDate fechaInicial,
			LocalDate fechaFinal) {
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getFechaRegreso().isBefore(fechaInicial) || alquiler.getFechaAlquiler().isAfter(fechaFinal))
				continue;
			if (alquiler.getVehiculo().equals(v))
				return false;
		}
		return true;
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		return listarAlquileres().stream().filter(Alquiler::tieneVehiculoAlquiladoAhora).map(Alquiler::getVehiculo)
				.toList();
	}

	public boolean estaDisponible(Vehiculo vehiculo) {
		List<Alquiler> listaAlquilados = listarAlquileres().stream().toList();
		for (Alquiler alquiler : listaAlquilados) {
			if (alquiler.tieneVehiculoAlquiladoAhora(vehiculo))
				return false;
		}
		return true;
	}

	public void agregarCliente(Cliente cliente) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		try {
			ClienteDao.getClienteManager().agregarCliente(cliente);
		} catch (Exception e) {
			lanzarDefaultCreacionObjetoException(
					"El cliente con identificacion \"" + cliente.getCedula() + "\" ya se encuentra registrado", e);
		}
	}

	public void agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		try {
			VehiculoDao.getVehiculoManager().agregarVehiculo(vehiculo);
		} catch (Exception e) {
			lanzarDefaultCreacionObjetoException(
					"El vehiculo de placa \"" + vehiculo.getPlaca() + "\" ya se encuentra registrado", e);
		}
	}

	public void agregarAlquiler(Alquiler alquiler)
			throws VehiculoNoDisponibleException, ObjetoYaExisteException, PersiscenciaDesconocidaException {
		try {
			if (!estaDisponible(alquiler.getVehiculo()))
				throw new VehiculoNoDisponibleException(
						"El vehiculo con placa: '" + alquiler.getVehiculo().getPlaca() + "' no está disponible");
			AlquilerDao.getAlquilerManager().agregarAlquiler(alquiler);
		} catch (Exception e) {
			if (e instanceof VehiculoNoDisponibleException)
				throw (VehiculoNoDisponibleException) e;
			lanzarDefaultCreacionObjetoException(
					"El alquiler con ID: '" + alquiler.getId() + "' ya se encuentra registrado", e);
		}
	}

	public List<Cliente> listarClientes() {
		return ClienteDao.getClienteManager().getClientes();
	}

	public List<Vehiculo> listarVehiculos() {
		VehiculoDao vehiculoManager = VehiculoDao.getVehiculoManager();
		List<Vehiculo> vehiculos = vehiculoManager.getVehiculos();
		vehiculoManager.close();
		return vehiculos;
	}

	private List<Vehiculo> listarVehiculos(EntityManager em) {
		return VehiculoDao.getVehiculoManager(em).getVehiculos();
	}

	public List<Alquiler> listarAlquileres() {
		return AlquilerDao.getAlquilerManager().getAlquileres();
	}

	private List<Alquiler> listarAlquileres(EntityManager em) {
		return AlquilerDao.getAlquilerManager(em).getAlquileres();
	}

	private void lanzarDefaultCreacionObjetoException(String message, Exception e)
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		Throwable cause = e;
		while (cause != null) {
			if (cause instanceof SQLIntegrityConstraintViolationException) {
				throw new ObjetoYaExisteException(message);
			}
			cause = cause.getCause();
		}
		throw new PersiscenciaDesconocidaException("Ha pasado un error inesperado con la persistencia", e);
	}

	public void agregarAlquiler(String cedulaCliente, String placaVehiculo, LocalDate fechaAlquiler,
			LocalDate fechaRegreso)
			throws VehiculoNoDisponibleException, ObjetoYaExisteException, PersiscenciaDesconocidaException {
		EntityManager em = UtilsJPA.getEntityManager();
		Cliente cliente = ClienteDao.getClienteManager(em).obtenerCliente(cedulaCliente);
		Vehiculo vehiculo = VehiculoDao.getVehiculoManager(em).obtenerVehiculo(cedulaCliente);
		
		Alquiler alquiler = Alquiler.builder().cliente(cliente).vehiculo(vehiculo).fechaAlquiler(fechaAlquiler)
				.fechaRegreso(fechaRegreso).build();
		cliente.agregarAlquiler(alquiler);
		vehiculo.agregarAlquiler(alquiler);
		em.close();
		agregarAlquiler(alquiler);

	}

}
