package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Se lanza cuando no se puede encontrar un elemento y se necesita
 * 
 */
public class ElementoNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ElementoNoEncontradoException}, se lanza
	 * cuando no se puede encontrar un elemento y se necesita
	 * 
	 * @param msg
	 */
	public ElementoNoEncontradoException(String msg) {
		super(msg);
	}
}
