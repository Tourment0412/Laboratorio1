package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.ViewMenuController;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuController implements Initializable {
	private static MenuController instance;

	public static MenuController getInstance() {
		return instance;
	}

	public MenuController() {
		instance = this;
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
	private SVGPath menuSVG;

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
	private BorderPane panelCentral;

	@FXML
	private Label showStatsLbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener((bundle) -> {
			mainLbl.setText(bundle.getString("MenuController.mainLbl"));
			addUserLbl.setText(bundle.getString("MenuController.addUserLbl"));
			addVehicleLbl.setText(bundle.getString("MenuController.addVehicleLbl"));
			rentVehicleLbl.setText(bundle.getString("MenuController.rentVehicleLbl"));
			showStatsLbl.setText(bundle.getString("MenuController.showStatsLbl"));
		});

		ViewMenuController.getInstance().crearAnimacionExtension(menuIzq.prefWidthProperty(),
				secondLayer.opacityProperty(), menuSVG.rotateProperty());
	}

	@FXML
	public void showMenuEvent(ActionEvent event) {
		showMenuAction();
	}

	@FXML
	void addUserEvent(MouseEvent event) {
		addUserAction();
	}

	@FXML
	void showMenu2Event(MouseEvent event) {
		showMenuAction();
	}

	@FXML
	void addVehicleEvent(MouseEvent event) {
		addVehicleAction();
	}

	@FXML
	void configEvent(ActionEvent event) {
	}

	@FXML
	void mainEvent(MouseEvent event) {

	}

	@FXML
	void rentVehicleEvent(MouseEvent event) {
		rentVehicleAction();
	}

	@FXML
	void showStatsEvent(MouseEvent event) {

	}

	private void showMenuAction() {
		ViewMenuController.getInstance().ejecutarAnimacionMenu((bool) -> {
			secondLayer.setDisable(bool);
		});
	}

	private void addVehicleAction() {
		MainViewController.getInstance().cambiarVista(Vista.ADD_VEHICLE);
	}

	private void addUserAction() {
		MainViewController.getInstance().cambiarVista(Vista.ADD_CLIENT);
	}

	private void rentVehicleAction() {
		MainViewController.getInstance().cambiarVista(Vista.RENT_INI);
	}

	public void cambiarCentro(Node node) {
		panelCentral.setCenter(node);
	}

}
