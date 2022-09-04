package panaderia.entidades.base;

/**
 * Los productos que ofrece la panadería a las tiendas.
 * 
 * @version 1.1
 */
public class Producto {
    private String codigo;
    private String nombre;
    private double valorUnitario;

    public Producto(String codigo, String nombre, double valorUnitario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public String toString(){
        return "Producto - código="+ codigo +
                ", nombre="+ nombre+
                ", valor unitario="+ valorUnitario;
    }
}
