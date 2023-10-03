package co.edu.uniquindio.programacionIII.alquilafacil.viewcontrollers;

import java.util.function.Consumer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class ViewMenuController {
	private static ViewMenuController instance;

	private ViewMenuController() {
	}

	public static ViewMenuController getInstance() {
		if (instance == null)
			instance = new ViewMenuController();
		return instance;
	}

	private boolean isMenuExtended = false;
	private boolean isLanguageMenuExtended = false;
	private Timeline timelineMenu;
	private Timeline timelineLanguage;
	private Consumer<Boolean> consumerMenu;
	private Consumer<Boolean> consumerLanguage;

	public void crearAnimacionExtension(DoubleProperty widthProperty, DoubleProperty opacityProperty,
			DoubleProperty rotacionSVG, Consumer<Boolean> consumerMenu) {
		timelineMenu = new Timeline();
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d),
				new KeyValue(opacityProperty, 0d), new KeyValue(rotacionSVG, 0d)));
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(opacityProperty, 1d),
				new KeyValue(widthProperty, 212d), new KeyValue(rotacionSVG, 90d)));
		this.consumerMenu = consumerMenu;
	}

	public void crearAnimacionExtensionLanguage(DoubleProperty widthProperty, DoubleProperty opacityProperty,
			DoubleProperty rotacionSVG, Consumer<Boolean> consumerLanguage) {
		timelineLanguage = new Timeline();
		timelineLanguage.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d),
				new KeyValue(opacityProperty, 0d), new KeyValue(rotacionSVG, 0d)));
		timelineLanguage.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(opacityProperty, 1d),
				new KeyValue(widthProperty, 160d), new KeyValue(rotacionSVG, 90d)));
		this.consumerLanguage = consumerLanguage;
	}

	public void ejecutarAnimacionMenu() {
		if (isLanguageMenuExtended)
			ejecutarAnimacionLanguage();
		consumerMenu.accept(isMenuExtended);
		if (isMenuExtended) {
			timelineMenu.stop();
			timelineMenu.setRate(-1);
			timelineMenu.jumpTo(Duration.millis(100));
			timelineMenu.play();
		} else {
			timelineMenu.playFromStart();
		}
		isMenuExtended = !isMenuExtended;
	}

	public void ejecutarAnimacionLanguage() {
		if (isMenuExtended)
			ejecutarAnimacionMenu();
		consumerLanguage.accept(isLanguageMenuExtended);
		if (isLanguageMenuExtended) {
			timelineLanguage.stop();
			timelineLanguage.setRate(-1);
			timelineLanguage.jumpTo(Duration.millis(100));
			timelineLanguage.play();
		} else {
			timelineLanguage.playFromStart();
		}
		isLanguageMenuExtended = !isLanguageMenuExtended;
	}
}
