
package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.dao.AlquilerDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.UtilsJPA;

public class DataService {
	private static DataService instance;
	private static final Logger LOGGER = Logger.getLogger(DataService.class.getName());

	private DataService() {
		FileHandler fh;
		try {
			fh = new FileHandler("logs.log", true);
			fh.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fh);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public static DataService getInstance() {
		if (instance == null)
			instance = new DataService();
		return instance;
	}

	public Double getTotalGanadoAlquileres() throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Obteniendo total ganado por alquileres");
			Double total = getTotalGanadoAlquileresThrow();
			LOGGER.info("El total ganado por alquileres fue obtenido satisfactoriamente");
			return total;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		}
	}

	private Double getTotalGanadoAlquileresThrow() throws PersiscenciaDesconocidaException {
		return listarAlquileres().stream().map(Alquiler::obtenerCostoTotal).mapToDouble(d -> d).sum();
	}

	public String getMarcaMasAlquilada() throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Obteniendo marca mas alquilada...");
			String marcaMasAlquilada = getMarcaMasAlquiladaThrows();
			LOGGER.info("La marca mas alquilada ha sido obtenida con exito");
			return marcaMasAlquilada;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		}
	}

	private String getMarcaMasAlquiladaThrows() throws PersiscenciaDesconocidaException {
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
			throws PersiscenciaDesconocidaException {
		try {

			LOGGER.info("Listando vehiculos en rango de fechas");
			List<Vehiculo> vehiculosRangoFechas = listarVehiculosRangoFechasThrow(fechaInicial, fechaFinal);
			LOGGER.info("Se ha podido obtener la lista de vehículos");
			return vehiculosRangoFechas;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		}

	}

	private List<Vehiculo> listarVehiculosRangoFechasThrow(LocalDate fechaInicial, LocalDate fechaFinal)
			throws PersiscenciaDesconocidaException {

		LOGGER.info("Intentando listar vehículos entre: " + fechaFinal + " y " + fechaFinal);
		EntityManager em = UtilsJPA.getEntityManager();
		List<Alquiler> alquileres = listarAlquileres(em);
		List<Vehiculo> result = listarVehiculos(em).stream().filter(v -> v.fueCreadoAntesDe(fechaFinal)
				&& vehiculoEstaDisplonibleRangoFechas(alquileres, v, fechaInicial, fechaFinal)).toList();
		em.close();
		return result;
	}

	private boolean vehiculoEstaDisplonibleRangoFechas(List<Alquiler> alquileres, Vehiculo v, LocalDate fechaInicial,
			LocalDate fechaFinal) {
		for (Alquiler alquiler : alquileres) {
			if (alquiler.estaEnRangoFechas(fechaInicial, fechaFinal) && alquiler.getVehiculo().equals(v))
				return false;
		}
		return true;
	}

	public List<Vehiculo> listarVehiculosAlquilados() throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando listar vehículos alquilados");
			List<Vehiculo> vehiculosAlquilados = listarVehiculosAlquiladosThrow();
			LOGGER.info("Se han listado los vehículos alquilados actualmente");
			return vehiculosAlquilados;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		}
	}

	private List<Vehiculo> listarVehiculosAlquiladosThrow() throws PersiscenciaDesconocidaException {
		return listarAlquileres().stream().filter(Alquiler::tieneVehiculoAlquiladoAhora).map(Alquiler::getVehiculo)
				.toList();
	}

	public boolean estaDisponible(Vehiculo vehiculo) throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando determinar si un vehículo está disponible");
			boolean estaDisponible = estaDisponibleThrow(vehiculo);
			LOGGER.info("Se pudo determinar si esta disponible o no");
			return estaDisponible;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		}
	}

	private boolean estaDisponibleThrow(Vehiculo vehiculo) throws PersiscenciaDesconocidaException {
		List<Alquiler> listaAlquilados = listarAlquileres();
		for (Alquiler alquiler : listaAlquilados) {
			if (alquiler.tieneVehiculoAlquiladoAhora(vehiculo)) {
				return false;
			}
		}
		return true;
	}

	public void agregarCliente(Cliente cliente) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {

		try {
			LOGGER.info("Intentando agregar un cliente con cedula '" + cliente.getCedula() + "'...");
			ClienteDao.getManager().agregarCliente(cliente);
			LOGGER.info("El cliente fue agregado satisfactoriamente");
		} catch (Exception e) {
			lanzarDefaultCreacionObjetoException(
					"El cliente con identificacion \"" + cliente.getCedula() + "\" ya se encuentra registrado", e);
		}
	}

	public void agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Agregando vehiculo con placa: '" + vehiculo.getPlaca() + "'...");
			VehiculoDao.getManager().agregarVehiculo(vehiculo);
			LOGGER.info("El vehiculo ha sido agregado satisfactoriamente");
		} catch (Exception e) {
			lanzarDefaultCreacionObjetoException(
					"El vehiculo de placa \"" + vehiculo.getPlaca() + "\" ya se encuentra registrado", e);
		}
	}

	public List<Cliente> listarClientes() throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando listar clientes...");
			List<Cliente> clientes = listarClientesThrow(null);
			LOGGER.info("Los clientes han sido cargados con exito");
			return clientes;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private List<Cliente> listarClientesThrow(EntityManager em) throws Exception {
		if (em != null)
			return ClienteDao.getManager().getClientes();

		ClienteDao manager = ClienteDao.getManager();
		List<Cliente> clientes = manager.getClientes();
		manager.close();
		return clientes;
	}

	public List<Vehiculo> listarVehiculos() throws PersiscenciaDesconocidaException {
		return listarVehiculos(null);
	}

	private List<Vehiculo> listarVehiculos(EntityManager em) throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando listar vehiculos...");
			List<Vehiculo> vehiculos = listarVehiculosThrow(em);
			LOGGER.info("Los vehiculos han sido listados con exito");
			return vehiculos;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private List<Vehiculo> listarVehiculosThrow(EntityManager em) throws Exception {
		if (em != null)
			return VehiculoDao.getManager(em).getVehiculos();
		VehiculoDao vehiculoManager = VehiculoDao.getManager();
		List<Vehiculo> vehiculos = vehiculoManager.getVehiculos();
		vehiculoManager.close();
		return vehiculos;
	}

	public List<Alquiler> listarAlquileres() throws PersiscenciaDesconocidaException {
		return listarAlquileres(null);
	}

	private List<Alquiler> listarAlquileres(EntityManager em) throws PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando listar alquileres");
			List<Alquiler> listarAlquileres = listarAlquileresThrow(em);
			LOGGER.info("Los alquileres han sido listados con exito");
			return listarAlquileres;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private List<Alquiler> listarAlquileresThrow(EntityManager em) throws Exception {
		if (em != null)
			return AlquilerDao.getManager(em).getAlquileres();
		AlquilerDao manager = AlquilerDao.getManager();
		List<Alquiler> alquileres = manager.getAlquileres();
		manager.close();
		return alquileres;
	}

	public void agregarAlquiler(String cedulaCliente, String placaVehiculo, LocalDate fechaAlquiler,
			LocalDate fechaRegreso) throws VehiculoNoDisponibleException, ObjetoYaExisteException,
			PersiscenciaDesconocidaException, ObjetoNoEncontradoException {
		try {
			LOGGER.info("Intentando agregar alquiler");
			EntityManager em = UtilsJPA.getEntityManager();
			Cliente cliente = obtenerCliente(cedulaCliente, em);
			Vehiculo vehiculo = obtenerVehiculo(placaVehiculo, em);
			LOGGER.info("Construyendo alquiler...");
			Alquiler alquiler = Alquiler.builder().cliente(cliente).vehiculo(vehiculo).fechaAlquiler(fechaAlquiler)
					.fechaRegreso(fechaRegreso).build();
			LOGGER.info("Verificando que el vehiculo este disponible...");
			List<Alquiler> alquileres = listarAlquileres(em);
			if (!vehiculoEstaDisplonibleRangoFechas(alquileres, vehiculo, fechaAlquiler, fechaRegreso))
				throw new VehiculoNoDisponibleException("El vehiculo con placa: '" + alquiler.getVehiculo().getPlaca()
						+ "' no está disponible para ese momento");

			LOGGER.info("Agregando Alquiler a cliente...");
			alquiler.getCliente().agregarAlquiler(alquiler);
			LOGGER.info("Agregando Alquiler a vehiculo...");
			alquiler.getVehiculo().agregarAlquiler(alquiler);

			LOGGER.info("Agregando alquiler...");
			agregarAlquilerThrow(alquiler, em);
			em.close();
			LOGGER.info("El alquiler Fue agregado satisfactoriamente");
		} catch (ObjetoYaExisteException | VehiculoNoDisponibleException | ObjetoNoEncontradoException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		} catch (PersiscenciaDesconocidaException e) {
			throw e;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private void agregarAlquilerThrow(Alquiler alquiler, EntityManager em)
			throws VehiculoNoDisponibleException, ObjetoYaExisteException, PersiscenciaDesconocidaException {
		try {
			if (em == null) {
				AlquilerDao manager = AlquilerDao.getManager();
				manager.agregarAlquiler(alquiler);
				manager.close();
			} else {
				AlquilerDao.getManager(em).agregarAlquiler(alquiler);
			}
		} catch (Exception e) {
			lanzarDefaultCreacionObjetoException(
					"El alquiler con ID: '" + alquiler.getId() + "' ya se encuentra registrado", e);
		}
	}

	public Alquiler obtenerAlquiler(long id) throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		return obtenerAlquiler(id, null);
	}

	private Alquiler obtenerAlquiler(long id, EntityManager em)
			throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Obteniendo alquiler...");
			Alquiler alquiler = obtenerAlquilerThrows(id, em);
			if (alquiler == null)
				throw new ObjetoNoEncontradoException("El alquiler no fue encontrado");
			LOGGER.info("El alquiler ha sido obtenido con exito");
			return alquiler;
		} catch (ObjetoNoEncontradoException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private Alquiler obtenerAlquilerThrows(long id, EntityManager em) throws Exception {
		if (em != null)
			return AlquilerDao.getManager(em).obtenerAlquiler(id);
		AlquilerDao alquilerManager = AlquilerDao.getManager();
		Alquiler alquiler = alquilerManager.obtenerAlquiler(id);
		alquilerManager.close();
		return alquiler;
	}

	public Vehiculo obtenerVehiculo(String placa) throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		return obtenerVehiculo(placa, null);
	}

	private Vehiculo obtenerVehiculo(String placa, EntityManager em)
			throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		try {
			Vehiculo vehiculo = obtenerVehiculoThrow(placa, em);
			if (vehiculo == null) {
				throw new ObjetoNoEncontradoException("El vehiculo no fue encontrado");
			}
			LOGGER.info("El vehiculo ha sido encontrado con exito");
			return vehiculo;
		} catch (ObjetoNoEncontradoException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private Vehiculo obtenerVehiculoThrow(String placa, EntityManager em) throws Exception {
		if (em != null)
			return VehiculoDao.getManager(em).obtenerVehiculo(placa);
		VehiculoDao manager = VehiculoDao.getManager();
		Vehiculo vehiculo = manager.obtenerVehiculo(placa);
		manager.close();
		return vehiculo;
	}

	public Cliente obtenerCliente(String cedula) throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		return obtenerCliente(cedula, null);
	}

	private Cliente obtenerCliente(String cedula, EntityManager em)
			throws ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		try {
			LOGGER.info("Intentando buscar el cliente");
			Cliente cliente = obtenerClienteThrow(cedula, em);
			if (cliente == null)
				throw new ObjetoNoEncontradoException("El cliente no fue encontrado");
			LOGGER.info("El cliente ha sido encontrado con exito");
			return cliente;
		} catch (ObjetoNoEncontradoException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		} catch (Exception e) {
			throw obtenerException(e);
		}
	}

	private Cliente obtenerClienteThrow(String cedula, EntityManager em) throws Exception {
		if (em != null)
			return ClienteDao.getManager(em).obtenerCliente(cedula);
		ClienteDao manager = ClienteDao.getManager();
		Cliente cliente = manager.obtenerCliente(cedula);
		manager.close();
		return cliente;

	}

	private void lanzarDefaultCreacionObjetoException(String message, Exception e)
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException {
		Throwable cause = e;
		while (cause != null) {
			if (cause instanceof SQLIntegrityConstraintViolationException) {
				LOGGER.warning(message);
				throw new ObjetoYaExisteException(message);
			}
			cause = cause.getCause();
		}
		throw obtenerException(e);
	}

	private PersiscenciaDesconocidaException obtenerException(Exception e) throws PersiscenciaDesconocidaException {
		PersiscenciaDesconocidaException exception = new PersiscenciaDesconocidaException(
				"Ha pasado un error inesperado con la persistencia", e);
		LOGGER.severe(exception.getMessage());
		return exception;
	}
}
