package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ar.edu.unju.escmi.tp7.dominio.Credito;
import ar.edu.unju.escmi.tp7.dominio.Cuota;

class CuotaTest {

    @Test
    void testListaDeCuotasNoEsNull() {
        Credito credito = new Credito(new ArrayList<Cuota>());
        assertNotNull(credito.getCuotas(), "La lista de cuotas no debe ser nula");
    }

    @Test
    void testListaTiene30Cuotas() {
        Credito credito = new Credito(new ArrayList<Cuota>());
        credito.generarCuotas();
        assertEquals(30, credito.getCuotas().size(), "La lista de cuotas debe tener 30 cuotas");
    }

    @Test
    void testCantidadDeCuotasNoSuperaElLimite() {
        Credito credito = new Credito(new ArrayList<Cuota>());
        credito.generarCuotas();
        assertTrue(credito.getCuotas().size() <= 30, "La cantidad de cuotas no debe superar las 30");
    }
}
