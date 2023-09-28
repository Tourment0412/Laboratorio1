package co.edu.uniquindio.programacionIII.alquilafacil.test;

import java.time.LocalDate;

import org.junit.Test;

import co.edu.uniquindio.programacionIII.alquilafacil.dao.AlquilerDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.ClienteDao;
import co.edu.uniquindio.programacionIII.alquilafacil.dao.VehiculoDao;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestModel extends Application {
	@Test
	public void crearCliente() throws Exception {
		ClienteDao clienteManager = ClienteDao.createClienteManager();
		Cliente c = Cliente.builder().cedula("1059386396").ciudad("Armenia").direccion("Luigi´s mansion")
				.email("santaigo@gmail.com").nombre("Santaigo").numeroTel("396286039743").build();
		System.out.println(c);
		clienteManager.actualizarCliente(c);
		clienteManager.close();
	}

	@Test
	public void actualizarCliente() throws Exception {
		ClienteDao clienteManager = ClienteDao.createClienteManager();
		Cliente cliente = clienteManager.obtenerCliente("1059386396");
		cliente.setDireccion("W el sobreviviente con Raquel");
		clienteManager.actualizarCliente(cliente);
		clienteManager.close();
	}

	@Test
	public void crearAlquiler() {
		AlquilerDao alquilerManager = AlquilerDao.createAlquilerManager();
		ClienteDao clienteManager = ClienteDao.createClienteManager();
		VehiculoDao vehiculoManager = VehiculoDao.createVehiculoManager();

		Alquiler alquiler = Alquiler.builder().cliente(clienteManager.obtenerCliente("1059386396"))
				.vehiculo(vehiculoManager.obtenerVehiculo("JFX020")).costoTotal(10000d)
				.fechaAlquiler(LocalDate.now().plusDays(20)).fechaRegreso(LocalDate.now().plusMonths(2)).build();
		alquilerManager.actualizarAlquiler(alquiler);
		alquilerManager.close();
	}

	@Test
	public void crearVehiculo() throws Exception {
		launch(new String[0]);
	}

	@Test
	public void listarClientes() throws Exception {
		ClienteDao clienteManager = ClienteDao.createClienteManager();
		System.out.println(clienteManager.getClientes());
		clienteManager.close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VehiculoDao clienteManager = VehiculoDao.createVehiculoManager();
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
