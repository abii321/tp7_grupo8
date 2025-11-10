package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Factura {

	private LocalDate fecha;
    private long nroFactura;
    private Cliente cliente;
    private List<Detalle> detalles = new ArrayList<Detalle>();
    private Credito credito;


    public Factura(){
    }
    public Factura(LocalDate fecha, long nroFactura, Cliente cliente, List<Detalle> detalles) {
        this.fecha = fecha;
        this.nroFactura = nroFactura;
        this.cliente = cliente;
        this.detalles = detalles;
        calcularTotal();
    }
    public long getNroFactura() {
        return nroFactura;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public List<Detalle> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
    
    public double calcularTotal() {
        double total = 0;
        for (Detalle detalle : detalles) {
            total += detalle.getImporte();
        }
        return total;
    }

    public void setCredito(Credito credito) {
    this.credito = credito;
}

public void agregarDetalle(Detalle detalle) {
        if (detalle != null) {
            this.detalles.add(detalle);
        }
    }
    @Override
    public String toString() {
        return  "\n\n******************** Factura ********************"
                + "\nFecha: " + fecha + " N° de Factura: " + nroFactura
                + "\nCliente: " + cliente.getNombre() 
                + "\n************ Detalles de la Factura *************"
                + "\n" + detalles.toString().replaceAll("\\[|\\]", "").replaceAll(", ", "") + "\n";
    }
}
