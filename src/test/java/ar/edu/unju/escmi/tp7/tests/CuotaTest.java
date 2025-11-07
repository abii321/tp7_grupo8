package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp7.dominio.*;

public class CuotaTest {

    private Cliente cliente;
    private TarjetaCredito tarjeta;
    private Producto producto;
    private Credito credito;
    private Factura factura;


    @BeforeEach
public void setUp() {
    cliente = new Cliente();
    cliente.setDni(12345678L);
    cliente.setNombre("Juan Pérez");

    tarjeta = new TarjetaCredito();
    tarjeta.setNumero(1234567898765432L); 
    tarjeta.setLimiteCompra(200000);
    cliente.setTarjetaCredito(tarjeta);

    producto = new Producto();
    producto.setDescripcion("TV 50 pulgadas");
    producto.setPrecioUnitario(500000);


    credito = new Credito();
    credito.setCliente(cliente);
    credito.setProducto(producto);
    credito.setTarjetaCredito(tarjeta); 

    factura = new Factura();
    factura.setCredito(credito);
    credito.setFactura(factura);

    credito.generarCuotas();
}


    @Test
    public void testListaCuotasNoEsNull() {
        List<Cuota> cuotas = credito.getCuotas();
        assertNotNull(cuotas, "La lista de cuotas no debe ser null.");
    }

    @Test
    public void testListaCuotasTiene30Cuotas() {
        List<Cuota> cuotas = credito.getCuotas();
        assertEquals(30, cuotas.size(), "La lista de cuotas debe tener exactamente 30 cuotas.");
    }

    @Test
    public void testCantidadCuotasNoSuperaLimite() {
        int cantidadMaxima = 30;
        int cantidadActual = credito.getCuotas().size();
        assertTrue(cantidadActual <= cantidadMaxima, "La cantidad de cuotas no debe superar el límite de 30.");
    }
}
