package co.edu.uniquindio.programacionIII.alquilafacil.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.utils.UtilsJPA;

public class ClienteDao {

	private final EntityManager em;

	private ClienteDao() {
		em = UtilsJPA.getEntityManager();
	}

	private ClienteDao(EntityManager em) {
		this.em = em;
	}

	public static ClienteDao getManager() {
		return new ClienteDao();
	}

	public static ClienteDao getManager(EntityManager em) {
		return new ClienteDao(em);
	}

	public void agregarCliente(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public Cliente obtenerCliente(String id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> getClientes() {
		return em.createQuery("SELECT * FROM clientes", Cliente.class).getResultList();
	}

	public void actualizarCliente(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	public void eliminarCliente(String id) {
		Cliente cliente = em.find(Cliente.class, id);
		if (cliente != null) {
			em.getTransaction().begin();
			em.remove(cliente);
			em.getTransaction().commit();
		}
	}

	public void close() {
		if (em != null) {
			em.close();
		}
	}
}
