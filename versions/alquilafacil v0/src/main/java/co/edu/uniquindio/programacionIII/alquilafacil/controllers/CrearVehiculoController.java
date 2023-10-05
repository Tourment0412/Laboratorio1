package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ArchivoNoEncontradoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class CrearVehiculoController implements Initializable {

	@FXML
	private Button btnCrearVehiculo;

	@FXML
	private Button btnImagen;

	@FXML
	private ComboBox<String> cmbTransmision;

	@FXML
	private DatePicker dateFecha;

	@FXML
	private Label lblFecha;

	@FXML
	private Label lblImagen;

	@FXML
	private Label lblKilometraje;

	@FXML
	private Label lblMarca;

	@FXML
	private Label lblModelo;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblPlaca;

	@FXML
	private Label lblPrecioDia;

	@FXML
	private Label lblSillas;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblTransmision;

	@FXML
	private TextField spnKilometraje;

	@FXML
	private TextField spnModelo;

	@FXML
	private TextField spnSilas;

	@FXML
	private TextField txtPrecioDia;

	@FXML
	private TextField txtMarca;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPlaca;

	private Image imagen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbTransmision.setItems(FXCollections.observableArrayList(Transmision.textValues()));
		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("RegistroVehiculo.lblTitle"));
			lblPlaca.setText(bundle.getString("RegistroVehiculo.lblPlaca"));
			lblNombre.setText(bundle.getString("RegistroVehiculo.lblNombre"));
			lblMarca.setText(bundle.getString("RegistroVehiculo.lblMarca"));
			lblModelo.setText(bundle.getString("RegistroVehiculo.lblModelo"));
			lblImagen.setText(bundle.getString("RegistroVehiculo.lblImagen"));
			lblTransmision.setText(bundle.getString("RegistroVehiculo.lblTransmision"));
			lblKilometraje.setText(bundle.getString("RegistroVehiculo.lblKilometraje"));
			lblPrecioDia.setText(bundle.getString("RegistroVehiculo.lblPrecioDia"));
			lblSillas.setText(bundle.getString("RegistroVehiculo.lblSillas"));
			btnCrearVehiculo.setText(bundle.getString("RegistroVehiculo.btnCrearVehiculo"));
			btnImagen.setText(bundle.getString("RegistroVehiculo.btnImagen"));
		});

	}

	@FXML
	void crearVehiculoEvent(ActionEvent event) {
		crearVehiculoAction();
	}

	private void crearVehiculoAction() {
		try {
			ModelFactoryController.getInstance().agregarVehiculo(txtPlaca.getText(), txtNombre.getText(),
					txtMarca.getText(), spnModelo.getText(), imagen, cmbTransmision.getValue(),
					spnKilometraje.getText(), txtPrecioDia.getText(), spnSilas.getText());
			Utils.mostrarAlerta("Informacion", "El vehiculo ha sido agregado con éxito");
		} catch (ObjetoYaExisteException | PersiscenciaDesconocidaException | ImagenNoObtenidaException
				| CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
	}

	@FXML
	void seleccionarImagenEvent(ActionEvent event) {
		seleccionarImagenAction();
	}

	private void seleccionarImagenAction() {
		try {
			imagen = Utils.getInstance().cargarImagenArchivo();
			Utils.mostrarAlerta("Informacion", "La imagen ha sido cargada con exito");
		} catch (ArchivoNoEncontradoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
	}
}