package ar.edu.unju.escmi.tp7.main;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

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
					 case 1:
              try {
             long dni = InputUtil.inputLong("Ingrese el DNI del cliente: ");
              Cliente cliente = CollectionCliente.buscarCliente(dni);
                if (cliente == null)
            throw new UsuarioNoRegistradoException("El cliente no está registrado.");
  
                 long codigoProducto = InputUtil.inputLong("Ingrese el código del producto: ");
                  Producto producto = CollectionProducto.buscarProducto(codigoProducto);
                   if (producto == null)
                throw new ProductoNoEncontradoException("No existe un producto con ese código.");

                  int cantidad = InputUtil.inputInt("Ingrese la cantidad que desea comprar: ");
                  Stock stock = CollectionStock.buscarStock(producto);
                  if (stock == null || stock.getCantidad() < cantidad)
                  throw new StockInsuficienteException("No hay suficiente stock disponible para este producto.");

                   // Crear detalle de la venta
                   Detalle detalle = new Detalle(cantidad, producto.getPrecio() * cantidad, producto);
                  List<Detalle> detalles = new ArrayList<>();
                   detalles.add(detalle);

                     // Crear factura
                    long nroFactura = CollectionFactura.facturas.size() + 1;
                   Factura factura = new Factura(LocalDate.now(), nroFactura, cliente, detalles);
                  CollectionFactura.agregarFactura(factura);
                    // Reducir stock
                    CollectionStock.reducirStock(stock, cantidad);

                 System.out.println("\n Venta realizada con éxito!");
                 System.out.println(factura);

                  } catch (UsuarioNoRegistradoException | ProductoNoEncontradoException | StockInsuficienteException e) {
                  System.out.println("Error " + e.getMessage());
                    } catch (Exception e) {
                System.out.println(" Error inesperado: " + e.getMessage());
                        }
                       break;
                
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

            case 3:
            try {
                CollectionStock.mostrarProductosDisponibles();
                } catch (IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
					 case 4:
                try {
                long codigo = InputUtil.inputLong("Ingrese el código del producto a consultar: ");
                Producto producto = CollectionProducto.buscarProducto(codigo);
               if (producto == null)
                throw new ProductoNoEncontradoException("No se encontró un producto con ese código.");

               Stock stock = CollectionStock.buscarStock(producto);
               if (stock == null)
               throw new StockInsuficienteException("No hay registro de stock para este producto.");

                System.out.println("\n El producto '" + producto.getDescripcion() +
                "' tiene un stock de " + stock.getCantidad() + " unidades.");

              } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println("Error " + e.getMessage());
              } catch (Exception e) {
              System.out.println("Error inesperado: " + e.getMessage());
               }
               break;


                case 6: System.out.println("Saliendo del programa..."); break;    
                default: System.out.println("La opcion seleccionada no existe");break;
            }

        }while(opcion != 6);

        sc.close();
	}
}
