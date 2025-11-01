package ar.edu.unju.escmi.tp7.dominio;

public class Detalle {

	private int cantidad;
    private double importe;
    private Producto producto;

    public Detalle(){
    }
    public Detalle(int cantidad, double importe, Producto producto) {
        this.cantidad = cantidad;
        this.importe = importe;
        this.producto = producto;
        calcularImporte();
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    public double getImporte(){
        return this.importe;
    }

    private void calcularImporte() {
        this.setImporte(this.cantidad * this.producto.getPrecio());
    }

    @Override
    public String toString() {
        return "PRODUCTO: " + producto + "\nCANTIDAD: " + cantidad + " | IMPORTE: " + importe + "\n";
    }
}
