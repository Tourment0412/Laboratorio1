package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SeleccionarVehiculoController {

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Vehiculo, Integer> colKilometraje;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo, Integer> colModelo;

    @FXML
    private TableColumn<Vehiculo, String> colNombre;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private TableColumn<Vehiculo, Double> colPrecioDia;

    @FXML
    private TableColumn<Vehiculo, Integer> colSillas;

    @FXML
    private TableColumn<Vehiculo, String> colTransmision;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<?> tblVehiculos;

    @FXML
    private TextField txtPlaca;

    @FXML
    void siguienteEvent(ActionEvent event) {
    	siguienteAction();
    }

    private void siguienteAction() {
    	MainViewController.getInstance().cambiarVista(Vista.RENT_FIN);
		
	}

	@FXML
    void volverEvent(ActionEvent event) {
		volverAc1tion();
    }

	private void volverAc1tion() {
		MainViewController.getInstance().cambiarVista(Vista.RENT_INI);
		
	}
    

}