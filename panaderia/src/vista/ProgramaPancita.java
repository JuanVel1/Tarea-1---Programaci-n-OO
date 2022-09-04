package panaderia.vista;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Scanner;

import panaderia.logica.ControlRecorrido;

/**
 * Realiza la interacción con el usuario
 * para poder iniciar el programa y procesar
 * las órdenes de pedido de un recorrido de ventas.
 * 
 * @version 1.0
 */
public class ProgramaPancita {
	private ControlRecorrido control;

	public ProgramaPancita() {
		this.control = new ControlRecorrido();
	}
	
	/**
	 * Permite cargar los datos iniciales necesarios
	 * para hacer el recorrido.
	 */
	public void iniciar() {
		this.control.cargarDatosIniciales();
	}
	
	/**
	 * Es el ciclo de control general del programa,
	 * para saber si hay más ordenes o termina.
	 */
	public void hacerRecorrido() {
		Scanner consola = new Scanner(System.in);
		String respuesta = "S";
		while (respuesta.equals("S")) {
			System.out.println("¿Desea crear orden de pedido (S/N)?");
			respuesta = consola.next().toUpperCase();
			if (respuesta.equals("S")) {
				this.procesarUnaOrden();
			}
		}
		System.out.println("Fin del programa. Muchas gracias.");
		consola.close();
	}
	
	/**
	 * Coordina el proceso para poder crear una orden
	 * de pedido, mostrarla y pedir la aceptación
	 * del usuario.
	 */
	private void procesarUnaOrden() {
		// COMPLETAR:
		
		// PRIMERO PEDIR EL CÓDIGO DE LA TIENDA
		// Y VERIFICAR SI EXISTE.
		Scanner consola = new Scanner(System.in);

		System.out.println("Escribe el código de la tienda: ");
		String codigoTienda = consola.next().toUpperCase();

		boolean existeLaTienda = this.control.existeTienda(codigoTienda);
		if (existeLaTienda == false) {
			System.out.println("\nNo existe la tienda ingresada\n");
			return;
		}
		
		// SI EXISTE:
		// PEDIR LA RUTA CON LOS PRODUCTOS DEL PEDIDO
		// Y SOLICITAR AL CONTROL CREAR LA ORDEN.
		System.out.println("Escribe la ruta del archivo con los productos que desea pedir: ");
		String nombreArchivo = consola.next();

		try {
			this.control.crearOrden(nombreArchivo, codigoTienda);
		} catch (IOException e) {
			Logger logger = Logger.getLogger(ProgramaPancita.class.getName());
			logger.warning("Error al cargar los detalles de los productos");

			return;
		}

		
		// LUEGO: PEDIR AL CONTROL LOS DATOS DE LA TIENDA
		// Y LOS DETALLES ORDENADOS, PARA MOSTRAR AL USUARIO.
		System.out.println("\n=== Datos de la tienda ===");
		System.out.println(this.control.obtenerDatosTienda(codigoTienda));

		System.out.println("\n=== Detalles ordenados ===");
		for (String detalle : this.control.obtenerDetallesOrdenados()) {
			System.out.println(detalle);
		}
		
		// PREGUNTAR SI DESEA ACEPTAR. SI ACEPTA:
		// INFORMAR AL CONTROL PARA QUE GUARDE LA ORDEN.
		System.out.println("\n¿Desea aceptar la orden? (S/N): ");
		String respuesta = consola.next().toUpperCase();

		if (respuesta.equals("S")) {
			this.control.guardarOrden();
		}
	}
}
