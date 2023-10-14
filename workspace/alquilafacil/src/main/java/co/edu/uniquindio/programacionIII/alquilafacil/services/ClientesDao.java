package co.edu.uniquindio.programacionIII.alquilafacil.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.edu.uniquindio.programacionIII.alquilafacil.model.Cliente;

public class ClientesDao {

	private final static String RUTA = "src/main/resources/data/clientes.txt";
	private static ClientesDao instance;

	public static ClientesDao getInstance() {
		if (instance == null)
			instance = new ClientesDao();
		return instance;
	}

	private String codificarCliente(Cliente cliente) {
		return MessageFormat.format("{0}={1}={2}={3}={4}={5}", cliente.getCedula(), cliente.getNombre(),
				cliente.getNumeroTel(), cliente.getEmail(), cliente.getCiudad(), cliente.getDireccion());
	}

	/**
	 * Metodo para guardar la lista deseada (despues de guardar un cliente)
	 * 
	 * @param lista
	 * @throws IOException
	 */
	public void escribirArchivoFormatter(Cliente cliente) throws IOException {
		FileWriter fw = new FileWriter(RUTA, true);
		Formatter ft = new Formatter(fw);
		String string = codificarCliente(cliente);
		ft.format(string + "%n");
		fw.close();
		ft.close();
	}

	/**
	 * Metodo para leer el archivo de vehiculo
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Cliente> leerArchivoScanner() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(RUTA)));
		Stream<Cliente> map = reader.lines().map(t -> decodificarCliente(t));
		reader.close();
		// tambien se puede con .toList (pero a veces falla)
		return map.collect(Collectors.toCollection(ArrayList::new));
	}

	private Cliente decodificarCliente(String t) {
		String[] param = t.split("=");
		Cliente nc = Cliente.builder().cedula(param[0]).nombre(param[1]).numeroTel(param[2]).email(param[3])
				.ciudad(param[4]).direccion(param[5]).build();
		return nc;
	}

}
