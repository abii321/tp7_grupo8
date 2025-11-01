package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp7.dominio.Detalle;
import ar.edu.unju.escmi.tp7.dominio.Factura;

public class CreditoTest {

    @Test
    public void testMontoTotalNoSupereLimite() {
        double montoPermitido = 1_500_000;
        Factura factura = crearFactura();
        double montoObtenido = factura.calcularTotal();
        assertTrue(montoObtenido <= montoPermitido,
            "❌ El monto total no debe superar $1.500.000");
    }

    @Test
    public void testSumaDetallesIgualTotalFactura() {
        Factura factura = crearFactura();
        double sumaDetalles = factura.getDetalles()
            .stream()
            .mapToDouble(Detalle::getImporte)
            .sum();
        double totalFactura = factura.calcularTotal();
        assertEquals(sumaDetalles, totalFactura, 0.001,
            "❌ La suma de los detalles no coincide con el total de la factura");
    }

    @Test
    public void testMontoNoSuperaLimites() {
        double limiteGeneral = 1_500_000;
        double limiteTarjeta = 800_000;
        Factura factura = crearFactura();
        double montoFactura = factura.calcularTotal();
        assertTrue(montoFactura <= limiteGeneral && montoFactura <= limiteTarjeta,
            "❌ El monto total supera el límite general o el disponible en la tarjeta");
    }

    private Factura crearFactura() {
        Factura factura = new Factura();
        factura.setDetalles(crearDetalles());
        return factura;
    }

    private List<Detalle> crearDetalles() {
        List<Detalle> detalles = new ArrayList<>();

        Detalle d1 = new Detalle();
        d1.setImporte(150_000);
        Detalle d2 = new Detalle();
        d2.setImporte(200_000);
        Detalle d3 = new Detalle();
        d3.setImporte(300_000);

        detalles.add(d1);
        detalles.add(d2);
        detalles.add(d3);

        return detalles;
    }
}
