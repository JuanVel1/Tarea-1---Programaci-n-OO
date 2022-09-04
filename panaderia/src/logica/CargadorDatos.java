package panaderia.logica;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import panaderia.datos.DatosPruebaTiendas;
import panaderia.datos.IFuenteDatos;
import panaderia.datos.LectorArchivo;
import panaderia.entidades.base.Producto;
import panaderia.entidades.base.Recorrido;
import panaderia.entidades.base.Tienda;

/**
 * Se encarga de crear los objetos a partir
 * de arreglos de cadenas de texto con la información
 * (esas cadenas pueden provenir de diferentes
 *  fuentes, por eso usa una interfaz, 
 *  para que se puedan cambiar fácilmente).
 * 
 * @version  1.0
 */
public class CargadorDatos {
	private Recorrido recorrido;

	public CargadorDatos(Recorrido recorrido) {
		this.recorrido = recorrido;
	}
	
	/**
	 * Carga los datos iniciales que necesita el programa:
	 * tiendas y productos.
	 */
	public void cargarDatosIniciales() {
		try {
			// SE DEBE CAMBIAR ESTA FUENTE, QUE ES DE PRUEBA
			// POR UNA QUE LEA LOS DATOS DE UN ARCHIVO.
			IFuenteDatos fuenteDatosTiendas = 
					new LectorArchivo("datos/tiendas.txt");
			
			List<String[]> datosBaseTiendas = 
					fuenteDatosTiendas.obtenerDatosBase();
			this.cargarDatosTiendas(datosBaseTiendas);

		} catch (IOException e) {
			Logger logger = Logger.getLogger(CargadorDatos.class.getName());
			logger.warning("Error al cargar los datos de la tienda");
		}

		try {
			// FALTA CARGAR LOS DATOS DE LOS PRODUCTOS:
			// PRIMERO HACERLO CON LA CLASE DE PRUEBA,
			// Y LUEGO CON LA QUE LEE DE UN ARCHIVO.
			IFuenteDatos fuenteDatosProductos = new LectorArchivo("datos/productos.txt");
			List<String[]> datosBaseProductos = 
					fuenteDatosProductos.obtenerDatosBase();
			this.cargarDatosProductos(datosBaseProductos);

		} catch (IOException e) {
			Logger logger = Logger.getLogger(CargadorDatos.class.getName());
			logger.warning("Error al cargar los datos de la tienda");
		}
	}
	
	/**
	 * A partir de los datos base (cadenas de texto),
	 * obtiene los datos de las tiendas,
	 * crea los objetos y los guarda en el objeto recorrido.
	 */
	private void cargarDatosTiendas(List<String[]> datosBase) {
		for (String[] datoBaseTienda: datosBase) {
			String codigo = datoBaseTienda[0];
			String nombre = datoBaseTienda[1];
			Tienda tienda = new Tienda(codigo, nombre);
			this.recorrido.addTienda(tienda);
		}
	}
	
	/**
	 * A partir de los datos base (cadenas de texto),
	 * obtiene los datos de los productos,
	 * crea los objetos y los guarda en el objeto recorrido.
	 */
	private void cargarDatosProductos(List<String[]> datosBase) {
		for (String[] datoProducto : datosBase) {
			String codigo = datoProducto[0];
			String nombre = datoProducto[1];
			double precio = Double.parseDouble(datoProducto[2]);

			Producto producto = new Producto(codigo, nombre, precio);
			this.recorrido.addProducto(producto);
		}
	}
}