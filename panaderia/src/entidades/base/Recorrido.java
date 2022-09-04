package panaderia.entidades.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recopila la información básica necesaria para que un
 * vendedor de la panadería pueda hacer su recorrido de ventas.
 * 
 * @version 1.5
 */
public class Recorrido {
    private List<Tienda> tiendas;
    private Map<String,Producto> productos;

    public Recorrido() {
        tiendas = new ArrayList<>();
        productos = new HashMap<>();
    }

    public void addTienda(Tienda tienda) {
    	if (buscarTienda(tienda.getCodigo()) != null) {
    		throw new IllegalArgumentException();
    	}
        this.tiendas.add(tienda);
    }

    public void addProducto(Producto producto){
        productos.put(producto.getCodigo(),producto);
    }

    public Producto buscarProducto(String codigo){
        return productos.get(codigo);
    }

    public Tienda buscarTienda(String codigo){
        for (Tienda tienda: tiendas) {
            if (tienda.getCodigo().equals(codigo)){
                return tienda;
            }
        }
        return null;
    }
}
