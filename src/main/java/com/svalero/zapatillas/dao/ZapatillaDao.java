package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Zapatilla;

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

    public boolean borrar(String modelo, int numero) {
        String sql = "DELETE FROM ZAPATILLA ZAP WHERE ZAP.MODELO = ? AND ZAP.NUMERO = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            statement.setInt(2, numero);
            int ejecuciones = statement.executeUpdate();

            return ejecuciones == 1;
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }

        return false;
    }

    public boolean modificar(String modelo, int numero, Zapatilla zapatilla) {
        String sql ="UPDATE ZAPATILLA ZAP SET ZAP.MODELO = ?, ZAP.COLOR = ?, ZAP.NUMERO = ?, ZAP.PRECIO = ? WHERE ZAP.MODELO = ? AND ZAP.NUMERO = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, zapatilla.getModelo());
            statement.setString(2, zapatilla.getColor());
            statement.setInt(3, zapatilla.getNumero());
            statement.setFloat(4,zapatilla.getPrecio());
            statement.setString(5, modelo);
            statement.setInt(6, numero);

            int ejecuciones = statement.executeUpdate();
            return ejecuciones == 1;
        }catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }
        return false;
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
        String sql = "SELECT * FROM ZAPATILLA ZAP WHERE ZAP.MODELO = ?";
        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
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

}
