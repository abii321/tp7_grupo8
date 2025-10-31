package ar.edu.unju.escmi.tp7.exceptions;

public class ProductoNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;

    

    public ProductoNoEncontradoException(String message) {
        super(message);
    }
}
