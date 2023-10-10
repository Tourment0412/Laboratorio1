package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.services.LogHandler;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlquilaFacil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	private List<Vehiculo> listaVehiculos;
	@NonNull
	private List<Cliente> listaClientes;

	@NonNull
	private List<Alquiler> listaAlquileres;

	public AlquilaFacil() {
		this.listaVehiculos = new ArrayList<>();
		this.listaClientes = new ArrayList<>();
		this.listaAlquileres = new ArrayList<>();
	}

	public boolean vehiculoEstaDisplonibleRangoFechas(Vehiculo v, LocalDate fechaInicial, LocalDate fechaFinal) {
		for (Alquiler alquiler : listaAlquileres)
			if (alquiler.estaEnRangoFechas(fechaInicial, fechaFinal) && alquiler.getVehiculo().equals(v))
				return false;
		return true;
	}

	public List<Vehiculo> listarVehiculosDisponiblesRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
			throws ListaVaciaException {
		LogHandler.getInstance().logInfo("Listando vehiculos en rango de fechas");
		List<Vehiculo> vehiculosRangoFechas = listarVehiculosRangoFechasThrow(fechaInicial, fechaFinal);
		LogHandler.getInstance().logInfo("Se ha podido obtener la lista de vehículos");
		if (vehiculosRangoFechas.isEmpty())
			throw new ListaVaciaException("No hay vehiculos en ese rango de fechas, elige otro rango");
		return vehiculosRangoFechas;
	}

	private List<Vehiculo> listarVehiculosRangoFechasThrow(LocalDate fechaInicial, LocalDate fechaFinal)
			throws ListaVaciaException {
		LogHandler.getInstance().logInfo("Intentando listar vehículos entre: " + fechaInicial + " y " + fechaFinal);
		ArrayList<Vehiculo> result = listaVehiculos.stream().filter(
				v -> v.fueCreadoAntesDe(fechaFinal) && vehiculoEstaDisplonibleRangoFechas(v, fechaInicial, fechaFinal))
				.collect(Collectors.toCollection(ArrayList::new));
		result.sort((o1, o2) -> o1.getPrecioAlquilerDia().compareTo(o2.getPrecioAlquilerDia()));
		return result;
	}

	public List<Vehiculo> listarVehiculosAlquiladosFecha(LocalDate fecha) throws ListaVaciaException {
		LogHandler.getInstance().logInfo("Intenando listar vehiculos en la fecha" + fecha);
		List<Vehiculo> vehiculosFecha = listarVehiculosAlquiladosFechaThrow(fecha);
		LogHandler.getInstance().logInfo("Se ha podido obtener los vechiculos alquilados en fecha dada");
		if (vehiculosFecha.isEmpty())
			throw new ListaVaciaException("No hay vehiculos alquilados para la fecha dada");
		return vehiculosFecha;
	}

	private List<Vehiculo> listarVehiculosAlquiladosFechaThrow(LocalDate fecha) {
		ArrayList<Vehiculo> result = listaVehiculos.stream().filter(v -> vehiculoAquiladoFecha(v, fecha))
				.collect(Collectors.toCollection(ArrayList::new));
		return result;
	}

	public boolean vehiculoAquiladoFecha(Vehiculo v, LocalDate fecha) {
		for (Alquiler alquiler : listaAlquileres) {
			if (alquiler.estaEnFecha(fecha) && alquiler.getVehiculo().equals(v))
				return true;

		}
		return false;
	}

	public Cliente agregarCliente(Cliente cliente) throws ObjetoYaExisteException {
		try {
			LogHandler.getInstance()
					.logInfo("Intentando agregar un cliente con cedula '" + cliente.getCedula() + "'...");
			agregarClienteThrow(cliente);
			LogHandler.getInstance().logInfo("El cliente fue agregado satisfactoriamente");
		} catch (ObjetoYaExisteException e) {
			LogHandler.getInstance().logWarning(e.getMessage());
			throw e;
		}
		return cliente;
	}

	private void agregarClienteThrow(Cliente cliente) throws ObjetoYaExisteException {
		if (existeCliente(cliente.getCedula()))
			throw new ObjetoYaExisteException(
					"El cliente con identificacion '" + cliente.getCedula() + "' ya se encuentra registrado");
		listaClientes.add(cliente);
	}

	public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws ObjetoYaExisteException {
		try {
			LogHandler.getInstance().logInfo("Agregando vehiculo con placa: '" + vehiculo.getPlaca() + "'...");
			agregarVehiculoThrow(vehiculo);
			LogHandler.getInstance().logInfo("El vehiculo ha sido agregado satisfactoriamente");
		} catch (ObjetoYaExisteException e) {
			LogHandler.getInstance()
					.logWarning("El vehiculo de placa \"" + vehiculo.getPlaca() + "\" ya se encuentra registrado");
			throw e;
		}
		return vehiculo;
	}

	private void agregarVehiculoThrow(Vehiculo vehiculo) throws ObjetoYaExisteException {
		if (existeVehiculo(vehiculo.getPlaca()))
			throw new ObjetoYaExisteException(
					"El vehiculo de placa \"" + vehiculo.getPlaca() + "\" ya se encuentra registrado");
		listaVehiculos.add(vehiculo);
	}

	public Alquiler agregarAlquiler(Alquiler alquiler) throws VehiculoNoDisponibleException {
		try {
			LogHandler.getInstance().logInfo("Intentando agregar alquiler");
			agregarAlquilerThrow(alquiler);
			LogHandler.getInstance().logInfo("El alquiler ha sido agregado satisfactoriamente");
		} catch (VehiculoNoDisponibleException e) {
			LogHandler.getInstance().logWarning(e.getMessage());
			throw e;
		}
		return alquiler;
	}

	public String getFreeAlquilerId() {
		String id = Utils.getInstance().crearCodigoRandomAquiler();
		return !existeAlquiler(id) ? id : getFreeAlquilerId();
	}

	private Alquiler agregarAlquilerThrow(Alquiler alquiler) throws VehiculoNoDisponibleException {
		if (!vehiculoEstaDisplonibleRangoFechas(alquiler.getVehiculo(), alquiler.getFechaAlquiler(),
				alquiler.getFechaRegreso()))
			throw new VehiculoNoDisponibleException("El vehiculo con placa: '" + alquiler.getVehiculo().getPlaca()
					+ "' no está disponible para ese momento");
		alquiler.setId(getFreeAlquilerId());
		listaAlquileres.add(alquiler);
		return alquiler;
	}

	private boolean existeCliente(String cedula) {
		return buscarCliente(cedula) != null;
	}

	private boolean existeVehiculo(String placa) {
		return buscarVehiculo(placa) != null;
	}

	private boolean existeAlquiler(String id) {
		return buscarAlquiler(id) != null;
	}

	public Cliente buscarCliente(String cedula) {
		LogHandler.getInstance().logInfo("Intentando buscar un cliente con cedula: " + cedula);
		for (Cliente cliente : listaClientes)
			if (cliente.getCedula().equals(cedula)) {
				LogHandler.getInstance().logInfo("El cliente fue encontrado con exito");
				return cliente;
			}
		LogHandler.getInstance().logInfo("El cliente no fue encontrado");
		return null;
	}

	public Vehiculo buscarVehiculo(String placa) {
		LogHandler.getInstance().logInfo("Intentando buscar un vehiculo con placa: " + placa);
		for (Vehiculo vehiculo : listaVehiculos)
			if (vehiculo.getPlaca().equals(placa)) {
				LogHandler.getInstance().logInfo("El vehiculo fue encontrado con exito");
				return vehiculo;
			}
		LogHandler.getInstance().logInfo("El vehiculo no fue encontrado");
		return null;
	}

	public Alquiler buscarAlquiler(String id) {
		LogHandler.getInstance().logInfo("Intentando buscar un alquiler con ID: " + id);
		for (Alquiler alquiler : listaAlquileres)
			if (alquiler.getId().equals(id)) {
				LogHandler.getInstance().logInfo("El alquiler fue encontrado con exito");
				return alquiler;
			}
		LogHandler.getInstance().logInfo("El alquiler no fue encontrado");
		return null;
	}

	public Double getTotalGanadoAlquileres() {
		LogHandler.getInstance().logInfo("Obteniendo total ganado por alquileres");
		Double total = listaAlquileres.stream().map(Alquiler::obtenerCostoTotal).mapToDouble(d -> d).sum();
		LogHandler.getInstance().logInfo("El total ganado por alquileres fue obtenido satisfactoriamente");
		return total;
	}

	public Double getGanadoAlquileres(LocalDate fechaInicial, LocalDate fechaFinal) {
		LogHandler.getInstance()
				.logInfo("Obteniendo total ganado por alquileres entre " + fechaInicial + " y " + fechaFinal);
		Double total = listaAlquileres.stream().filter(alquiler -> alquiler.estaEnRangoFechas(fechaInicial, fechaFinal))
				.map(alquiler -> alquiler.obtenerCostoTotal()).mapToDouble(d -> d).sum();
		LogHandler.getInstance().logInfo("El total ganado por alquileres fue obtenido satisfactoriamente");
		return total;

	}

	public String getMarcaMasAlquilada() throws ElementoNoEncontradoException {
		try {
			LogHandler.getInstance().logInfo("Obteniendo marca mas alquilada...");
			String marca = getMarcaMasAlquiladaAux();
			LogHandler.getInstance().logInfo("La marca mas alquilada ha sido obtenida con exito");
			return marca;
		} catch (ElementoNoEncontradoException e) {
			LogHandler.getInstance().logWarning(e.getMessage());
			throw e;
		}
	}

	private String getMarcaMasAlquiladaAux() throws ElementoNoEncontradoException {

		List<String> list = listaAlquileres.stream().map(alquiler -> alquiler.getVehiculo().getMarca()).toList();
		if (list.size() == 0)
			throw new ElementoNoEncontradoException("La marca mas alquilada no fue encontrada");
		Map<String, Integer> mapeo = new HashMap<>();
		for (String string : list) {
			mapeo.put(string, mapeo.getOrDefault(mapeo, 0) + 1);
		}
		String marcaMax = null;
		int max = 0;
		for (Map.Entry<String, Integer> entry : mapeo.entrySet()) {
			if (entry.getValue() > max) {
				marcaMax = entry.getKey();
				max = entry.getValue();
			}
		}
		if (marcaMax == null)
			throw new ElementoNoEncontradoException("La marca mas alquilada no fue encontrada");
		return marcaMax;
	}

	public List<Vehiculo> listarVehiculosAlquilados() {
		LogHandler.getInstance().logInfo("Listando vehículos alquilados");
		List<Vehiculo> vehiculosAlquilados = listaAlquileres.stream().filter(Alquiler::tieneVehiculoAlquiladoAhora)
				.map(Alquiler::getVehiculo).toList();
		LogHandler.getInstance().logInfo("Se han listado los vehículos alquilados actualmente");
		return vehiculosAlquilados;
	}

	public boolean estaDisponible(String placa) {
		LogHandler.getInstance().logInfo("Determinando si el vehículo está disponible");
		Vehiculo vehiculo = buscarVehiculo(placa);
		for (Alquiler alquiler : listaAlquileres) {
			if (alquiler.tieneVehiculoAlquiladoAhora(vehiculo)) {
				LogHandler.getInstance().logInfo("El vehiculo no esta disponible");
				return false;
			}
		}
		LogHandler.getInstance().logInfo("El vehiculo si esta disponible");
		return true;
	}

	public void actualizarCliente(Cliente cliente) throws ObjetoNoEncontradoException {
		LogHandler.getInstance().logInfo("Actualizando el cliente");
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).equals(cliente)) {
				LogHandler.getInstance().logInfo("El cliente fue encontrado");
				listaClientes.set(i, cliente);
				return;
			}
		}
		throw new ObjetoNoEncontradoException("El cliente no fue encontrado");
	}
}
