package ar.edu.unju.escmi.tp7.exceptions;

public class StockInsuficienteException extends Exception {
    private static final long serialVersionUID = 1L;

    public StockInsuficienteException(String message) {
        super(message);
    }
}
