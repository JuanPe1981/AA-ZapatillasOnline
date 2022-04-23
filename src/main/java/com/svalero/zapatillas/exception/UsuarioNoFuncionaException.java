package com.svalero.zapatillas.exception;

public class UsuarioNoFuncionaException extends Exception {

    public UsuarioNoFuncionaException(String message) {
        super(message);
    }

    public UsuarioNoFuncionaException(){
        super("Usuario y contraseñas incorrectos");
    }
}
