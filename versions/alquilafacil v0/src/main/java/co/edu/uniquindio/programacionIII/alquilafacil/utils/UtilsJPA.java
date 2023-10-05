package co.edu.uniquindio.programacionIII.alquilafacil.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilsJPA {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplication");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void closeConnection() {
		if (emf.isOpen())
			emf.close();
	}
}
