package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;

public class VehiculosDao {
	private static VehiculosDao instance;
	private final static String RUTA = "src/main/resources/data/vehiculos.txt";

	public static VehiculosDao getInstance() {
		if (instance == null)
			instance = new VehiculosDao();
		return instance;
	}

	/**
	 * Metodo para guardar la lista deseada (despues de guardar un vehiculo)
	 * 
	 * @param lista
	 * @throws IOException
	 */
	public void escribirArchivoFormatter(Vehiculo vehiculo) throws IOException {
		FileWriter fw = new FileWriter(RUTA, true);
		Formatter ft = new Formatter(fw);
		ft.format(vehiculo.getPlaca() + "=" + vehiculo.getNombre() + "=" + vehiculo.getMarca() + "="
				+ vehiculo.getModelo() + "=" + vehiculo.getRutaImg() + "=" + vehiculo.getTransmision() + "="
				+ vehiculo.getKilometraje() + "=" + vehiculo.getPrecioAlquilerDia() + "=" + vehiculo.getNumSillas()
				+ vehiculo.getFechaCreacion().toString());
		fw.close();
		ft.close();
	}

	/**
	 * Metodo para leer el archivo de clinete
	 * 
	 * @return
	 * @throws IOException
	 */

	public ArrayList<Vehiculo> leerArchivoScanner() throws IOException {

		ArrayList<String> lista = new ArrayList<>();
		Scanner sc = new Scanner(new File(RUTA));

		while (sc.hasNextLine()) {
			lista.add(sc.nextLine());
		}

		sc.close();
		Stream<Vehiculo> map = lista.stream().map(t -> {
			String[] param = t.split("=");
			try {
				Vehiculo nv = Vehiculo.builder().placa(param[0]).nombre(param[1]).marca(param[2])
						.modelo(Integer.parseInt(param[3])).rutaImg(param[4])
						.transmision(Transmision.valueByText(param[5])).kilometraje(Integer.parseInt(param[6]))
						.precioAlquilerDia(Double.parseDouble(param[7])).numSillas(Integer.parseInt(param[8])).build();
				nv.setFechaCreacion(LocalDate.parse(param[9]));
				return nv;
			} catch (ImagenNoObtenidaException e) {
				return null;
			}

		});

		return map.collect(Collectors.toCollection(ArrayList::new));
	}

}
