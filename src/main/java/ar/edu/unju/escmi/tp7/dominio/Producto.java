package ar.edu.unju.escmi.tp7.dominio;

public class Producto {

	private long codigo;
    private String descripcion;
    private double precioUnitario;
    private String origenFabricacion;

    public Producto() {

    }

    public Producto(long codigo, String descripcion, double precioUnitario, String origenFabricacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.origenFabricacion = origenFabricacion;
    }

  public String getNombre() {
    return descripcion;
}

public double getPrecio() {
    return precioUnitario;
}


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getOrigenFabricacion() {
        return origenFabricacion;
    }

    public void setOrigenFabricacion(String origenFabricacion) {
        this.origenFabricacion = origenFabricacion;
    }

    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= stock) {
            stock -= cantidad;
        } else {
            System.out.println("⚠️ Cantidad inválida para reducir el stock");
        }
    }
    @Override
    public String toString() {
        return "Codigo: " + codigo + " Descripcion: " + descripcion + " Precio Unitario: " + precioUnitario
                + " Origen fabricacion: " + origenFabricacion;
    }
}
