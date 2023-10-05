package co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.alquilafacil.aplication.App;
import co.edu.uniquindio.programacionIII.alquilafacil.controllers.FacturaController;
import co.edu.uniquindio.programacionIII.alquilafacil.controllers.MenuController;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.Vista;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.Printer.MarginType;
import javafx.scene.Parent;

public class MainViewController {
	private static MainViewController instance;

	private MainViewController() {
	}

	public static MainViewController getInstance() {
		if (instance == null)
			instance = new MainViewController();
		return instance;
	}

	public void cambiarVista(Vista vista) {
		try {
			MenuController.getInstance().cambiarCentro(loadFXML(vista.getFxml()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Parent loadMainMenu() throws IOException {
		return loadFXML("menuPrincipal");
	}

	public void generarFactura(Alquiler alquiler) throws IOException {
		Parent vistaFactura = loadFXML(Vista.FACTURA.getFxml());
		FacturaController.getInstance().loadRentInfo(alquiler);

		Platform.runLater(() -> {
			PrinterJob job = PrinterJob.createPrinterJob();
			Printer printer = Printer.getDefaultPrinter();
			PageLayout lay = printer.createPageLayout(Paper.A3, PageOrientation.PORTRAIT, MarginType.DEFAULT);
			job.setPrinter(printer);
			if (job != null) {
				boolean success = job.printPage(lay, vistaFactura);
				if (success) {
					job.endJob();
				}
			}
		});
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/programacionIII/alquilafacil/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
