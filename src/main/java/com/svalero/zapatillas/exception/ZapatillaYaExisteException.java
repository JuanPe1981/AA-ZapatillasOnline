package com.svalero.zapatillas.exception;

public class ZapatillaYaExisteException extends Exception {

    public ZapatillaYaExisteException (String mensaje) {
        super(mensaje);
    }

    public ZapatillaYaExisteException() {
        super("Ese modelo de Zapatilla, en ese color y con ese número ya existe");
    }

}
