package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	private Date fechaRegistro;

	@NonNull
	@Getter
	private LocalDate fechaAlquiler;

	@NonNull
	@Getter
	private LocalDate fechaRegreso;

	@NonNull
	@Getter
	private Double costoTotal;

	@Builder
	private Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler, LocalDate fechaRegreso,
			Double costoTotal) {
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaRegreso = fechaRegreso;
		this.costoTotal = costoTotal;
	}
}
