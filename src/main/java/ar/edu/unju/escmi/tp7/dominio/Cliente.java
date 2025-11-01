package ar.edu.unju.escmi.tp7.dominio;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.collections.CollectionFactura;

public class Cliente {
	private long dni;
	private String nombre;
	private String direccion;
	private String telefono;
	
	public Cliente(long dni, String nombre, String direccion, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
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
}
