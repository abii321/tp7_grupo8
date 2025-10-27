package ar.edu.unju.escmi.tp7.exceptions;

public class UsuarioNoRegistradoException extends Exception {
    private static final long serialVersionUID = 1L; 
    public UsuarioNoRegistradoException(String message){
        super(message);
    }
}
