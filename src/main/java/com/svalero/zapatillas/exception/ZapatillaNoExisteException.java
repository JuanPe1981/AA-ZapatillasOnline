package com.svalero.zapatillas.exception;

public class ZapatillaNoExisteException extends Exception {

    public ZapatillaNoExisteException(String menssage) {
        super(menssage);
    }

    public ZapatillaNoExisteException() {
        super("El modelo de Zapatilla no existe");
    }

}
