package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javafx.scene.image.Image;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
public class Vehiculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@NonNull
	@Getter
	private String placa;
	@NonNull
	@Getter
	@Setter
	private String nombre;
	@NonNull
	@Getter
	@Setter
	private String marca;
	@NonNull
	@Getter
	@Setter
	private Integer modelo;

	@Setter
	@Getter
	private String rutaImg;

	@NonNull
	@Getter
	@Setter
	private Transmision transmision;
	@NonNull
	@Getter
	@Setter
	private Integer kilometraje;
	@Getter
	@Setter
	@NonNull
	private Double precioAlquilerDia;
	@NonNull
	@Getter
	@Setter
	private Integer numSillas;

	@Getter
	@Setter
	private LocalDate fechaCreacion;

	@Builder
	public Vehiculo(@NonNull String placa, @NonNull String nombre, @NonNull String marca, @NonNull Integer modelo,
			@NonNull Transmision transmision, @NonNull Integer kilometraje, @NonNull Double precioAlquilerDia,
			@NonNull Integer numSillas, @NonNull String rutaImg) {
		this.placa = placa;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		this.rutaImg = rutaImg;
		this.transmision = transmision;
		this.kilometraje = kilometraje;
		this.precioAlquilerDia = precioAlquilerDia;
		this.numSillas = numSillas;
		this.fechaCreacion = LocalDate.now();
	}

	public Image getImage() {
		return new Image(rutaImg);
	}

	public boolean fueCreadoAntesDe(LocalDate fecha) {
		Objects.requireNonNull(fecha, "La fecha final no puede ser null");
		return fechaCreacion.isEqual(fecha) || fechaCreacion.isBefore(fecha);
	}

	public boolean placaEmpiezaPor(String filtro) {
		return placa.startsWith(filtro);
	}
}
