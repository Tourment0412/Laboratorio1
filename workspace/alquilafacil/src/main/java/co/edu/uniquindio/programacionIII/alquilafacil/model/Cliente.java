package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@ToString
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@NonNull
	private String cedula;
	@Getter
	@Setter
	@NonNull
	private String nombre;
	@Getter
	@Setter
	@NonNull
	private String numeroTel;
	@Getter
	@Setter
	@NonNull
	private String email;
	@Getter
	@Setter
	@NonNull
	private String ciudad;
	@Getter
	@Setter
	@NonNull
	private String direccion;

	@OneToMany()
	@Getter
	private List<Alquiler> listaRegistros;

	@Builder
	private Cliente(@NonNull String cedula, @NonNull String nombre, @NonNull String numeroTel, @NonNull String email,
			@NonNull String ciudad, @NonNull String direccion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.numeroTel = numeroTel;
		this.email = email;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.listaRegistros = new ArrayList<>();
	}

}
