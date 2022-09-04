package panaderia.datos;

import java.io.BufferedWriter;
import static java.nio.file.StandardOpenOption.APPEND;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import panaderia.entidades.pedido.OrdenPedido;

/**
 * Guarda la información de las órdenes de pedido en un archivo, para que
 * después puedan ser procesadas en la panadería.
 * 
 * @version 1.0
 */
public class EscritorArchivoOrdenes {
	private static final String ARCHIVO = "OrdenesPedido.txt";

	/**
	 * Guarda la información de una orden de pedido
	 * en un archivo de texto, para que luego
	 * se puedan revisar y procesar en la panadería.
	 */
	public void escribirOrden(OrdenPedido orden) {
		Path rutaArchivo = Paths.get(ARCHIVO);

		try {
			BufferedWriter escritor = Files.newBufferedWriter(rutaArchivo, APPEND);
			escritor.write(orden.toString());
			escritor.close();
		} catch (IOException ex) {
			System.out.println(ex);
			Logger logger = 
					Logger.getLogger(EscritorArchivoOrdenes.class.getName());
			logger.warning("Error al escribir en el archivo "
					+ "la Orden de Pedido: "+orden.toString());
		}
	}
}
