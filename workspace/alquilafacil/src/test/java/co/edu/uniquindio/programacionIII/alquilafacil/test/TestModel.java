package co.edu.uniquindio.programacionIII.alquilafacil.test;

import java.time.LocalDate;

import org.junit.Test;

import co.edu.uniquindio.programacionIII.alquilafacil.controllers.ModelFactoryController;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestModel extends Application {
	@Test
	public void crearCliente() {
		Cliente c = Cliente.builder().cedula("1059386396").ciudad("Armenia").direccion("Luigi´s mansion")
				.email("santaigo@gmail.com").nombre("Santaigo").numeroTel("396286039743").build();

		try {
			ModelFactoryController.getInstance().agregarCliente(c);
		} catch (ObjetoYaExisteException | PersiscenciaDesconocidaException e) {
			System.out.println("---------------------------");
			System.out.println(e.toString());
			System.out.println("---------------------------");
		}

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
	public void crearAlquiler()
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, VehiculoNoDisponibleException {

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
				.image(new Image(getClass()
						.getResource("/co/edu/uniquindio/programacionIII/alquilafacil/sources/defaultimg.png")
						.toExternalForm()))
				.kilometraje(100).marca("Chevrolet").nombre("Onix").numSillas(5).placa("JFX020").modelo(2023)
				.precioAlquilerDia(500000d).transmision(Transmision.MANUAL).build();
		System.out.println(vehiculo);
		clienteManager.actualizarVehiculo(vehiculo);
		clienteManager.close();
	}

}
