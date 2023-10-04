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

public class FiltrarPorPrecioController {

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo, Integer> colModelo;

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
    void filtrarEvent(ActionEvent event) {

    }

    @FXML
    void regresarEvent(ActionEvent event) {
    	regresarAction();
    }
    
    private void regresarAction() {
    	MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT);
    }

}