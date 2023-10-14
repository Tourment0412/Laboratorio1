package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import co.edu.uniquindio.programacionIII.alquilafacil.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Transmision;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo;
import co.edu.uniquindio.programacionIII.alquilafacil.model.Vehiculo.VehiculoBuilder;

public class VehiculosDao {
	private static VehiculosDao instance;
	private final static String RUTA = "src/main/resources/data/vehiculos.txt";

	public static VehiculosDao getInstance() {
		if (instance == null)
			instance = new VehiculosDao();
		return instance;
	}

	public String codificarVehiculo(Vehiculo vehiculo) {
		return MessageFormat.format(
				"{0}= codif ={1}= codif ={2}= codif ={3}= codif ={4}= codif ={5}= codif ={6}= codif ={7}= codif ={8}= codif ={9}",
				vehiculo.getPlaca(), vehiculo.getNombre(), vehiculo.getMarca(), vehiculo.getModelo(),
				Base64.getEncoder().encodeToString(vehiculo.getImageBytes()), vehiculo.getTransmision().getText(),
				vehiculo.getKilometraje(), vehiculo.getPrecioAlquilerDia(), vehiculo.getNumSillas(),
				vehiculo.getFechaCreacion().toString());
	}

	public Vehiculo decodificarVehiculo(String encoding) {
		StringTokenizer tokenizer = new StringTokenizer("= codif =");

		try {
			VehiculoBuilder builder = Vehiculo.builder().placa(tokenizer.nextToken()).nombre(tokenizer.nextToken())
					.marca(tokenizer.nextToken()).modelo(Integer.parseInt(tokenizer.nextToken()))
					.imageBytes(Base64.getDecoder().decode(tokenizer.nextToken()))
					.transmision(Transmision.valueByText(tokenizer.nextToken()))
					.kilometraje(Integer.parseInt(tokenizer.nextToken()))
					.precioAlquilerDia(Double.parseDouble(tokenizer.nextToken()))
					.numSillas(Integer.parseInt(tokenizer.nextToken()))
					.fechaCreacion(LocalDate.parse(tokenizer.nextToken()));
			return builder.build();
		} catch (NumberFormatException | ImagenNoObtenidaException e) {
			return null;
		}
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
		BufferedReader reader = new BufferedReader(new FileReader(new File(RUTA)));
		ArrayList<Vehiculo> lista = reader.lines().map(cadena -> decodificarVehiculo(cadena))
				.collect(Collectors.toCollection(ArrayList::new));
		reader.close();
		return lista;
	}

}
