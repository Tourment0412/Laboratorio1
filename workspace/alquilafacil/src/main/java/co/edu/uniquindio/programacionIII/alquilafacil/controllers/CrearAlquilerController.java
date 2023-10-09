package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.VehiculoNoDisponibleException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.CreacionAlquilerHandler;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrearAlquilerController implements Initializable {

	@FXML
	private TabPane root;

	@FXML
	private Button btnFinalizar;

	@FXML
	private Button btnSiguienteFechas;

	@FXML
	private Button btnSiguienteVehiculo;

	@FXML
	private Button btnVolverCliente;

	@FXML
	private Button btnVolverVehiculo;

	@FXML
	private TableColumn<Cliente, String> colCedula;

	@FXML
	private TableColumn<Vehiculo, String> colKilometraje;

	@FXML
	private TableColumn<Vehiculo, String> colMarca;

	@FXML
	private TableColumn<Vehiculo, String> colModelo;

	@FXML
	private TableColumn<Vehiculo, String> colNombre;

	@FXML
	private TableColumn<Cliente, String> colNombreCliente;

	@FXML
	private TableColumn<Vehiculo, String> colPlaca;

	@FXML
	private TableColumn<Vehiculo, String> colPrecioDia;

	@FXML
	private TableColumn<Vehiculo, String> colSillas;

	@FXML
	private TableColumn<Vehiculo, String> colTransmision;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private DatePicker initialDatePicker;

	@FXML
	private Label lblCedula;

	@FXML
	private Label lblEligeCliente;

	@FXML
	private Label lblEligeFechas;

	@FXML
	private Label lblEligeVehiculo;

	@FXML
	private Label lblFechaFin;

	@FXML
	private Label lblFechaIni;

	@FXML
	private Label lblPlaca;

	@FXML
	private Tab tabCliente;

	@FXML
	private Tab tabFechas;

	@FXML
	private Tab tabVehiculo;

	@FXML
	private TableView<Cliente> tblClientes;

	@FXML
	private TableView<Vehiculo> tblVehiculos;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtPlaca;

	@FXML
	private Button btnFiltrarVehiculo;

	@FXML
	private Button btnFiltrarCliente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		colCedula.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getCedula()));
		colNombreCliente.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));

		DecimalFormat format = new DecimalFormat("#");
		colKilometraje.setCellValueFactory(
				cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getKilometraje())));
		colMarca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getMarca()));
		colModelo.setCellValueFactory(cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getModelo())));
		colNombre.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));
		colPlaca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPlaca()));
		colPrecioDia.setCellValueFactory(
				cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getPrecioAlquilerDia())));
		colSillas.setCellValueFactory(cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getNumSillas())));
		colTransmision
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getTransmision().getText()));
		Propiedades.getInstance().addListener(bundle -> {
			btnSiguienteFechas.setText(bundle.getString("CrearAlquiler.btnSiguiente"));
			btnSiguienteVehiculo.setText(bundle.getString("CrearAlquiler.btnSiguiente"));

			btnVolverVehiculo.setText(bundle.getString("CrearAlquiler.btnVolver"));
			btnVolverCliente.setText(bundle.getString("CrearAlquiler.btnVolver"));
			btnFinalizar.setText(bundle.getString("CrearAlquiler.btnFinalizar"));
			btnFiltrarCliente.setText(bundle.getString("CrearAlquiler.filtrar"));
			btnFiltrarVehiculo.setText(bundle.getString("CrearAlquiler.filtrar"));

			lblEligeFechas.setText(bundle.getString("CrearAlquiler.lblEligeFechas"));
			lblEligeVehiculo.setText(bundle.getString("CrearAlquiler.lblEligeVehiculo"));
			lblEligeCliente.setText(bundle.getString("CrearAlquiler.lblEligeCliente"));

			lblFechaIni.setText(bundle.getString("CrearAlquiler.lblFechaIni"));
			lblFechaFin.setText(bundle.getString("CrearAlquiler.lblFechaFin"));

			tblVehiculos.setPlaceholder(new Label(bundle.getString("TablaSinContenido")));
			tblClientes.setPlaceholder(new Label(bundle.getString("TablaSinContenido")));

			lblPlaca.setText(bundle.getString("CrearAlquiler.lblPlaca"));
			colPlaca.setText(bundle.getString("CrearAlquiler.colPlaca"));
			colNombre.setText(bundle.getString("CrearAlquiler.colNombre"));
			colMarca.setText(bundle.getString("CrearAlquiler.colMarca"));
			colModelo.setText(bundle.getString("CrearAlquiler.colModelo"));
			colKilometraje.setText(bundle.getString("CrearAlquiler.colKilometraje"));
			colPrecioDia.setText(bundle.getString("CrearAlquiler.colPrecioDia"));
			colTransmision.setText(bundle.getString("CrearAlquiler.colTransmision"));
			colSillas.setText(bundle.getString("CrearAlquiler.colSillas"));

			colNombreCliente.setText(bundle.getString("CrearAlquiler.colNombre"));
			lblCedula.setText(bundle.getString("CrearAlquiler.lblCedula"));
			colCedula.setText(bundle.getString("CrearAlquiler.colCedula"));
		});
	}

	@FXML
	void siguienteFechasEvent(ActionEvent event) {
		siguienteFechasAction();
	}

	@FXML
	void siguienteVehiculoEvent(ActionEvent event) {
		siguienteVehiculoAction();
	}

	@FXML
	void finalizarEvent(ActionEvent event) {
		finalizarAction();
	}

	@FXML
	void volverClienteEvent(ActionEvent event) {
		gotoVehiculo();
	}

	@FXML
	void volverVehiculoEvent(ActionEvent event) {
		goToFechas();
	}

	@FXML
	public void filtrarVehiculoEvent(ActionEvent event) {
		filtrarVehiculoAction();
	}

	@FXML
	public void filtrarClienteEvent(ActionEvent event) {
		filtrarClienteAction();
	}

	private void filtrarVehiculoAction() {
		try {
			actualizarTablaVehiculos(txtPlaca.getText());
		} catch (ListaVaciaException | CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
			vaciarTablaVehiculos();
		}
	}

	private void filtrarClienteAction() {
		try {
			actualizarTablaCliente(txtCedula.getText());
		} catch (ListaVaciaException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
			vaciarTablaClientes();
		}
	}

	private void vaciarTablaVehiculos() {
		tblVehiculos.setItems(FXCollections.observableArrayList());
		tblVehiculos.refresh();
	}

	private void vaciarTablaClientes() {
		tblClientes.setItems(FXCollections.observableArrayList());
		tblClientes.refresh();
	}

	private void finalizarAction() {
		try {
			CreacionAlquilerHandler.getInstance().selectCliente(tblClientes.getSelectionModel().getSelectedItem());
			ModelFactoryController.getInstance().agregarAlquiler();
		} catch (VehiculoNoDisponibleException | ObjetoYaExisteException | ObjetoNoEncontradoException
				| CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
		Platform.runLater(() -> Utils.mostrarAlerta("Información", "El alquiler ha sido creado con exito"));
		clearData();
		goToFechas();

	}

	private void clearData() {
		initialDatePicker.setValue(null);
		endDatePicker.setValue(null);
		tblClientes.getSelectionModel().clearSelection();
		tblVehiculos.getSelectionModel().clearSelection();
		CreacionAlquilerHandler.getInstance().clearData();
	}

	private void siguienteFechasAction() {
		try {
			CreacionAlquilerHandler.getInstance().selectDates(initialDatePicker.getValue(), endDatePicker.getValue());
			actualizarTablaVehiculos();
			gotoVehiculo();
			Platform.runLater(
					() -> Utils.mostrarAlerta("Información", "El rango se fechas ha sido seleccionado con exito"));
		} catch (CampoInvalidoException | ListaVaciaException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}

	}

	private void actualizarTablaVehiculos() throws ListaVaciaException, CampoInvalidoException {
		CreacionAlquilerHandler handler = CreacionAlquilerHandler.getInstance();

		tblVehiculos.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance()
				.listarVehiculosRangoFechas(handler.getFechaAlquiler(), handler.getFechaRegreso())));
		tblVehiculos.refresh();
	}

	private void actualizarTablaVehiculos(String filtro) throws ListaVaciaException, CampoInvalidoException {
		CreacionAlquilerHandler handler = CreacionAlquilerHandler.getInstance();

		tblVehiculos.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance()
				.listarVehiculosRangoFechas(handler.getFechaAlquiler(), handler.getFechaRegreso()).stream()
				.filter(vehiculo -> vehiculo.placaEmpiezaPor(filtro)).toList()));
		tblVehiculos.refresh();
	}

	private void actualizarTablaCliente(String filtro) throws ListaVaciaException {
		tblClientes.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().listarClientes()
				.stream().filter(cliente -> cliente.cedulaEmpiezaPor(filtro)).toList()));
		tblClientes.refresh();
	}

	private void actualizarTablaClientes() {
		tblClientes.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().listarClientes()));
		tblClientes.refresh();
	}

	private void siguienteVehiculoAction() {
		try {
			CreacionAlquilerHandler.getInstance().selectVehiculo(tblVehiculos.getSelectionModel().getSelectedItem());
			Utils.mostrarAlerta("Información", "El vehiculo ha sido seleccionado con exito");
			actualizarTablaClientes();
			gotoCliente();
		} catch (CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
	}

	private void goToFechas() {
		root.getSelectionModel().select(tabFechas);
	}

	private void gotoVehiculo() {
		root.getSelectionModel().select(tabVehiculo);
	}

	private void gotoCliente() {
		root.getSelectionModel().select(tabCliente);
	}

}
