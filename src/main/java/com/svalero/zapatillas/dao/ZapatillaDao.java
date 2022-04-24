package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.ZapatillaNoExisteException;
import com.svalero.zapatillas.exception.ZapatillaYaExisteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ZapatillaDao {

    private Connection connection;

    public ZapatillaDao(Connection connection){
        this.connection = connection;
    }

    public void añadir(Zapatilla zapatilla) throws SQLException, ZapatillaYaExisteException {
        if (existeZapatilla(zapatilla.getModelo(),zapatilla.getColor(), zapatilla.getNumero()))
            throw new ZapatillaYaExisteException();

        String sql = "INSERT INTO Zapatillas (MODELO, COLOR, NUMERO, PRECIO) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, zapatilla.getModelo());
        statement.setString(2, zapatilla.getColor());
        statement.setInt(3, zapatilla.getNumero());
        statement.setFloat(4, zapatilla.getPrecio());
        statement.executeUpdate();
    }

    public boolean borrar(String modelo, int numero) throws SQLException {
        String sql = "DELETE FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ? AND ZAP.NUMERO = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelo);
        statement.setInt(2, numero);
        int ejecuciones = statement.executeUpdate();

        return ejecuciones == 1;
    }

    public boolean modificar(String modelo, int numero, Zapatilla zapatilla) throws SQLException {
        String sql ="UPDATE ZAPATILLAS ZAP SET ZAP.MODELO = ?, ZAP.COLOR = ?, ZAP.NUMERO = ?, ZAP.PRECIO = ? WHERE ZAP.MODELO = ? AND ZAP.NUMERO = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, zapatilla.getModelo());
        statement.setString(2, zapatilla.getColor());
        statement.setInt(3, zapatilla.getNumero());
        statement.setFloat(4,zapatilla.getPrecio());
        statement.setString(5, modelo);
        statement.setInt(6, numero);

        int ejecuciones = statement.executeUpdate();
        return ejecuciones == 1;
    }

    public ArrayList<Zapatilla> verTodo () throws SQLException{
        String sql = "SELECT * FROM ZAPATILLAS";

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Zapatilla zapatilla = new Zapatilla();
            zapatilla.setIdZapatilla(resultSet.getInt("idzapatilla"));
            zapatilla.setModelo(resultSet.getString("modelo"));
            zapatilla.setColor(resultSet.getString("color"));
            zapatilla.setNumero(resultSet.getInt("numero"));
            zapatilla.setPrecio(resultSet.getFloat("precio"));
            zapatillas.add(zapatilla);
        }

        return zapatillas;
    }

    public ArrayList<Zapatilla> buscarModelo (String modelo) throws SQLException, ZapatillaNoExisteException {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ?";

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelo);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Zapatilla zapatilla = new Zapatilla();
            zapatilla.setIdZapatilla(resultSet.getInt("idzapatilla"));
            zapatilla.setModelo(resultSet.getString("modelo"));
            zapatilla.setColor(resultSet.getString("color"));
            zapatilla.setNumero(resultSet.getInt("numero"));
            zapatilla.setPrecio(resultSet.getFloat("precio"));
            zapatillas.add(zapatilla);
        }

        return zapatillas;
    }


    public Zapatilla buscarZapatillaColorNumero (String modelo, String color, int numero) {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ? AND ZAP.COLOR = ? AND ZAP.NUMERO = ?";
        Zapatilla zapatilla = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            statement.setString(2, color);
            statement.setInt(3, numero);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                zapatilla = new Zapatilla();
                zapatilla.setModelo(resultSet.getString("modelo"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setNumero(resultSet.getInt("numero"));
                zapatilla.setPrecio(resultSet.getFloat("precio"));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo conectar con la base de datos. Intentelo mas tarde.");
            sqle.printStackTrace();
        }

        return zapatilla;
    }

    public Optional<Zapatilla> buscarZapatillaModelo (String modelo) {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ?";
        Zapatilla zapatilla = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                zapatilla = new Zapatilla();
                zapatilla.setIdZapatilla(resultSet.getInt("idzapatilla"));
                zapatilla.setModelo(resultSet.getString("modelo"));
                zapatilla.setColor(resultSet.getString("color"));
                zapatilla.setNumero(resultSet.getInt("numero"));
                zapatilla.setPrecio(resultSet.getFloat("precio"));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo conectar con la base de datos. Intentelo mas tarde.");
            sqle.printStackTrace();
        }

        return Optional.ofNullable(zapatilla);
    }

    public boolean existeZapatilla(String modelo, String color, int numero) throws SQLException {
        Zapatilla zapatilla = buscarZapatillaColorNumero(modelo, color, numero);
        return zapatilla != null;
    }

    public boolean noExisteZapatilla (String modelo) throws SQLException {
        Optional<Zapatilla> zapatilla = buscarZapatillaModelo(modelo);
        return !zapatilla.isPresent();
    }
}
