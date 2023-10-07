package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ObjetoYaExisteException;
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

	@Setter
	@Getter
	private String rutaImg;

	@Lob
	@Setter
	@Getter
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

	@Getter
	@Setter
	private LocalDate fechaCreacion;

	@Getter
	@OneToMany
	private List<Alquiler> listaAlquileres;

	@Builder
	public Vehiculo(@NonNull String placa, @NonNull String nombre, @NonNull String marca, @NonNull Integer modelo,
			Image image, @NonNull Transmision transmision, @NonNull Integer kilometraje,
			@NonNull Double precioAlquilerDia, @NonNull Integer numSillas, @NonNull String rutaImg)
			throws ImagenNoObtenidaException {
		this.placa = placa;
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		setImage(image);
		this.rutaImg = rutaImg;
		this.transmision = transmision;
		this.kilometraje = kilometraje;
		this.precioAlquilerDia = precioAlquilerDia;
		this.numSillas = numSillas;
		this.fechaCreacion = LocalDate.now();
		this.listaAlquileres = new ArrayList<>();
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

	public void agregarAlquiler(Alquiler alquiler) throws ObjetoYaExisteException {
		if (listaAlquileres.contains(alquiler))
			throw new ObjetoYaExisteException("El alquiler ya existe en el vehiculo");
		listaAlquileres.add(alquiler);
	}

	public boolean placaEmpiezaPor(String filtro) {
		return placa.startsWith(filtro);
	}
}
