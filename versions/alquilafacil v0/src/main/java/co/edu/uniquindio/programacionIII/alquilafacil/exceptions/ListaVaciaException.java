package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * se lanza cuando le resultado de una lista esta vacio y se supone que no lo
 * puede estar
 * 
 */
public class ListaVaciaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ListaVaciaException}, se lanza cuando le
	 * resultado de una lista esta vacio y se supone que no lo puede estar
	 * 
	 * @param msg
	 */
	public ListaVaciaException(String msg) {
		super(msg);
	}
}
