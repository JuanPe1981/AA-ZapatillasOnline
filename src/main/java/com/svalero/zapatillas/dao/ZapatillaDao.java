package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Zapatilla;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Zapatilla> verTodo (){
        String sql = "SELECT * FROM ZAPATILLA";
        ArrayList<Zapatilla> zapatillas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Zapatilla zapatilla = new Zapatilla();
                zapatilla.setModelo(resultSet.getString("modelo"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setNumero(resultSet.getInt("numero"));
                zapatilla.setPrecio(resultSet.getFloat("precio"));
                zapatillas.add(zapatilla);
            }
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }
        return zapatillas;
    }

    public ArrayList<Zapatilla> buscarModelo (String modelo) {
        String sql = "SELECT * FROM ZAPATILLA WHERE MODELO = ?";
        Zapatilla zapatilla = null;

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                zapatilla = new Zapatilla();
                zapatilla.setModelo(resultSet.getString("modelo"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setNumero(resultSet.getInt("numero"));
                zapatilla.setPrecio(resultSet.getFloat("precio"));
                zapatillas.add(zapatilla);
            }
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }
        return zapatillas;
    }

}
