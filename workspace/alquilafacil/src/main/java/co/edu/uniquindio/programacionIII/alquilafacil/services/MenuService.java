package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.util.function.Consumer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class MenuService {
	private static MenuService instance;

	private MenuService() {
	}

	public static MenuService getInstance() {
		if (instance == null)
			instance = new MenuService();
		return instance;
	}

	private boolean isExtended = false;
	private Timeline timelineMenu;

	public void crearAnimacionExtension(DoubleProperty widthProperty, DoubleProperty doubleProperty,
			DoubleProperty rotacionSVG) {
		timelineMenu = new Timeline();
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d),
				new KeyValue(doubleProperty, 0d), new KeyValue(rotacionSVG, 0d)));
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(doubleProperty, 1d),
				new KeyValue(widthProperty, 212d), new KeyValue(rotacionSVG, 180d)));
	}

	public void ejecutarAnimacionMenu(Consumer<Boolean> consumer) {
		consumer.accept(isExtended);
		if (isExtended) {
			timelineMenu.stop();
			timelineMenu.setRate(-1);
			timelineMenu.jumpTo(Duration.millis(100));
			timelineMenu.play();
		} else {
			timelineMenu.playFromStart();
		}
		isExtended = !isExtended;
	}
}
