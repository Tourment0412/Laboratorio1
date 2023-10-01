package co.edu.uniquindio.programacionIII.alquilafacil.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.services.UtilsJPA;

public class VehiculoDao {

	private final EntityManager em;

	private VehiculoDao() {
		em = UtilsJPA.getEntityManager();
	}

	private VehiculoDao(EntityManager em) {
		this.em = em;
	}

	public static VehiculoDao getVehiculoManager() {
		return new VehiculoDao();
	}

	public static VehiculoDao getVehiculoManager(EntityManager em) {
		return new VehiculoDao(em);
	}

	public void agregarVehiculo(Vehiculo vehiculo) {
		em.getTransaction().begin();
		em.persist(vehiculo);
		em.getTransaction().commit();
	}

	public Vehiculo obtenerVehiculo(String id) {
		return em.find(Vehiculo.class, id);
	}

	public List<Vehiculo> getVehiculos() {
		return em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
	}

	public void actualizarVehiculo(Vehiculo vehiculo) {
		em.getTransaction().begin();
		em.merge(vehiculo);
		em.getTransaction().commit();
	}

	public void eliminarVehiculo(long id) {
		Vehiculo vehiculo = em.find(Vehiculo.class, id);
		if (vehiculo != null) {
			em.getTransaction().begin();
			em.remove(vehiculo);
			em.getTransaction().commit();
		}
	}

	public void close() {
		if (em != null) {
			em.close();
		}
	}
}