package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
public class Alquiler implements Serializable, Comparator<Alquiler> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Getter
	@Setter
	private String id;

	@Getter
	private Cliente cliente;

	@Getter
	private Vehiculo vehiculo;

	@Getter
	private LocalDate fechaRegistro;

	@NonNull
	@Getter
	private LocalDate fechaAlquiler;

	@NonNull
	@Getter
	private LocalDate fechaRegreso;

	@Builder
	private Alquiler(@NonNull Cliente cliente, @NonNull Vehiculo vehiculo, @NonNull LocalDate fechaAlquiler,
			@NonNull LocalDate fechaRegreso) {
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaRegreso = fechaRegreso;
		this.fechaRegistro = LocalDate.now();
	}

	public boolean tieneVehiculoAlquiladoAhora() {
		LocalDate now = LocalDate.now();
		if ((now.isAfter(fechaAlquiler) && now.isBefore(fechaRegistro)) || now.isEqual(fechaAlquiler)
				|| now.isEqual(fechaRegreso))
			return true;
		return false;
	}

	public boolean tieneVehiculoAlquiladoAhora(Vehiculo vehiculo) {
		return tieneVehiculoAlquiladoAhora() && getVehiculo().equals(vehiculo);
	}

	public boolean tieneVehiculoAlquiladoAhora(LocalDate fecha) {

		if ((fecha.isAfter(fechaAlquiler) && fecha.isBefore(fechaRegistro)) || fecha.isEqual(fechaAlquiler)
				|| fecha.isEqual(fechaRegreso))
			return true;
		return false;
	}

	public boolean tieneVehiculoAlquiladoAhora(Vehiculo vehiculo, LocalDate fecha) {
		return tieneVehiculoAlquiladoAhora(fecha) && getVehiculo().equals(vehiculo);
	}

	public long contarDiasAlquiler() {
		return fechaAlquiler.until(fechaRegreso, ChronoUnit.DAYS);
	}

	public Double obtenerCostoTotal() {
		return contarDiasAlquiler() * vehiculo.getPrecioAlquilerDia();
	}

	public boolean estaEnRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
		return !fechaFinal.isBefore(this.fechaAlquiler) && !this.fechaRegreso.isBefore(fechaInicial);
	}
	
	public boolean estaEnFecha(LocalDate fecha) {
		return (fechaAlquiler.isBefore(fecha)||fechaAlquiler.equals(fecha))&&(fechaRegreso.isAfter(fecha)||fechaRegreso.isEqual(fecha));
	}

	@Override
	public int compare(Alquiler o1, Alquiler o2) {
		return o1.getId().compareTo(o2.getId());
	}
}
