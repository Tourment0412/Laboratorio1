package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHandler {

	private static LogHandler instance;

	private static final Logger LOGGER = Logger.getLogger(LogHandler.class.getName());

	public static LogHandler getInstance() {
		if (instance == null)
			instance = new LogHandler();
		return instance;
	}

	private LogHandler() {
		FileHandler fh;
		try {
			fh = new FileHandler("logs.log", true);
			fh.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fh);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public void logInfo(String msg) {
		LOGGER.info(msg);
	}

	public void logWarning(String msg) {
		LOGGER.warning(msg);		
	}

}
