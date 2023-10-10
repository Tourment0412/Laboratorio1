package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Formatter;
import java.util.Scanner;
import java.util.stream.Collectors;

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

	public String codificarVehiculo(Vehiculo vehiculo) {
		return vehiculo.getPlaca() + "= codif =" + vehiculo.getNombre() + "= codif =" + vehiculo.getMarca()
				+ "= codif =" + vehiculo.getModelo() + "= codif ="
				+ Base64.getEncoder().encodeToString(vehiculo.getImageBytes()) + "= codif ="
				+ vehiculo.getTransmision().getText() + "= codif =" + vehiculo.getKilometraje() + "= codif ="
				+ vehiculo.getPrecioAlquilerDia() + "= codif =" + vehiculo.getNumSillas() + "= codif ="
				+ vehiculo.getFechaCreacion().toString();
	}

	public Vehiculo decodificarVehiculo(String encoding) {
		String[] arr = encoding.split("= codif =");
		Vehiculo vehiculo = null;
		try {
			vehiculo = Vehiculo.builder().placa(arr[0]).nombre(arr[1]).marca(arr[2]).modelo(Integer.parseInt(arr[3]))
					.transmision(Transmision.valueByText(arr[5])).kilometraje(Integer.parseInt(arr[6]))
					.precioAlquilerDia(Double.parseDouble(arr[7])).numSillas(Integer.parseInt(arr[8])).build();
		} catch (NumberFormatException | ImagenNoObtenidaException e) {
			return null;
		}
		vehiculo.setImageBytes(Base64.getDecoder().decode(arr[4]));
		vehiculo.setFechaCreacion(LocalDate.parse(arr[9]));
		return vehiculo;
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
		ft.format(codificarVehiculo(vehiculo) + "%n");
		fw.close();
		ft.close();
	}

	/**
	 * Metodo para leer el archivo de cliente
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Vehiculo> leerArchivoScanner() throws IOException {

		ArrayList<String> lista = new ArrayList<>();
		Scanner sc = new Scanner(new File(RUTA));

		while (sc.hasNextLine())
			lista.add(sc.nextLine());
		sc.close();

		return lista.stream().map(t -> decodificarVehiculo(t)).collect(Collectors.toCollection(ArrayList::new));
	}

}
