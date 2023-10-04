package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.AlertUtils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SeleccionarVehiculoController implements Initializable {

	private static SeleccionarVehiculoController instance;

	public static SeleccionarVehiculoController getInstance() {
		return instance;
	}

	public SeleccionarVehiculoController() {
		instance = this;
	}

	@FXML
	private Button btnSiguiente;

	@FXML
	private Button btnVolver;

	@FXML
	private TableColumn<Vehiculo, String> colKilometraje;

	@FXML
	private TableColumn<Vehiculo, String> colMarca;

	@FXML
	private TableColumn<Vehiculo, String> colModelo;

	@FXML
	private TableColumn<Vehiculo, String> colNombre;

	@FXML
	private TableColumn<Vehiculo, String> colPlaca;

	@FXML
	private TableColumn<Vehiculo, String> colPrecioDia;

	@FXML
	private TableColumn<Vehiculo, String> colSillas;

	@FXML
	private TableColumn<Vehiculo, String> colTransmision;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblTitle;

	@FXML
	private TableView<Vehiculo> tblVehiculos;

	@FXML
	private TextField txtPlaca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colKilometraje
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getKilometraje().toString()));
		colMarca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getMarca()));
		colModelo.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getModelo().toString()));
		colNombre.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));
		colPlaca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPlaca()));
		colPrecioDia.setCellValueFactory(
				cell -> new ReadOnlyStringWrapper(cell.getValue().getPrecioAlquilerDia().toString()));
		colSillas.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNumSillas().toString()));
		colTransmision
				.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getTransmision().getText()));

		try {
			tblVehiculos.setItems(
					FXCollections.observableArrayList(ModelFactoryController.getInstance().listarVehiculos()));
		} catch (PersiscenciaDesconocidaException e) {
			AlertUtils.mostrarAlerta("Advertencia", e.getMessage());
		}
		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("SeleccionarVehiculo.lblTitle"));
			lblPlaca.setText(bundle.getString("SeleccionarVehiculo.lblPlaca"));
			colPlaca.setText(bundle.getString("SeleccionarVehiculo.colPlaca"));
			colNombre.setText(bundle.getString("SeleccionarVehiculo.colNombre"));
			colMarca.setText(bundle.getString("SeleccionarVehiculo.colMarca"));
			colModelo.setText(bundle.getString("SeleccionarVehiculo.colModelo"));
			colKilometraje.setText(bundle.getString("SeleccionarVehiculo.colKilometraje"));
			colPrecioDia.setText(bundle.getString("SeleccionarVehiculo.colPrecioDia"));
			colTransmision.setText(bundle.getString("SeleccionarVehiculo.colTransmision"));
			colSillas.setText(bundle.getString("SeleccionarVehiculo.colSillas"));
			btnSiguiente.setText(bundle.getString("SeleccionarVehiculo.btnSiguiente"));
			btnVolver.setText(bundle.getString("SeleccionarVehiculo.btnVolver"));
			tblVehiculos.setPlaceholder(new Label(bundle.getString("TablaSinContenido")));
		});
	}

	@FXML
	void siguienteEvent(ActionEvent event) {
		siguienteAction();
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void siguienteAction() {
		if (tblVehiculos.getSelectionModel().getSelectedItem() == null) {
			AlertUtils.mostrarAlerta("Advertencia", "Recuerda seleccionar un vehiculo", AlertType.WARNING);
			return;
		}
		MainViewController.getInstance().cambiarVista(Vista.RENT_FIN);
	}

	private void volverAction() {
		MainViewController.getInstance().cambiarVista(Vista.RENT_INI);
	}

	public Vehiculo getVehiculo() {
		return tblVehiculos.getSelectionModel().getSelectedItem();
	}

}