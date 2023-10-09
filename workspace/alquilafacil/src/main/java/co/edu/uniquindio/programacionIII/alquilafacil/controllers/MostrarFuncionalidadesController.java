package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class MostrarFuncionalidadesController implements Initializable {

	@FXML
	private Button btnListarAlquilados;

	@FXML
	private Button btnListarEcoCos;

	@FXML
	private Button btnMarcaMasAlq;

	@FXML
	private Button btnTotalGanado;
	
	private String alertTitle;
	private String alertText;

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
		String cad= DataService.getInstance().getMarcaMasAlquilada();
		Utils.mostrarAlerta(alertTitle, alertText+" "+cad, AlertType.INFORMATION);
	}

	@FXML
	void totalGanadoEvent(ActionEvent event) {
		totalGanadoAction();
	}

	private void totalGanadoAction() {
		MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT3);

	}

	private void listarEcoCosAction() {
		MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT1);
	}

	private void listarAlquilados() {
		MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT2);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle -> {
			btnListarEcoCos.setText(bundle.getString("MostarFuncionalidades.btnListarEcoCos"));
			btnListarAlquilados.setText(bundle.getString("MostarFuncionalidades.btnListarAlquilados"));
			btnTotalGanado.setText(bundle.getString("MostarFuncionalidades.btnTotalGanado"));
			btnMarcaMasAlq.setText(bundle.getString("MostarFuncionalidades.btnMarcaMasAlq"));
			alertTitle=bundle.getString("MarcaMasAquilada");
			alertText=bundle.getString("MarcaMasAquiladaText");
		});

	}

}