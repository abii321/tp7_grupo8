package ar.edu.unju.escmi.tp7.exceptions;

public class UsuarioSinTarjetaCreditoException extends Exception{
    private static final long serialVersionUID = 1L;

    public UsuarioSinTarjetaCreditoException(String message) {
        super(message);
    }
}
