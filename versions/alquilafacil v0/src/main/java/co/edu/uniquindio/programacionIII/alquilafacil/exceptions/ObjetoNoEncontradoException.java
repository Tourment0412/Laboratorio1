package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Es lanzada cuando un objeto que se intenta buscar no se encuentra
 *
 */
public class ObjetoNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ObjetoNoEncontradoException}, es lanzada
	 * cuando un objeto que se intenta buscar no se encuentra
	 * 
	 * @param msg
	 */
	public ObjetoNoEncontradoException(String msg) {
		super(msg);
	}
}
