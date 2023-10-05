package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.PersiscenciaDesconocidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CrearClienteController implements Initializable {

	@FXML
	private Button btnRegistrar;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtCiudad;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtNumero;

	@FXML
	void registrarEvent(ActionEvent event) {
		try {
			ModelFactoryController.getInstance().agregarCliente(txtCedula.getText(), txtNombre.getText(),
					txtNumero.getText(), txtEmail.getText(), txtCiudad.getText(), txtDireccion.getText());
			Utils.mostrarAlerta("Informacion", "El cliente fue agregado satisfactoriamente");
		} catch (ObjetoYaExisteException | PersiscenciaDesconocidaException | CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle -> {
			lblTitle.setText(bundle.getString("RegistroCliente.lblTitle"));
			btnRegistrar.setText(bundle.getString("RegistroCliente.btnRegistrar"));
			txtCedula.setPromptText(bundle.getString("RegistroCliente.txtCedula"));
			txtNombre.setPromptText(bundle.getString("RegistroCliente.txtNombre"));
			txtNumero.setPromptText(bundle.getString("RegistroCliente.txtNumero"));
			txtEmail.setPromptText(bundle.getString("RegistroCliente.txtEmail"));
			txtCiudad.setPromptText(bundle.getString("RegistroCliente.txtCiudad"));
			txtDireccion.setPromptText(bundle.getString("RegistroCliente.txtDireccion"));
		});

	}

}
