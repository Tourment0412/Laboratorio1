package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "alquileres")
@NoArgsConstructor
public class Alquiler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;

	@Getter
	@OneToOne
	private Cliente cliente;

	@Getter
	@OneToOne
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

	public long contarDiasAlquiler() {
		return fechaAlquiler.until(fechaRegreso, ChronoUnit.DAYS);
	}

	public Double obtenerCostoTotal() {
		return contarDiasAlquiler() * vehiculo.getPrecioAlquilerDia();
	}
}
