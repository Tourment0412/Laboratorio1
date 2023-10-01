package co.edu.uniquindio.programacionIII.alquilafacil.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Test;

import co.edu.uniquindio.programacionIII.alquilafacil.controllers.ModelFactoryController;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.AlquilerDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.UtilsJPA;
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
		ClienteDao clienteManager = ClienteDao.getClienteManager();
		Cliente cliente = clienteManager.obtenerCliente("1059386396");
		cliente.setDireccion("W el sobreviviente con Raquel");
		clienteManager.actualizarCliente(cliente);
		clienteManager.close();
	}

	@Test
	public void crearAlquiler()
			throws ObjetoYaExisteException, PersiscenciaDesconocidaException, VehiculoNoDisponibleException {
		EntityManager em = UtilsJPA.getEntityManager();
		AlquilerDao alquilerManager = AlquilerDao.getAlquilerManager(em);
		ClienteDao clienteManager = ClienteDao.getClienteManager(em);
		VehiculoDao vehiculoManager = VehiculoDao.getVehiculoManager(em);

		Alquiler alquiler = Alquiler.builder().cliente(clienteManager.obtenerCliente("1059386396"))
				.vehiculo(vehiculoManager.obtenerVehiculo("JFX020")).fechaAlquiler(LocalDate.now().plusDays(20))
				.fechaRegreso(LocalDate.now().plusMonths(2)).build();
		ModelFactoryController.getInstance().agregarAlquiler("1059386396", "JFX020", LocalDate.now().plusDays(20),
				LocalDate.now().plusMonths(2));

		ModelFactoryController.getInstance().agregarAlquiler(alquiler);
		alquilerManager.agregarAlquiler(alquiler);
		alquilerManager.close();
	}

	@Test
	public void crearVehiculo() throws Exception {
		launch(new String[0]);
	}

	@Test
	public void listarClientes() throws Exception {
		ClienteDao clienteManager = ClienteDao.getClienteManager();
		System.out.println(clienteManager.getClientes());
		clienteManager.close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VehiculoDao clienteManager = VehiculoDao.getVehiculoManager();
		Vehiculo vehiculo = Vehiculo.builder()
				.image(new Image(getClass()
						.getResource("/co/edu/uniquindio/programacionIII/alquilafacil/resources/defaultimg.png")
						.toExternalForm()))
				.kilometraje(100).marca("Chevrolet").nombre("Onix").numSillas(5).placa("JFX020").modelo(2023)
				.precioAlquilerDia(500000d).transmision(Transmision.MANUAL).build();
		System.out.println(vehiculo);
		clienteManager.actualizarVehiculo(vehiculo);
		clienteManager.close();
	}

}
