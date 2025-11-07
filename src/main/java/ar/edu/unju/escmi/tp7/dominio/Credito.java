package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Credito {
	private TarjetaCredito tarjetaCredito;
	private Factura factura;
	private List<Cuota> cuotas = new ArrayList<Cuota>();
    private Cliente cliente;
    private Producto producto;
    

	public Credito() {
	}
	public Credito(TarjetaCredito tarjetaCredito, Factura factura) {
    this.tarjetaCredito = tarjetaCredito;
    this.factura = factura;
    this.cuotas = new ArrayList<>();
    generarCuotas();
}

	public Credito(TarjetaCredito tarjetaCredito, Factura factura, List<Cuota> cuotas) {
		this.tarjetaCredito = tarjetaCredito;
		this.factura = factura;
		this.cuotas = cuotas;
		generarCuotas();
	}

	public Credito(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	public TarjetaCredito getTarjetaCredito() {
		return tarjetaCredito;
	}
	public List<Cuota> getCuotas() {
		return cuotas;
	}

    public void setFactura(Factura factura) {
    this.factura = factura;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
    this.tarjetaCredito = tarjetaCredito;
    }


	
	public void generarCuotas() {
    cuotas.clear(); // limpia antes de generar
    double totalFactura = this.factura.calcularTotal();
	double limiteDisponible = this.tarjetaCredito.getLimiteCompra();

    if (totalFactura > limiteDisponible) {
        System.out.println("⚠️ El monto total supera el límite disponible de la tarjeta.");
        return;
    }

    int cantidadCuotas = 30; // Valor máximo permitido
    if (cuotas.size() > 30) {
        cantidadCuotas = 30; // limitar si venían más
    } else if (cuotas.isEmpty()) {
        cantidadCuotas = 30; // default
    } else {
        cantidadCuotas = cuotas.size(); // usa las que ya tenga si están bien
    }

    double montoCuota = totalFactura / cantidadCuotas;
    int nroCuota = 0;
    LocalDate currentDate = LocalDate.now();
    LocalDate auxDate = LocalDate.now();

    for (int i = 0; i < cantidadCuotas; i++) {
        nroCuota++;
        Cuota cuota = new Cuota();
        cuota.setMonto(montoCuota);
        cuota.setNroCuota(nroCuota);
        cuota.setFechaGeneracion(currentDate);
        auxDate = auxDate.plusMonths(1);
        cuota.setFechaVencimiento(auxDate);
        cuotas.add(cuota);
    }
}

	public void mostarCredito() {
		System.out.println("Tarjeta De Credito: " + tarjetaCredito + "\n" + factura + "\nCant. Cuotas:\n");
		for(Cuota cuota: cuotas) {
			System.out.println(cuota);
		}
	}

     public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}