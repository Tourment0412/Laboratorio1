package co.edu.uniquindio.programacionIII.alquilafacil.test;

import java.time.LocalDate;

import org.junit.Test;

import co.edu.uniquindio.programacionIII.alquilafacil.controllers.ModelFactoryController;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CommunicationService;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestModel extends Application {
	@Test
	public void crearCliente()
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, CampoInvalidoException {

		ModelFactoryController.getInstance().agregarCliente("1059386396", "Santiago", "396286039743",
				"santaigo@gmail.com", "Armenia", "Calle 13");

	}

	@Test
	public void obtenerCliente()
			throws NullPointerException, ObjetoNoEncontradoException, PersiscenciaDesconocidaException {
		CommunicationService.getInstance().obtenerCliente("golahoasda");
	}

	@Test
	public void actualizarCliente() throws Exception {
		ClienteDao clienteManager = ClienteDao.getManager();
		Cliente cliente = clienteManager.obtenerCliente("1059386396");
		cliente.setDireccion("W el sobreviviente con Raquel");
		clienteManager.actualizarCliente(cliente);
		clienteManager.close();
	}

	@Test
	public void crearAlquiler() throws ObjetoYaExisteException, PersiscenciaDesconocidaException,
			VehiculoNoDisponibleException, NullPointerException, ObjetoNoEncontradoException, CampoInvalidoException {
		ModelFactoryController.getInstance().agregarAlquiler("1059386396", "JFX020", LocalDate.now().plusDays(20),
				LocalDate.now().plusMonths(2));
	}

	@Test
	public void crearVehiculo() throws Exception {
		launch(new String[0]);
	}

	@Test
	public void listarClientes() throws Exception {
		ClienteDao clienteManager = ClienteDao.getManager();
		System.out.println(clienteManager.getClientes());
		clienteManager.close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VehiculoDao clienteManager = VehiculoDao.getManager();
		Vehiculo vehiculo = Vehiculo.builder()
				.image(new Image(
						getClass().getResource("/co/edu/uniquindio/programacionIII/alquilafacil/sources/defaultimg.png")
								.toExternalForm()))
				.kilometraje(100).marca("Chevrolet").nombre("Onix").numSillas(5).placa("JFX020").modelo(2023)
				.precioAlquilerDia(500000d).transmision(Transmision.MANUAL).build();
		clienteManager.actualizarVehiculo(vehiculo);
		clienteManager.close();
	}

}
