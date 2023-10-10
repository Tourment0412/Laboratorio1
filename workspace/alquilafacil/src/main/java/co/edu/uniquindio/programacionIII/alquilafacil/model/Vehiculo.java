package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.imageio.ImageIO;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Vehiculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	@EqualsAndHashCode.Include
	private String placa;
	@NonNull
	@Setter
	private String nombre;
	@NonNull
	@Setter
	private String marca;
	@NonNull
	@Setter
	private Integer modelo;

	@NonNull
	@Setter
	private Transmision transmision;
	@NonNull
	@Setter
	private Integer kilometraje;
	@Setter
	@NonNull
	private Double precioAlquilerDia;
	@NonNull
	@Setter
	private Integer numSillas;

	@Setter
	private LocalDate fechaCreacion;

	@ToString.Exclude
	@Setter
	private byte[] imageBytes;

	@Builder
	public Vehiculo(@NonNull String placa, @NonNull String nombre, @NonNull String marca, Image image,
			@NonNull Integer modelo, @NonNull Transmision transmision, @NonNull Integer kilometraje,
			@NonNull Double precioAlquilerDia, @NonNull Integer numSillas) throws ImagenNoObtenidaException {
		this.placa = placa;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;

		this.transmision = transmision;
		this.kilometraje = kilometraje;
		this.precioAlquilerDia = precioAlquilerDia;
		this.numSillas = numSillas;
		this.fechaCreacion = LocalDate.now();
		if (image != null)
			setImage(image);
	}

	public Image getImage() {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
		return new Image(bais);
	}

	public void setImage(Image image) throws ImagenNoObtenidaException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
			imageBytes = baos.toByteArray();
		} catch (IOException e) {
			throw new ImagenNoObtenidaException("La imagen no pudo ser obtenida", e);
		}
	}

	public boolean fueCreadoAntesDe(LocalDate fecha) {
		Objects.requireNonNull(fecha, "La fecha final no puede ser null");
		return fechaCreacion.isEqual(fecha) || fechaCreacion.isBefore(fecha);
	}

	public boolean placaEmpiezaPor(String filtro) {
		return placa.startsWith(filtro);
	}
}
