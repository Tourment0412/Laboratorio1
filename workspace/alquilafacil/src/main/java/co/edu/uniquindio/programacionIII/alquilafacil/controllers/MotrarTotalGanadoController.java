package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.services.DataService;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class MotrarTotalGanadoController implements Initializable {

	@FXML
	private Button btnMostrarGanado;

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
	private Button btnVolver;

	private String msgTotalGanado;

	private String msgTotalPesos;

	@FXML
	void mostrarGanadoEvent(ActionEvent event) {
		mostrarGanadoAction(dtpFechaIni.getValue(), dtpFechaFin.getValue());

	}

	private void mostrarGanadoAction(LocalDate value, LocalDate value2) {
		Double ganadoAlquileres = DataService.getInstance().getTotalGanadoAlquileres();
		Utils.mostrarAlerta(msgTotalGanado, ganadoAlquileres + " " + msgTotalPesos, AlertType.INFORMATION);

	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void volverAction() {
		MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("MostrarTotalGanado.lblTitle"));
			lblFechaIni.setText(bundle.getString("MostrarTotalGanado.lblFechaIni"));
			lblFechaFin.setText(bundle.getString("MostrarTotalGanado.lblFechaFinal"));
			btnMostrarGanado.setText(bundle.getString("MostrarTotalGanado.btnMostrarTotal"));
			btnVolver.setText(bundle.getString("MostrarTotalGanado.btnVolver"));
			msgTotalGanado = bundle.getString("MsgTotalGanado");
			msgTotalPesos = bundle.getString("MsgTotalPesos");
		});

	}

}
