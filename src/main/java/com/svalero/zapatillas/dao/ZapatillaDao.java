package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZapatillaDao {

    private Connection connection;

    public ZapatillaDao(Connection connection){
        this.connection = connection;
    }

    public void añadir(Zapatilla zapatilla) {
        String sql = "INSERT INTO Zapatilla (MODELO, COLOR, NUMERO, PRECIO) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, zapatilla.getModelo());
            statement.setString(2, zapatilla.getColor());
            statement.setInt(3, zapatilla.getNumero());
            statement.setFloat(4, zapatilla.getPrecio());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }
    }

    public Zapatilla borrar(String modelo) {
        return null;

    }

    public Zapatilla modificar(String modelo) {
        return null;
    }

    public Zapatilla verTodo (){
        return null;
    }

    public Zapatilla buscarModelo (String modelo) {
        return null;
    }

}
