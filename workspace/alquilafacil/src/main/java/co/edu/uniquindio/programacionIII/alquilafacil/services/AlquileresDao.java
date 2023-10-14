package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Alquiler;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlquileresDao {

	private static AlquileresDao instance;

	@Getter
	private static final String RUTA = "src/main/resources/data/alquileres.dat";

	public static AlquileresDao getInstance() {
		if (instance == null)
			instance = new AlquileresDao();
		return instance;
	}

	/**
	 * Guarda el objeto (pensado en alquileres) deseado en un archivo indicado por
	 * medio de serializacion
	 * 
	 * @param listaAlquileres
	 */
	public void saveData(List<Alquiler> listaAlquileres) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(listaAlquileres);
			oos.close();
		} catch (IOException e) {
			LogHandler.getInstance().logSevere(e.getMessage());
		}
	}

	/**
	 * Este metodo se encarga de cargar un objeto de la serializacion y darle la
	 * "forma" deseada
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Alquiler> loadData() {
		List<Alquiler> listaAlquileres;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			listaAlquileres = (List<Alquiler>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			listaAlquileres = new ArrayList<Alquiler>();
			saveData(listaAlquileres);
		}
		return listaAlquileres;

	}

}
