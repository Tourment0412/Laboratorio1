package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Se lanza cuando hay una excepcion desconocida a causa de la persiscencia
 */
public class PersiscenciaDesconocidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link PersiscenciaDesconocidaException}, se
	 * lanza cuando hay una excepcion desconocida a causa de la persiscencia
	 * 
	 * @param message
	 * @param causa
	 */
	public PersiscenciaDesconocidaException(String message, Throwable causa) {
		super(crearMensaje(message, causa), causa);
	}

	private static String crearMensaje(String mensaje, Throwable causa) {
		StringBuilder builder = new StringBuilder(mensaje);
		Throwable causaAux = causa;
		while (causaAux != null) {
			builder.append(" - Caused by: [");
			builder.append(causaAux.toString());
			builder.append("]");
			causaAux = causaAux.getCause();
		}
		return builder.toString();
	}
}
