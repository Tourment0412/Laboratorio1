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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ConcretarAlquilerController implements Initializable {

    @FXML
    private Button btnConcretar;

    @FXML
    private Button btnVolver;

    @FXML
    private ImageView imgVehiculo;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblCedulaCliente;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblFechaIni;

    @FXML
    private Label lblMarca;

    @FXML
    private Label lblMarcaVehiculo;

    @FXML
    private Label lblModelo;

    @FXML
    private Label lblModeloVehuculo;

    @FXML
    private Label lblNombreC;

    @FXML
    private Label lblNombreCliente;

    @FXML
    private Label lblNombreV;

    @FXML
    private Label lblNombreVehiculo;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblPlacaVehiculo;

    @FXML
    private Label lblTitle;

    @FXML
    void concretarEvent(ActionEvent event) {

    }

    @FXML
    void volverEvent(ActionEvent event) {
    	volverAction();
    }
    
    private void volverAction() {
    	MainViewController.getInstance().cambiarVista(Vista.RENT_SEC);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle->{
			lblTitle.setText(bundle.getString("concretarAlquiler.lblTitle"));
			lblCedula.setText(bundle.getString("concretarAlquiler.lblCedula"));
			lblNombreC.setText(bundle.getString("concretarAlquiler.lblNombreC"));
			lblMarca.setText(bundle.getString("concretarAlquiler.lblMarca"));
			lblNombreV.setText(bundle.getString("concretarAlquiler.lblNombreV"));
			lblModelo.setText(bundle.getString("concretarAlquiler.lblModelo"));
			lblPlaca.setText(bundle.getString("concretarAlquiler.lblPlaca"));
			lblFechaIni.setText(bundle.getString("concretarAlquiler.lblFechaIni"));
			lblFechaFin.setText(bundle.getString("concretarAlquiler.lblFechaFin"));
			btnConcretar.setText(bundle.getString("concretarAlquiler.btnConcretar"));
			btnVolver.setText(bundle.getString("concretarAlquiler.btnVolver"));
		});
		
	}

}