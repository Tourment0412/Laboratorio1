package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SeleccionarClienteController {

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

    }

}