package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Se lanza cuando una imagen no puede ser cargada
 * 
 * @author Amador (Corem05)
 *
 */
public class ArchivoNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ArchivoNoEncontradoException}, se lanza
	 * cuando una imagen no puede ser cargada
	 * 
	 * @param msg
	 */
	public ArchivoNoEncontradoException(String msg) {
		super(msg);
	}
}
