package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.DatePicker;

public class ConcretarAlquilerController implements Initializable {

	@FXML
	private Button btnConcretar;

	@FXML
	private Button btnVolver;

	@FXML
	private ImageView imgVehiculo;

	@FXML
	private Label lblCedula;

	@FXML
	private Label lblCedulaCliente;

	@FXML
	private Label lblFechaFin;

	@FXML
	private Label lblFechaIni;

	@FXML
	private Label lblMarca;

	@FXML
	private Label lblMarcaVehiculo;

	@FXML
	private Label lblModelo;

	@FXML
	private Label lblNombreC;

	@FXML
	private Label lblNombreCliente;

	@FXML
	private Label lblNombreV;

	@FXML
	private Label lblNombreVehiculo;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblPlacaVehiculo;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblModeloVehiculo;

	@FXML
	private DatePicker fechaInicial;

	@FXML
	private DatePicker fechaFinal;

	private String clienteCedu;

	private String vehiculoPlaca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Cliente cliente = SeleccionarClienteController.getInstance().getCliente();
		Vehiculo vehiculo = SeleccionarVehiculoController.getInstance().getVehiculo();

		clienteCedu = cliente.getCedula();
		vehiculoPlaca = vehiculo.getPlaca();

		lblCedulaCliente.setText(cliente.getCedula());
		lblNombreCliente.setText(cliente.getNombre());
		lblMarcaVehiculo.setText(vehiculo.getMarca());
		lblNombreVehiculo.setText(vehiculo.getNombre());
		lblModeloVehiculo.setText(new DecimalFormat("#").format(vehiculo.getModelo()));
		lblPlacaVehiculo.setText(vehiculo.getPlaca());
		imgVehiculo.setImage(vehiculo.getImage());

		lblMarcaVehiculo.setText(cliente.getNombre());
		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("concretarAlquiler.lblTitle"));
			lblCedula.setText(bundle.getString("concretarAlquiler.lblCedula"));
			lblNombreC.setText(bundle.getString("concretarAlquiler.lblNombreC"));
			lblMarca.setText(bundle.getString("concretarAlquiler.lblMarca"));
			lblNombreV.setText(bundle.getString("concretarAlquiler.lblNombreV"));
			lblModelo.setText(bundle.getString("concretarAlquiler.lblModelo"));
			lblPlaca.setText(bundle.getString("concretarAlquiler.lblPlaca"));
			lblFechaIni.setText(bundle.getString("concretarAlquiler.lblFechaIni"));
			lblFechaFin.setText(bundle.getString("concretarAlquiler.lblFechaFin"));
			btnConcretar.setText(bundle.getString("concretarAlquiler.btnConcretar"));
			btnVolver.setText(bundle.getString("concretarAlquiler.btnVolver"));
		});

	}

	@FXML
	void concretarEvent(ActionEvent event) {
		contretarAction();
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void volverAction() {
		MainViewController.getInstance().cambiarVista(Vista.RENT_SEC);
	}

	private void contretarAction() {
		try {
			Alquiler alquiler = ModelFactoryController.getInstance().agregarAlquiler(clienteCedu, vehiculoPlaca,
					fechaInicial.getValue(), fechaFinal.getValue());
			Utils.mostrarAlerta("Informacion", "El alquiler ha sido creado con exito");
			MainViewController.getInstance().generarFactura(alquiler);
		} catch (VehiculoNoDisponibleException | ObjetoYaExisteException | PersiscenciaDesconocidaException
				| ObjetoNoEncontradoException | CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		} catch (IOException e) {
			e.printStackTrace();
			Utils.mostrarAlerta("Advertencia", "No se pudo generar la factura como pdf", AlertType.WARNING);
		}
	}

}