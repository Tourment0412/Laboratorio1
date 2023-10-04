package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MostrarVehiRentDia {

    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo, Integer> colModelo;

    @FXML
    private TableColumn<Vehiculo, String> colNombre;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<Vehiculo> tblVehiculos;

    @FXML
    void filtrarEvent(ActionEvent event) {

    }
    
    @FXML
    void volverEvent(ActionEvent event) {
    	volverAction();
    }

    private void volverAction() {
    	MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT);
    }
}
