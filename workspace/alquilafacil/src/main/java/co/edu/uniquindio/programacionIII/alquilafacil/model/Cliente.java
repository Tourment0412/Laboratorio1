package co.edu.uniquindio.programacionIII.alquilafacil.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
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
	}

	public boolean cedulaEmpiezaPor(String filtro) {
		return cedula.startsWith(filtro);
	}

}
