package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp7.collections.CollectionProducto;
import ar.edu.unju.escmi.tp7.collections.CollectionStock;
import ar.edu.unju.escmi.tp7.dominio.Producto;
import ar.edu.unju.escmi.tp7.dominio.Stock;

public class StockTest {

    @BeforeEach
    public void setUp() {
        // Precargamos productos y stocks antes de cada test
        CollectionProducto.precargarProductos();
        CollectionStock.precargarStocks();
    }

    @Test
    public void testReducirStockDisminuyeCantidad() {
        // Tomamos un producto y su stock asociado
        Producto producto = CollectionProducto.productos.get(0);
        Stock stock = CollectionStock.buscarStock(producto);

        assertNotNull(stock, "No se encontró stock para el producto");

        int cantidadInicial = stock.getCantidad();
        int cantidadAReducir = 5;

        // Reducimos stock usando el método de CollectionStock
        CollectionStock.reducirStock(stock, cantidadAReducir);

        // Verificamos que haya disminuido correctamente
        assertEquals(cantidadInicial - cantidadAReducir, stock.getCantidad(),
                "El stock no se redujo correctamente");
    }

    @Test
    public void testReducirStockNoPermiteNegativos() {
        Producto producto = CollectionProducto.productos.get(1);
        Stock stock = CollectionStock.buscarStock(producto);

        assertNotNull(stock, "No se encontró stock para el producto");

        int cantidadInicial = stock.getCantidad();
        int cantidadAReducir = cantidadInicial + 10; // Intento de reducir más del disponible

        CollectionStock.reducirStock(stock, cantidadAReducir);

        // El stock no debe quedar negativo ni cambiar
        assertTrue(stock.getCantidad() >= 0, "El stock quedó negativo");
        assertEquals(cantidadInicial, stock.getCantidad(),
                "El stock debería mantenerse igual si la cantidad es inválida");
    }

    @Test
    public void testBuscarStockProductoInexistenteDevuelveNull() {
        Producto productoFalso = new Producto(9999, "Inexistente", 1000.0, "Desconocido");
        Stock resultado = CollectionStock.buscarStock(productoFalso);

        assertNull(resultado, "Se devolvió un stock para un producto inexistente");
    }
}
