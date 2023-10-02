package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.services.MenuService;
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

	}

	@FXML
	void showStatsEvent(MouseEvent event) {

	}

	@FXML
	void initialize() {
		MenuService.getInstance().crearAnimacionExtension(menuIzq.prefWidthProperty(), secondLayer.opacityProperty(),
				menuSVG.rotateProperty());
	}

	private void showMenuAction() {
		MenuService.getInstance().ejecutarAnimacionMenu((bool) -> {
			secondLayer.setDisable(bool);
		});
	}

	private void addVehicleAction() {
		ViewServices.getInstance().cambiarVista(Vista.ADD_VEHICLE);
	}

	private void addUserAction() {
		ViewServices.getInstance().cambiarVista(Vista.ADD_CLIENT);
	}

	public void cambiarCentro(Node node) {
		panelCentral.setCenter(node);
	}

}
