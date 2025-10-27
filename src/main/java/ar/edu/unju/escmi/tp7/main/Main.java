package ar.edu.unju.escmi.tp7.main;

import java.util.Scanner;
import java.util.List;

//import javax.swing.text.Utilities;
//import org.jcp.xml.dsig.internal.dom.Utils;

import ar.edu.unju.escmi.tp7.collections.*;
import ar.edu.unju.escmi.tp7.dominio.*;
import ar.edu.unju.escmi.tp7.utils.InputUtil;
import ar.edu.unju.escmi.tp7.exceptions.*;


public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		CollectionTarjetaCredito.precargarTarjetas();
        CollectionCliente.precargarClientes();
        CollectionProducto.precargarProductos();
        CollectionStock.precargarStocks();
        int opcion = 0;
        do {
        	System.out.println("\n====== Menu Principal =====");
            System.out.println("1- Realizar una venta");
            System.out.println("2- Revisar compras realizadas por el cliente (debe ingresar el DNI del cliente)");
            System.out.println("3- Mostrar lista de los electrodomésticos");
            System.out.println("4- Consultar stock");
            System.out.println("5- Revisar creditos de un cliente (debe ingresar el DNI del cliente)");
            System.out.println("6- Salir");
            opcion = InputUtil.inputInt("Ingrese su opcion: ");

            switch (opcion) {
                case 2: {
                    long dni = InputUtil.inputLong("Ingrese el dni del cliente: ");
                    try{
                        Cliente cliente = CollectionCliente.buscarCliente(dni);
                        if( cliente == null ) throw new UsuarioNoRegistradoException("El usuario no existe.");
                        List<Factura> compras = cliente.consultarCompras();
                        for(Factura f : compras) System.out.println(f);
                    } catch (UsuarioNoRegistradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: System.out.println("Saliendo del programa..."); break;    
                default: System.out.println("La opcion seleccionada no existe");break;
            }

        }while(opcion != 6);

        sc.close();
	}
}
