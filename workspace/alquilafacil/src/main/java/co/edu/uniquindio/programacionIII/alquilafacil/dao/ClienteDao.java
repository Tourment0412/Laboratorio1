package co.edu.uniquindio.programacionIII.alquilafacil.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;
import co.edu.uniquindio.programacionIII.alquilafacil.services.UtilsJPA;

public class ClienteDao {

	private final EntityManager em;

	private ClienteDao() {
		em = UtilsJPA.getEntityManager();
	}

	public static ClienteDao createClienteManager() {
		return new ClienteDao();
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

	public void eliminarCliente(long id) {
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
