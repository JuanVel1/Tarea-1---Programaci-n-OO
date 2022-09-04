package panaderia.entidades.pedido;

import panaderia.entidades.base.Producto;

/**
 * Información de uno de los productos contenidos en una orden de pedido.
 * Permite tener la cantidad pedida y hacer algunas operaciones,
 * como calcular el total y definir la forma de ordenar (alafabético).
 * 
 * @version 1.1
 */
public class DetallePedido implements Comparable<DetallePedido> {
    private Producto producto;
    private int cantidadPedida;

    public DetallePedido(Producto producto, int cantidadPedida) {
        this.producto = producto;
        this.cantidadPedida = cantidadPedida;
    }

    /**
     * Calcula el valor de este detalle del pedido, considerando
     * el precio unitario del producto y la cantidad pedida.
     * 
     * @return  el valor total del detalle, en pesos
     */
    public double calcularValorTotal(){
        double precioProducto = producto.getValorUnitario();
        return precioProducto * cantidadPedida;
    }

    public String getNombreProducto(){
        return producto.getNombre();
    }

    /**
     * Permite definir el criterio por el cual se ordenarán estos objetos
     * cuando sean comparados.
     * En este caso, por el nombre del producto
     *  (como es String, se usará el método compareTo de String
     *   para hacer la comparación internamente).
     *   
     * @param otroDetalle El otro objeto con el cual se compara este
     * @return positivo si este es mayor, cero si son iguales
     * 		 y negativo si este es menor que el otro objeto.
     */
    @Override
    public int compareTo(DetallePedido otroDetalle) {
        return producto.getNombre().compareTo(
        		otroDetalle.producto.getNombre());
    }    
    
    @Override
    public String toString() {
        return producto.toString() +
                ", cantidadPedida=" + cantidadPedida +
                ", valorTotal="+ this.calcularValorTotal();
    }
}
