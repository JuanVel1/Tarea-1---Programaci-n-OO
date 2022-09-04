package panaderia.logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panaderia.datos.EscritorArchivoOrdenes;
import panaderia.datos.IFuenteDatos;
import panaderia.datos.LectorArchivo;
import panaderia.entidades.base.Producto;
import panaderia.entidades.base.Recorrido;
import panaderia.entidades.pedido.OrdenPedido;
import panaderia.entidades.base.Tienda;

/**
 * Lógica del programa de un recorrido de un vendedor,
 * para crear las órdenes de pedido de las tiendas.
 * 
 * @version 1.0
 */
public class ControlRecorrido {
	private Recorrido recorrido;
	private OrdenPedido ordenEnProceso;
	
	public ControlRecorrido() {
		this.recorrido = new Recorrido();
		this.ordenEnProceso = null;
	}

	public void cargarDatosIniciales() {
		CargadorDatos cargador = new CargadorDatos(recorrido);
		cargador.cargarDatosIniciales();
	}
	
	
	// COMPLETAR LOS MÉTODOS QUE FALTAN
	/**
	 * Recibe un código de tienda y verifica
	 * si existe una tienda en el recorrido
	 */
	public boolean existeTienda(String codigoTienda) {
		Tienda tiendaEncontrada = this.recorrido.buscarTienda(codigoTienda);

		return tiendaEncontrada != null;
	}

	/**
	 * Crea una orden, con los detalles obtenidos de los productos y su respectiva cantidad
	 * @param nombreArchivoProductos Nombre del archivo donde se encuentra el codigo de producto y su cantidad
	 * @param codigoTienda El codigo de la tienda al que pertenece la orden
	 */
	public void crearOrden(String nombreArchivoProductos, String codigoTienda) throws IOException {
		this.ordenEnProceso = new OrdenPedido(this.recorrido.buscarTienda(codigoTienda));

	
		IFuenteDatos fuenteDatosDetalles = new LectorArchivo(nombreArchivoProductos);

		// Retorna una lista con el codigo de producto y cantidad pedida
		List<String[]> datosBase = fuenteDatosDetalles.obtenerDatosBase();

		for (String[] detalle : datosBase) {
			this.crearDetalle(ordenEnProceso, detalle);
		}		
	}


	/**
	 * Crea el detalle de una orden de pedido
	 * @param orden La orden de pedido
	 * @param datosBaseDetalle Los detalles obtenidos del archivo de texto
	 */
	private void crearDetalle(OrdenPedido orden, String[] datosBaseDetalle) {
		String codigo = datosBaseDetalle[0];
		int cantidad = Integer.parseInt(datosBaseDetalle[1]);
		
		Producto productoEncontrado = this.recorrido.buscarProducto(codigo);
		if (productoEncontrado == null) {
			return;
		}

		orden.addDetalle(productoEncontrado, cantidad);
	}

	/**
	 * Método que obtiene todos los datos relacionados am una tienda
	 * @param codigoTienda El código de la tienda que se desea obtener los datos
	 * @return Los datos de la tienda
	 */
	public String obtenerDatosTienda(String codigoTienda) {
		Tienda tiendaEncontrada = this.recorrido.buscarTienda(codigoTienda);

		return tiendaEncontrada.toString();
	}

	/**
	 * Método que obtiene una lista con todos los datos 
	 * de la tienda, ordenados según el nombre en orden alfabético
	 * @return La lista de detalles ordenados
	 */
	public List<String> obtenerDetallesOrdenados() {
		return this.ordenEnProceso.getDetallesOrdenados();
	}
	
	/**
	 * Envía la orden para que su información 
	 * se guarde en un archivo, y luego
	 * deja el atributo en null para que se
	 * pueda procesar una nueva orden.
	 */
	public void guardarOrden() {
		EscritorArchivoOrdenes escritor = new EscritorArchivoOrdenes();
		escritor.escribirOrden(ordenEnProceso);
		ordenEnProceso = null;
	}
}
