package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MostrarFuncionalidadesController {

    @FXML
    private Button btnListarAlquilados;

    @FXML
    private Button btnListarEcoCos;

    @FXML
    private Button btnMarcaMasAlq;

    @FXML
    private Button btnTotalGanado;

    @FXML
    void listarAlquiladosEvent(ActionEvent event) {
    	listarAlquilados();
    }

    @FXML
    void listarEcoCosEvent(ActionEvent event) {
    	listarEcoCosAction();
    }

    @FXML
    void marcaMasAlqEvent(ActionEvent event) {

    }

    @FXML
    void totalGanadoEvent(ActionEvent event) {

    }
    
    private void listarEcoCosAction() {
    	MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT1);
    }
    
    private void listarAlquilados() {
    	MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT2);
    }

}