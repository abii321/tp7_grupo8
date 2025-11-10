package ar.edu.unju.escmi.tp7.dominio;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.collections.CollectionFactura;

public class Cliente {
	private long dni;
	private String nombre;
	private String direccion;
	private String telefono;
	private TarjetaCredito tarjetaCredito;


	// constructor vacío (necesario para CuotaTest.java)
    public Cliente() {}
	
	public Cliente(long dni, String nombre, String domicilio, String telefono) {
    this.dni = dni;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
	this.tarjetaCredito = tarjetaCredito;
	}



    public Cliente(long dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
	public long getDni() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}

	public List<Factura> consultarCompras() {
		List<Factura> shoppingList = new ArrayList<Factura>();
		if (CollectionFactura.facturas != null) {
			for (Factura fac : CollectionFactura.facturas) {
				if (fac.getCliente().getDni() == this.dni) {
					shoppingList.add(fac);
				}
			}
		} else {
			shoppingList = null;
		}
		return shoppingList;
	}

	public void setDni(long dni) {
    this.dni = dni;
}

	public void setNombre(String nombre) {
    this.nombre = nombre;
	}

	public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
}
