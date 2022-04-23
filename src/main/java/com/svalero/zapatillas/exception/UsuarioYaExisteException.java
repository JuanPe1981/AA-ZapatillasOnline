package com.svalero.zapatillas.exception;

public class UsuarioYaExisteException extends Exception {

    public UsuarioYaExisteException (String mensaje) {
        super(mensaje);
    }

    public UsuarioYaExisteException() {
        super("Ese usuario ya existe");
    }
}
