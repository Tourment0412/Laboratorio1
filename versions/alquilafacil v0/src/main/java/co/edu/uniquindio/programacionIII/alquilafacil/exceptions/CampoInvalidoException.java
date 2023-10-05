package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Se lanza cuando hay un campo que es inválido para una operacion
 * 
 */
public class CampoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link CampoInvalidoException}, se lanza cuando
	 * hay un campo que es inválido para una operacion
	 * 
	 * @param msg
	 */
	public CampoInvalidoException(String msg) {
		super(msg);
	}
}
