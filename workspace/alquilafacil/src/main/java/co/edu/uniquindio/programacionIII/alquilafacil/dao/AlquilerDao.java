package co.edu.uniquindio.programacionIII.alquilafacil.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import co.edu.uniquindio.programacionIII.alquilafacil.services.UtilsJPA;

public class AlquilerDao {

	private final EntityManager em;

	private AlquilerDao() {
		em = UtilsJPA.getEntityManager();
	}

	private AlquilerDao(EntityManager em) {
		this.em = em;
	}

	public static AlquilerDao getManager() {
		return new AlquilerDao();
	}

	public static AlquilerDao getAlquilerManager(EntityManager em) {
		return new AlquilerDao(em);
	}

	public void agregarAlquiler(Alquiler alquiler) {
		em.getTransaction().begin();
		em.persist(alquiler);
		em.getTransaction().commit();
	}

	public Alquiler obtenerAlquiler(long id) {
		return em.find(Alquiler.class, id);
	}

	public List<Alquiler> getAlquileres() {
		return em.createQuery("SELECT a FROM Alquiler a", Alquiler.class).getResultList();
	}

	public void actualizarAlquiler(Alquiler alquiler) {
		em.getTransaction().begin();
		em.merge(alquiler);
		em.getTransaction().commit();
	}

	public void eliminarAlquiler(long id) {
		Alquiler alquiler = em.find(Alquiler.class, id);
		if (alquiler != null) {
			em.getTransaction().begin();
			em.remove(alquiler);
			em.getTransaction().commit();
		}
	}

	public void close() {
		if (em != null) {
			em.close();
		}
	}
}
