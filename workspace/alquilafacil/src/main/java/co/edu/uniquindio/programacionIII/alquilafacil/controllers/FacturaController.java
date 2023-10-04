package co.edu.uniquindio.programacionIII.alquilafacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Propiedades;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FacturaController implements Initializable {

	private static FacturaController instance;

	public static FacturaController getInstance() {
		return instance;
	}

	public FacturaController() {
		instance = this;
	}

	@FXML
	private Label alquilerIdLbl;

	@FXML
	private Label billedToLbl;

	@FXML
	private Label clientAdressLbl;

	@FXML
	private Label clientAdressValueLbl;

	@FXML
	private Label clientCityLbl;

	@FXML
	private Label clientCityValueLbl;

	@FXML
	private Label clientEmailLbl;

	@FXML
	private Label clientEmailValueLbl;

	@FXML
	private Label clientIdLbl;

	@FXML
	private Label clientIdValueLbl;

	@FXML
	private Label clientNameLbl;

	@FXML
	private Label clientNameValueLbl;

	@FXML
	private Label clientNumberLbl;

	@FXML
	private Label clientNumberValueLbl;

	@FXML
	private Label referenceImgLbl;

	@FXML
	private Label rentDayLbl;

	@FXML
	private Label rentDaysLbl;

	@FXML
	private Label rentDaysValueLbl;

	@FXML
	private Label rentEndLbl;

	@FXML
	private Label rentEndValueLbl;

	@FXML
	private Label rentInformationLbl;

	@FXML
	private Label rentPriceDayLbl;

	@FXML
	private Label rentPriceDayValueLbl;

	@FXML
	private Label rentPriceLbl;

	@FXML
	private Label rentPriceValueLbl;

	@FXML
	private Label rentStartLbl;

	@FXML
	private Label rentStartValueLbl;

	@FXML
	private Label titleLbl;

	@FXML
	private Label vehicleBrandLbl;

	@FXML
	private Label vehicleBrandValueLbl;

	@FXML
	private Label vehicleCreationLbl;

	@FXML
	private Label vehicleCreationValueLbl;

	@FXML
	private ImageView vehicleImg;

	@FXML
	private Label vehicleInformationLbl;

	@FXML
	private Label vehicleMilageLbl;

	@FXML
	private Label vehicleMilageValueLbl;

	@FXML
	private Label vehicleModelLbl;

	@FXML
	private Label vehicleModelValueLbl;

	@FXML
	private Label vehicleNameLbl;

	@FXML
	private Label vehicleNameValueLbl;

	@FXML
	private Label vehiclePlateLbl;

	@FXML
	private Label vehiclePlateValueLbl;

	@FXML
	private Label vehicleSeatsLbl;

	@FXML
	private Label vehicleSeatsValueLbl;

	@FXML
	private Label vehicleTransmissionLbl;

	@FXML
	private Label vehicleTransmissionValueLbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Propiedades.getInstance().setProperties(bundle -> {
			titleLbl.setText(bundle.getString("FacturaController.titleLbl"));
			billedToLbl.setText(bundle.getString("FacturaController.billedToLbl"));
			clientNameLbl.setText(bundle.getString("FacturaController.clientNameLbl"));
			clientIdLbl.setText(bundle.getString("FacturaController.clientIdLbl"));
			clientNumberLbl.setText(bundle.getString("FacturaController.clientNumberLbl"));
			clientEmailLbl.setText(bundle.getString("FacturaController.clientEmailLbl"));
			clientCityLbl.setText(bundle.getString("FacturaController.clientCityLbl"));
			clientAdressLbl.setText(bundle.getString("FacturaController.clientAdressLbl"));
			vehicleInformationLbl.setText(bundle.getString("FacturaController.vehicleInformationLbl"));
			vehiclePlateLbl.setText(bundle.getString("FacturaController.vehiclePlateLbl"));
			vehicleNameLbl.setText(bundle.getString("FacturaController.vehicleNameLbl"));
			vehicleBrandLbl.setText(bundle.getString("FacturaController.vehicleBrandLbl"));
			vehicleModelLbl.setText(bundle.getString("FacturaController.vehicleModelLbl"));
			vehicleMilageLbl.setText(bundle.getString("FacturaController.vehicleMilageLbl"));
			vehicleSeatsLbl.setText(bundle.getString("FacturaController.vehicleSeatsLbl"));
			vehicleTransmissionLbl.setText(bundle.getString("FacturaController.vehicleTransmissionLbl"));
			vehicleCreationLbl.setText(bundle.getString("FacturaController.vehicleCreationLbl"));
			rentInformationLbl.setText(bundle.getString("FacturaController.rentInformationLbl"));
			rentStartLbl.setText(bundle.getString("FacturaController.rentStartLbl"));
			rentEndLbl.setText(bundle.getString("FacturaController.rentEndLbl"));
			rentDaysLbl.setText(bundle.getString("FacturaController.rentDaysLbl"));
			rentPriceDayLbl.setText(bundle.getString("FacturaController.rentPriceDayLbl"));
			rentPriceLbl.setText(bundle.getString("FacturaController.rentPriceLbl"));
			referenceImgLbl.setText(bundle.getString("FacturaController.referenceImgLbl"));
		});
	}

	public void setRentInfo(Alquiler alquiler) {
		// cliente
		clientNameValueLbl.setText(alquiler.getCliente().getNombre());
		clientIdValueLbl.setText(alquiler.getCliente().getCedula());
		clientNumberValueLbl.setText(alquiler.getCliente().getNumeroTel());
		clientEmailValueLbl.setText(alquiler.getCliente().getEmail());
		clientCityValueLbl.setText(alquiler.getCliente().getCiudad());
		clientAdressValueLbl.setText(alquiler.getCliente().getDireccion());

		// vehiculo
		vehiclePlateValueLbl.setText(alquiler.getVehiculo().getPlaca());
		vehicleNameValueLbl.setText(alquiler.getVehiculo().getNombre());
		vehicleBrandValueLbl.setText(alquiler.getVehiculo().getMarca());
		vehicleModelValueLbl.setText(alquiler.getVehiculo().getModelo().toString());
		vehicleMilageValueLbl.setText(alquiler.getVehiculo().getKilometraje().toString());
		vehicleSeatsValueLbl.setText(alquiler.getVehiculo().getNumSillas().toString());
		vehicleTransmissionValueLbl.setText(alquiler.getVehiculo().getTransmision().getText());
		vehicleCreationValueLbl.setText(alquiler.getVehiculo().getFechaCreacion().toString());
		vehicleImg.setImage(alquiler.getVehiculo().getImage());

		// alquiler
		rentDayLbl.setText(alquiler.getFechaRegistro().toString());
		rentStartValueLbl.setText(alquiler.getFechaAlquiler().toString());
		rentEndValueLbl.setText(alquiler.getFechaRegreso().toString());
		rentDaysValueLbl.setText(alquiler.contarDiasAlquiler() + "");
		rentPriceDayValueLbl.setText(alquiler.getVehiculo().getPrecioAlquilerDia().toString());
		rentPriceValueLbl.setText(alquiler.obtenerCostoTotal().toString());
		alquilerIdLbl.setText(alquiler.getId().toString());
	}

}
