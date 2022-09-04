package panaderia.entidades.base;

/**
 * Sitio donde se venden los productos de la panadería,
 * para lo cual hacen pedidos.
 * 
 * @version 1.1
 */
public class Tienda {
    private String codigo;
    private String nombre;

    public Tienda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Tienda - codigo= " + codigo + ", nombre=" + nombre;
    }
}
