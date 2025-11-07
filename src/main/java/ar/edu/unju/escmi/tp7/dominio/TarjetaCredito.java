package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;

public class TarjetaCredito {
	private long numero;
	private LocalDate fechaCaducacion;
	private Cliente cliente;
	private double limiteCompra;

	public TarjetaCredito() {
    }
    public TarjetaCredito(long numero, LocalDate fechaCaducacion, Cliente cliente, double limiteCompra) {
		super();
		this.numero = numero;
		this.fechaCaducacion = fechaCaducacion;
		this.cliente = cliente;
		this.limiteCompra = limiteCompra;
	}

	public long getNumero() {
		return numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public double getLimiteCompra() {
		return limiteCompra;
	}

	public void setLimiteCompra(double limite) {
        this.limiteCompra = limite;
    }

	public void setNumero(long numero) {
        this.numero = numero;
    }
	@Override
	public String toString() {
		return "\nNumero: " + numero + " Fecha De Caducacion: " + fechaCaducacion + "\nNombre Titular: "
				+ cliente.getNombre() + ", Limite De Compra Actual:" + limiteCompra;
	}

	
}