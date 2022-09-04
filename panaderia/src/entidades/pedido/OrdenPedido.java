package panaderia.entidades.pedido;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import panaderia.entidades.base.Producto;
import panaderia.entidades.base.Tienda;

/**
 * Una orden de pedido que realiza una tienda, con varios productos
 * (detalles).
 * 
 * @version 1.5
 */
public class OrdenPedido {
    private Tienda tienda;
    private LocalDate fecha;
    private List<DetallePedido> detalles;

    public OrdenPedido(Tienda tienda) {
        this.tienda = tienda;
        this.fecha = LocalDate.now();
        this.detalles = new ArrayList<>();
    }

    /**
     * Ordena los elementos de la lista (los detalles de la orden)
     * de manera alfabética por nombre del producto.
     * Para esto la clase DetallePedido debe implementar 
     * la interfaz Comparable.
     */
    private void ordenarDetalles(){
        Collections.sort(detalles);
    }

    /**
     * Retorna la información de los productos que incluye el pedido,
     * pero como String (no con los objetos Producto), 
     * para que se puedan mostrar al usuario.
     */
    public List<String> getDetallesOrdenados(){
    	this.ordenarDetalles();
        List<String> datosDetallesPedido = new ArrayList<>();
        for (DetallePedido detalle: detalles){
            datosDetallesPedido.add(detalle.toString());
        }
        return datosDetallesPedido;
    }

    /** 
     * Guarda la información del producto y la cantidad que
     * desea pedirse de ese producto, para que quede 
     * en el pedido.
     */
    public void addDetalle(Producto producto, int cantidadPedida){
        DetallePedido detalle = new DetallePedido(producto, cantidadPedida);
        detalles.add(detalle);
    }

	@Override
	public String toString() {
		return "OrdenPedido - tienda=" + tienda + ", fecha=" + fecha
				+ ", detalles=[" + detalles + "]";
	}
}
