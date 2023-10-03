package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.MainViewController;
import co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers.ViewMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuController implements Initializable, Consumer<ResourceBundle> {
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
	private VBox menuDer;

	@FXML
	private Label lblSpanish;

	@FXML
	private Label lblEnglish;

	@FXML
	private BorderPane languageLayer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().addListener(this);
		ViewMenuController.getInstance().crearAnimacionExtension(menuIzq.prefWidthProperty(),
				secondLayer.opacityProperty(), menuSVG.rotateProperty(), bool -> secondLayer.setDisable(bool));
		ViewMenuController.getInstance().crearAnimacionExtensionLanguage(menuDer.prefWidthProperty(),
				languageLayer.opacityProperty(), config.rotateProperty(), bool -> languageLayer.setDisable(bool));
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
		showLanguageAction();
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

	@FXML
	public void showLanguage2Event(MouseEvent event) {
		showLanguageAction();
	}

	@FXML
	public void changeLanguageSpanishEvent(MouseEvent event) {
		changeLanguageSpanishAction();
	}

	@FXML
	public void changeLanguageEnglishEvent(MouseEvent event) {
		changeLanguageEnglishEvent();
	}

	private void showMenuAction() {
		ViewMenuController.getInstance().ejecutarAnimacionMenu();
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

	private void changeLanguageSpanishAction() {
		Propiedades.getInstance().setLanguage("es");
	}

	private void changeLanguageEnglishEvent() {
		Propiedades.getInstance().setLanguage(Locale.US);
	}

	private void showLanguageAction() {
		ViewMenuController.getInstance().ejecutarAnimacionLanguage();
	}

	public void cambiarCentro(Node node) {
		panelCentral.setCenter(node);
	}

	@Override
	public void accept(ResourceBundle t) {
		mainLbl.setText(t.getString("MenuController.mainLbl"));
		addUserLbl.setText(t.getString("MenuController.addUserLbl"));
		addVehicleLbl.setText(t.getString("MenuController.addVehicleLbl"));
		rentVehicleLbl.setText(t.getString("MenuController.rentVehicleLbl"));
		showStatsLbl.setText(t.getString("MenuController.showStatsLbl"));
		lblEnglish.setText(t.getString("English"));
		lblSpanish.setText(t.getString("Spanish"));
	}

}
