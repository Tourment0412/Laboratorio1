package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MostrarFuncionalidadesController implements Initializable{

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle->{
			btnListarEcoCos.setText(bundle.getString("MostarFuncionalidades.btnListarEcoCos"));
			btnListarAlquilados.setText(bundle.getString("MostarFuncionalidades.btnListarAlquilados"));
			btnTotalGanado.setText(bundle.getString("MostarFuncionalidades.btnTotalGanado"));
			btnMarcaMasAlq.setText(bundle.getString("MostarFuncionalidades.btnMarcaMasAlq"));
		});
		
	}

}