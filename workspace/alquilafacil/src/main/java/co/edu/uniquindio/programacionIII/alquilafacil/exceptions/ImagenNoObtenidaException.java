package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * se lanza cuando la imagen del vehiculo no puede ser obtenida
 *
 */
public class ImagenNoObtenidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ImagenNoObtenidaException}, se lanza
	 * cuando la imagen del vehiculo no puede ser obtenida
	 * 
	 * @param msg
	 * @param causa
	 */
	public ImagenNoObtenidaException(String msg, Throwable causa) {
		super(getMsg(msg, causa));
	}

	private static String getMsg(String msg, Throwable causa) {
		StringBuilder sb = new StringBuilder(msg);
		while (causa != null) {
			sb.append(" - caused by: ");
			sb.append(causa.getMessage());
			causa = causa.getCause();
		}
		return sb.toString();
	}
}
