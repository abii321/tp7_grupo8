package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ar.edu.unju.escmi.tp7.dominio.Producto;

public class StockTest {

    @Test
    public void testReducirStockDisminuyeCantidad() {
        Producto producto = new Producto();
        producto.setStock(50);

        producto.reducirStock(10);

        assertEquals(40, producto.getStock(),
            "❌ El stock no se redujo correctamente tras la venta");
    }

    @Test
    public void testReducirStockNoPermiteNegativos() {
        Producto producto = new Producto();
        producto.setStock(20);

        producto.reducirStock(25); // intenta vender más de lo disponible

        assertEquals(20, producto.getStock(),
            "❌ No debería permitir reducir más stock del disponible");
    }

    @Test
    public void testReducirStockNoPermiteCeroONegativo() {
        Producto producto = new Producto();
        producto.setStock(15);

        producto.reducirStock(0);
        producto.reducirStock(-3);

        assertEquals(15, producto.getStock(),
            "❌ No debería modificar el stock si la cantidad es inválida");
    }
}
