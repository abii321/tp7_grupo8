package ar.edu.unju.escmi.tp7.dominio;

public class Producto {

	private long codigo;
    private String descripcion;
    private double precioUnitario;
    private String origenFabricacion;

    public Producto(){
    }
    public Producto(long codigo, String descripcion, double precioUnitario, String origenFabricacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.origenFabricacion = origenFabricacion;

    }

    public String getDescripcion(){
        return descripcion;
    }

    public long getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return descripcion;
    }
    public double getPrecio(){
        return precioUnitario;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " Descripcion: " + descripcion + " Precio Unitario: " + precioUnitario
                + " Origen fabricacion: " + origenFabricacion;
    }
}
