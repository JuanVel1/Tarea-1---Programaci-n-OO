package panaderia.datos;

import java.io.IOException;
import java.util.List;

/**
 * Una fuente externa que proporciona datos para el programa,
 * como por ejemplo, un archivo, una base de datos u otro.
 * 
 * @version 1.0
 */
public interface IFuenteDatos {
	
	/**
	 * Obtiene una lista de elementos de una fuente de datos.
	 * Cada elemento es un arreglo de cadenas 
	 * (lo cual permite obtener diferentes tipos de información,
	 *  lo importante es que se pueda organizar en 
	 *  conjuntos de cadenas).
	 * @throws IOException
	 */
	public abstract List<String[]> obtenerDatosBase() throws IOException;
}
