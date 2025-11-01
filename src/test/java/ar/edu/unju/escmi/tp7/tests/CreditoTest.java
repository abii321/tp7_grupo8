package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ar.edu.unju.escmi.tp7.dominio.Detalle;
import ar.edu.unju.escmi.tp7.dominio.Factura;

class CreditoTest {

    public static final int MONTO_1 = 500000;
    public static final int MONTO_2 = 2000000;
    public static final int MONTO_3 = 1000;

    @Test
    void testMontoTotalNoSuperaLimite() {
        double montoObtenido = crearFactura().calcularTotal();
        double montoPermitido = 1500000;
        assertTrue(montoObtenido <= montoPermitido, "El monto total no debe superar $1.500.000");
    }

    @Test
    void testSumaDetallesIgualTotalFactura() {
        Factura factura = crearFactura();
        double sumaDetalles = factura.getDetalles().stream().mapToDouble(Detalle::getImporte).sum();
        assertEquals(sumaDetalles, factura.calcularTotal(), 0.01, "La suma de importes debe coincidir con el total");
    }

    @Test
    void testMontoNoSuperaLimiteYTarjeta() {
        double limiteGeneral = 1500000;
        double limiteTarjeta = 1200000;
        double montoFactura = crearFactura().calcularTotal();
        assertTrue(montoFactura <= limiteGeneral && montoFactura <= limiteTarjeta,
                "El monto no debe superar el límite general ni el de la tarjeta");
    }

    private Factura crearFactura() {
        Factura factura = new Factura();
        factura.setDetalles(crearDetalles());
        return factura;
    }
//------
    private List<Detalle> crearDetalles() {
        List<Detalle> detalles = new ArrayList<>();
        Detalle d1 = new Detalle();
        d1.setImporte(MONTO_1);
        Detalle d2 = new Detalle();
        d2.setImporte(MONTO_3);
        detalles.add(d1);
        detalles.add(d2);
        return detalles;
    }
}
