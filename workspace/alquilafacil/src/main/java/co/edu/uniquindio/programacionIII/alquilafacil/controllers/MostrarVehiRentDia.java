package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.CampoInvalidoException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ListaVaciaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Utils;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class MostrarVehiRentDia implements Initializable {

    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo, String> colModelo;

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
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	DecimalFormat format = new DecimalFormat("#");
		colMarca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getMarca()));
		colModelo.setCellValueFactory(cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getModelo())));
		colNombre.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));
		colPlaca.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPlaca()));
		
		Propiedades.getInstance().addListener(bundle->{
			lblTitle.setText(bundle.getString("MostrarVehiRentDia.lblTitle"));
			lblFecha.setText(bundle.getString("MostrarVehiRentDia.lblFecha"));
			btnFiltrar.setText(bundle.getString("MostrarVehiRentDia.btnFiltrar"));
			colPlaca.setText(bundle.getString("MostrarVehiRentDia.colPlaca"));
			colNombre.setText(bundle.getString("MostrarVehiRentDia.colNombre"));
			colMarca.setText(bundle.getString("MostrarVehiRentDia.colMarca"));
			colModelo.setText(bundle.getString("MostrarVehiRentDia.colModelo"));
			btnVolver.setText(bundle.getString("MostrarVehiRentDia.btnVolver"));
			tblVehiculos.setPlaceholder(new Label(bundle.getString("TablaSinContenido")) );
		});
		
	}

    @FXML
    void filtrarEvent(ActionEvent event) {
    	
    	try {
			tblVehiculos.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().
					listarVehiculosFecha(dtpFecha.getValue())));
					;
			tblVehiculos.refresh();
			
		} catch (ListaVaciaException | CampoInvalidoException e) {
			Utils.mostrarAlerta("Advertencia", e.getMessage(), AlertType.WARNING);
		}

    }
    
    @FXML
    void volverEvent(ActionEvent event) {
    	volverAction();
    }

    private void volverAction() {
    	MainViewController.getInstance().cambiarVista(Vista.FUNCTEXT);
    }

	
}
