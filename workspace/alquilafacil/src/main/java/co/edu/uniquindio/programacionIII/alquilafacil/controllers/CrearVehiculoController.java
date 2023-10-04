package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class CrearVehiculoController implements Initializable {

    @FXML
    private Button btnCrearVehiculo;

    @FXML
    private Button btnImagen;

    @FXML
    private ComboBox<?> cmbTransmision;

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
    private Spinner<Integer> spnKilometraje;

    @FXML
    private Spinner<Integer> spnModelo;

    @FXML
    private Spinner<Integer> spnSilas;

    @FXML
    private TextField tctPrecioDia;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPlaca;

    @FXML
    void crearVehiculoEvent(ActionEvent event) {

    }

    @FXML
    void seleccionarImagenEvent(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle->{
			
		});
		
	}

}