package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SeleccionarClienteController implements Initializable {

    @FXML
    private Button btnSiguiente;

    @FXML
    private TableColumn<Cliente, String> colCedula;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private Label lblCedula;

    @FXML
    private Label lblCliente;

    @FXML
    private Label lblClienteSelect;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TextField txtCedula;

    @FXML
    void siguienteEvent(ActionEvent event) {
    	siguienteAction();
    }
    
    
    private void siguienteAction() {
    	MainViewController.getInstance().cambiarVista(Vista.RENT_SEC);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(bundle->{
			lblTitle.setText(bundle.getString("SeleccionarCliente.lblTitle"));
			lblCedula.setText(bundle.getString("SeleccionarCliente.lblCedula"));
			lblCliente.setText(bundle.getString("SeleccionarCliente.lblCliente"));
			colNombre.setText(bundle.getString("SeleccionarCliente.colNombre"));
			colCedula.setText(bundle.getString("SeleccionarCliente.colCedula"));
			btnSiguiente.setText(bundle.getString("SeleccionarCliente.btnSiguiente"));
			tblClientes.setPlaceholder(new Label(bundle.getString("TablaSinContenido")) );
		});
		
	}

}