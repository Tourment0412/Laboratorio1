package co.edu.uniquindio.programacionIII.alquilafacil.test;

import java.time.LocalDate;

import org.junit.Test;

import co.edu.uniquindio.programacionIII.alquilafacil.controllers.ModelFactoryController;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CreacionAlquilerHandler;

public class TestModel {
	@Test
	public void crearCliente() throws ObjetoYaExisteException, CampoInvalidoException {

		ModelFactoryController.getInstance().agregarCliente("1059386396", "Santiago", "396286039743",
				"santaigo@gmail.com", "Armenia", "Calle 13");

	}

	@Test
	public void obtenerCliente() throws CampoInvalidoException, ObjetoNoEncontradoException {
		ModelFactoryController.getInstance().obtenerCliente("golahoasda");
	}

	@Test
	public void actualizarCliente() throws Exception {
		ModelFactoryController.getInstance().actualizarCliente("1059386396", "Pepe", null, null, null, null);
	}

	@Test
	public void crearAlquiler() throws CampoInvalidoException, ObjetoNoEncontradoException,
			VehiculoNoDisponibleException, ObjetoYaExisteException {
		ModelFactoryController modelFactory = ModelFactoryController.getInstance();
		CreacionAlquilerHandler.getInstance().selectVehiculo(modelFactory.obtenerVehiculo("JFX020"));
		CreacionAlquilerHandler.getInstance().selectCliente(modelFactory.obtenerCliente("1059386396"));
		CreacionAlquilerHandler.getInstance().selectDates(LocalDate.now().plusDays(20), LocalDate.now().plusMonths(2));
		ModelFactoryController.getInstance().agregarAlquiler();
	}

	@Test
	public void crearVehiculo() throws Exception {
		ModelFactoryController.getInstance().agregarVehiculo("JFX020", "Chevrolet", "Onix", "2023", "hola.png",
				"Manual", "10000", "500000", "5");
	}

	@Test
	public void listarClientes() throws Exception {
		ModelFactoryController.getInstance().listarClientes();
	}

}
