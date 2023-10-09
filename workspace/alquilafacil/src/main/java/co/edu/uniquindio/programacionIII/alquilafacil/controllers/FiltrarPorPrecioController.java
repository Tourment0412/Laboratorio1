package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

public class FiltrarPorPrecioController implements Initializable {

	@FXML
	private TableColumn<Vehiculo, String> colMarca;

	@FXML
	private TableColumn<Vehiculo, String> colModelo;

	@FXML
	private TableColumn<Vehiculo, String> colNombre;

	@FXML
	private TableColumn<Vehiculo, String> colPlaca;

	@FXML
	private TableColumn<Vehiculo, String> colPrecio;

	@FXML
	private DatePicker dtpFechaFin;

	@FXML
	private DatePicker dtpFechaIni;

	@FXML
	private Label lblFechaFin;

	@FXML
	private Label lblFechaIni;

	@FXML
	private Label lblTitle;

	@FXML
	private TableView<Vehiculo> tblFiltrados;

	@FXML
	private TabPane root;

	@FXML
	private Tab inicioTab;

	@FXML
	private Button btnVolverInicio;

	@FXML
	private Tab tablaTab;

	@FXML
	private Button btnVolverTabla;

	@FXML
	private Button btnVerTablaInicio;

	@FXML
	void regresarEvent(ActionEvent event) {
		regresarAction();
	}

	private void regresarAction() {
		MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DecimalFormat format = new DecimalFormat("#");
		colMarca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getMarca()));
		colModelo.setCellValueFactory(cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getModelo())));
		colNombre.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));
		colPlaca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPlaca()));

		colPrecio.setCellValueFactory(
				cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getPrecioAlquilerDia())));

		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("FiltrarPorPrecio.lblTitle"));
			lblFechaIni.setText(bundle.getString("FiltrarPorPrecio.lblFechaIni"));
			lblFechaFin.setText(bundle.getString("FiltrarPorPrecio.lblFechaFin"));
			btnVerTablaInicio.setText(bundle.getString("CrearAlquiler.btnSiguiente"));
			colPrecio.setText(bundle.getString("FiltrarPorPrecio.colPrecio"));
			colPlaca.setText(bundle.getString("FiltrarPorPrecio.colPlaca"));
			colNombre.setText(bundle.getString("FiltrarPorPrecio.colNombre"));
			colMarca.setText(bundle.getString("FiltrarPorPrecio.colMarca"));
			colModelo.setText(bundle.getString("FiltrarPorPrecio.colModelo"));
			btnVolverInicio.setText(bundle.getString("FiltrarPorPrecio.btnRegresar"));
			btnVolverTabla.setText(bundle.getString("FiltrarPorPrecio.btnRegresar"));
			tblFiltrados.setPlaceholder(new Label(bundle.getString("TablaSinContenido")));
		});

	}

	@FXML
	public void verTablaEvent(ActionEvent event) {
		try {
			tblFiltrados.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance()
					.listarVehiculosRangoFechas(dtpFechaIni.getValue(), dtpFechaFin.getValue())));
			tblFiltrados.refresh();
			gotoTabla();
		} catch (ListaVaciaException | CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
	}

	@FXML
	public void volverTablaEvent(ActionEvent event) {
		goToInicio();
	}

	private void goToInicio() {
		root.getSelectionModel().select(inicioTab);
	}

	private void gotoTabla() {
		root.getSelectionModel().select(tablaTab);
	}

}