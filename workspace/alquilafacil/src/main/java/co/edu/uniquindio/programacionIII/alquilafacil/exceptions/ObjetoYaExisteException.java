package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

public class ObjetoYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ObjetoYaExisteException}, se lanza
	 * cuando un objeto ya fue registrado enteriormente
	 * 
	 * @param message
	 */
	public ObjetoYaExisteException(String message) {
		super(message);
	}
}
