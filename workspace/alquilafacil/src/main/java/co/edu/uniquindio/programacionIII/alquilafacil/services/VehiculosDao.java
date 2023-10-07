package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class VehiculosDao {
	private static VehiculosDao instance;
	private final static String RUTA="src/main/resources/META-INF/vehiculos.txt";
	
	
	public static VehiculosDao getInstance() {
		if(instance==null)
			instance=new VehiculosDao();
		return instance;
	}
	
	
	/**
	 * Metodo para guardar la lista deseada (despues de guardar un vehiculo)
	 * 
	 * @param lista
	 * @throws IOException
	 */
	public void escribirArchivoFormatter(List<String> lista) throws IOException {
		Formatter ft = new Formatter(RUTA);
		for (String s : lista) {
			ft.format(s + "%n");
		}
		ft.close();
	}
	
	/**
	 * Metodo para leer el archivo de clinete
	 * @return
	 * @throws IOException
	 */

	public ArrayList<String> leerArchivoScanner() throws IOException {

		ArrayList<String> lista = new ArrayList<>();
		Scanner sc = new Scanner(new File(RUTA));

		while (sc.hasNextLine()) {
			lista.add(sc.nextLine());
		}

		sc.close();

		return lista;
	}
	
	

}
