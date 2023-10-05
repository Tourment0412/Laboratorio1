package co.edu.uniquindio.programacionIII.alquilafacil.exceptions;

/**
 * Esta excepcion se lanza cuando el vehículo no está disponible (está
 * alquilado)
 * 
 * @author Amador (Corem05)
 *
 */
public class VehiculoNoDisponibleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link VehiculoNoDisponibleException}, esta
	 * excepcion se lanza cuando el vehículo no está disponible (está alquilado)
	 * 
	 * @param msg
	 */
	public VehiculoNoDisponibleException(String msg) {
		super(msg);
	}
}
