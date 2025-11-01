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
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		CollectionCliente.precargarClientes();
		CollectionTarjetaCredito.precargarTarjetas();
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
                case 1:{
                    try {
                        long dni = InputUtil.inputLong("Ingrese el DNI del cliente: ");
                        Cliente cliente = CollectionCliente.buscarCliente(dni);
                        if (cliente == null) throw new UsuarioNoRegistradoException("El cliente no está registrado.");

                        // Buscar tarjeta asociada al cliente
                        TarjetaCredito tarjeta = CollectionTarjetaCredito.buscarTarjetaCreditoPorCliente(cliente);
                        if (tarjeta == null) throw new Exception("El cliente no tiene una tarjeta de crédito registrada.");

                        // Crear factura vacía
                        long nroFactura = CollectionFactura.facturas.size() + 1;
                        Factura factura = new Factura(LocalDate.now(), nroFactura, cliente, new ArrayList<>());

                        double totalCompra = 0;
                        boolean continuar = true;

                        do {
                            long codigoProducto = InputUtil.inputLong("Ingrese el código del producto: ");
                            Producto producto = CollectionProducto.buscarProducto(codigoProducto);
                            if (producto == null) throw new ProductoNoEncontradoException("No existe un producto con ese código.");
  
                            int cantidad = InputUtil.inputInt("Ingrese la cantidad que desea comprar: ");
                            Stock stock = CollectionStock.buscarStock(producto);
                            if (stock == null || stock.getCantidad() < cantidad)
                                throw new StockInsuficienteException("No hay suficiente stock disponible para este producto.");

                            double subtotal = producto.getPrecioUnitario() * cantidad;
                            totalCompra += subtotal;

                            // Verificar límites del programa “Ahora 30”
                            if (producto.getDescripcion().toLowerCase().contains("celular") && totalCompra > 800000)
                                throw new Exception("El límite de compra para celulares es $800.000.");
                            if (totalCompra > 1500000)
                                throw new Exception("El monto total supera el límite permitido del programa Ahora 30.");

                            // Crear detalle y agregarlo a la factura
                            Detalle detalle = new Detalle(cantidad, producto.getPrecioUnitario() * cantidad, producto);
                            factura.agregarDetalle(detalle);

                            // Reducir stock
                            CollectionStock.reducirStock(stock, cantidad);
              
                            String seguir = InputUtil.inputString("¿Desea agregar otro producto? (s/n): ");
                            continuar = seguir.equalsIgnoreCase("s");

                        } while (continuar);

                        // Verificar límite de tarjeta antes de generar crédito
                        if (totalCompra > tarjeta.getLimiteCompra())
                            throw new Exception("El total de la compra supera el límite disponible en la tarjeta.");

                        // Crear crédito asociado a la factura
                        Credito credito = new Credito(tarjeta, factura);
                        CollectionCredito.agregarCredito(credito);

                        // Registrar factura en el sistema
                        CollectionFactura.agregarFactura(factura);

                        System.out.println("\n Venta realizada con éxito!");
                        System.out.println(factura);
                        System.out.println("Crédito generado correctamente para el cliente.");

                    } catch (UsuarioNoRegistradoException | ProductoNoEncontradoException | StockInsuficienteException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;
                }
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

                case 3: {
                    try {
                        CollectionStock.mostrarProductosDisponibles();
                    } catch (IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
			    
                case 4: {
                    try {
                        long codigo = InputUtil.inputLong("Ingrese el código del producto a consultar: ");
                        Producto producto = CollectionProducto.buscarProducto(codigo);

                        if (producto == null) throw new ProductoNoEncontradoException("No se encontró un producto con ese código.");

                        Stock stock = CollectionStock.buscarStock(producto);
                        if (stock == null) throw new StockInsuficienteException("No hay registro de stock para este producto.");

                        System.out.println("\n El producto '" + producto.getDescripcion() +
                        "' tiene un stock de " + stock.getCantidad() + " unidades.");

                    } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                        System.out.println("Error " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;
                }

                case 5 : {
                    long dni = InputUtil.inputLong("Ingrese el dni del cliente: ");
                    try{
                        Cliente cliente = CollectionCliente.buscarCliente(dni);
                        if( cliente == null ) throw new UsuarioNoRegistradoException("El usuario no existe.");
                        
                        TarjetaCredito tc = CollectionTarjetaCredito.buscarTarjetaCreditoPorCliente(cliente);
                        if( tc == null ) throw new UsuarioSinTarjetaCreditoException("El usuario no tiene tarjeta de credito asociada");

                        CollectionCredito.buscarCreditoDeTarjetaEsp(tc.getNumero());

                    } catch (UsuarioNoRegistradoException | UsuarioSinTarjetaCreditoException e) {
                        System.out.println(e.getMessage());
                    } 
                      catch (Exception e) {
                        System.out.println("Error:"+e.getMessage());
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
