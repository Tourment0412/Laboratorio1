package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "vehiculos")
@NoArgsConstructor
public class Vehiculo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
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
	@Lob
	private byte[] imageBytes;
	@NonNull
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
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

	@Builder
	public Vehiculo(@NonNull String placa, @NonNull String nombre, @NonNull String marca, @NonNull Integer modelo,
			Image image, @NonNull Transmision transmision, @NonNull Integer kilometraje,
			@NonNull Double precioAlquilerDia, @NonNull Integer numSillas) throws IOException {
		this.placa = placa;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		setImage(image);
		this.transmision = transmision;
		this.kilometraje = kilometraje;
		this.precioAlquilerDia = precioAlquilerDia;
		this.numSillas = numSillas;
	}

	public Image getImage() {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
		return new Image(bais);
	}

	public void setImage(Image image) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
		imageBytes = baos.toByteArray();
	}
}
