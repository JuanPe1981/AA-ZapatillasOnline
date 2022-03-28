package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;

public class ZapatillaDao {

    private Connection connection;

    public ZapatillaDao(Connection connection){
        this.connection = connection;
    }

    public Zapatilla añadir(Zapatilla zapatilla) {
        return null;
    }

    public Zapatilla borrar(String modelo) {
        return null;

    }

    public Zapatilla modificar(String modelo) {
        return null;
    }

    public Zapatilla verTodo () {
        return null;
    }

    public Zapatilla buscarModelo (String modelo) {
        return null;
    }

}
