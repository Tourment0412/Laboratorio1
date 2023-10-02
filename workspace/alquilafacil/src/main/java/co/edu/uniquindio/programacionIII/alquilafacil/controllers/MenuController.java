package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.services.ViewServices;
import co.edu.uniquindio.programacionIII.alquilafacil.services.Vista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuController {
	private static MenuController instance;

	public static MenuController getInstance() {
		if (instance == null)
			instance = new MenuController();
		return instance;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label addUserLbl;

	@FXML
	private Label addVehicleLbl;

	@FXML
	private SVGPath config;

	@FXML
	private Label mainLbl;

	@FXML
	private VBox menuIzq;

	@FXML
	private Label rentVehicleLbl;

	@FXML
	private BorderPane secondLayer;

	@FXML
	private Label showStatsLbl;

	@FXML
	BorderPane centralPane;

	@FXML
	void addUserEvent(MouseEvent event) {
		ViewServices.getInstance().cambiarVista(Vista.ADD_CLIENT);
	}

	@FXML
	void addVehicleEvent(MouseEvent event) {
		ViewServices.getInstance().cambiarVista(Vista.ADD_VEHICLE);
	}

	@FXML
	void configEvent(ActionEvent event) {
	}

	@FXML
	void mainEvent(MouseEvent event) {

	}

	@FXML
	void rentVehicleEvent(MouseEvent event) {

	}

	@FXML
	void showStatsEvent(MouseEvent event) {

	}

	@FXML
	void initialize() {
	}

	public void cambiarCentro(Node node) {
		centralPane.setCenter(node);
	}

}
