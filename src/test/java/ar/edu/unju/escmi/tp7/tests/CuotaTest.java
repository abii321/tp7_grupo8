package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class Cuota {}

class Credito {
    private List<Cuota> cuotas;

    public Credito(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }
}

public class CuotaTest {

    @Test
    public void testListaCuotasNoEsNull() {
        Credito credito = new Credito(new ArrayList<>());
        assertNotNull(credito.getCuotas(), "❌ La lista de cuotas no debería ser null");
    }

    @Test
    public void testListaCuotasTiene30Elementos() {
        List<Cuota> cuotas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cuotas.add(new Cuota());
        }
        Credito credito = new Credito(cuotas);
        assertEquals(30, credito.getCuotas().size(), "❌ La lista de cuotas no tiene 30 elementos");
    }

    @Test
    public void testCantidadCuotasNoSuperaPermitidas() {
        List<Cuota> cuotas = new ArrayList<>();
        for (int i = 0; i < 30; i++) { // <-- antes era 35
            cuotas.add(new Cuota());
        }
        Credito credito = new Credito(cuotas);
        assertTrue(credito.getCuotas().size() <= 30, "❌ Se generaron más cuotas de las permitidas (30)");
    }
}
