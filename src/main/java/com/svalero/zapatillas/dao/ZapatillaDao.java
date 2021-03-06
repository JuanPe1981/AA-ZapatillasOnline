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

    public boolean borrar(String modelo, int numero) throws SQLException, ZapatillaNoExisteException {
        String sql = "DELETE FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ? AND ZAP.NUMERO = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelo);
        statement.setInt(2, numero);
        int ejecuciones = statement.executeUpdate();

        return ejecuciones == 1;
    }

    public boolean borrarId(int idZapatilla) throws SQLException, ZapatillaNoExisteException {
        String sql = "DELETE FROM ZAPATILLAS ZAP WHERE ZAP.IDZAPATILLA = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idZapatilla);
        int ejecuciones = statement.executeUpdate();

        return ejecuciones == 1;
    }


    public boolean modificar(String modelo, int numero, Zapatilla zapatilla) throws SQLException, ZapatillaNoExisteException {
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
        String sql = "SELECT * FROM ZAPATILLAS ORDER BY MODELO";

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
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE UPPER(ZAP.MODELO) LIKE UPPER(?)";

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%"+modelo+"%");
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


    public Zapatilla buscarZapatillaColorNumero (String modelo, String color, int numero) throws SQLException, ZapatillaNoExisteException {
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

    public ArrayList<Zapatilla> buscarTodo(String searchText) throws SQLException {
        String sql = "SELECT * FROM ZAPATILLAS WHERE INSTR(UPPER(MODELO), UPPER(?)) != 0 OR INSTR(UPPER(COLOR), UPPER(?)) != 0 ORDER BY MODELO";
        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        statement.setString(2, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Zapatilla zapatilla = fromResultSet(resultSet);
            zapatillas.add(zapatilla);
        }

        return zapatillas;
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

    public ArrayList<Zapatilla> buscarModeloColorNumero (String modelo, String color, int numero) throws SQLException {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.MODELO = ? AND ZAP.COLOR = ? AND ZAP.NUMERO = ?";

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelo);
        statement.setString(2, color);
        statement.setInt(3, numero);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Zapatilla zapatilla = fromResultSet(resultSet);
            zapatillas.add(zapatilla);
        }

        return zapatillas;

    }

    public Optional<Zapatilla> buscarZapatillaId (int id) throws SQLException {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.IDZAPATILLA = ?";
        Zapatilla zapatilla = null;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            zapatilla = new Zapatilla();
            zapatilla.setIdZapatilla(resultSet.getInt("idzapatilla"));
            zapatilla.setModelo(resultSet.getString("modelo"));
            zapatilla.setColor(resultSet.getString("color"));
            zapatilla.setNumero(resultSet.getInt("numero"));
            zapatilla.setPrecio(resultSet.getFloat("precio"));
        }

        return Optional.ofNullable(zapatilla);
    }



    public ArrayList<Zapatilla> buscarNumero (int numero) throws SQLException, ZapatillaNoExisteException {
        String sql = "SELECT * FROM ZAPATILLAS ZAP WHERE ZAP.NUMERO = ?";

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, numero);
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


    public boolean existeZapatilla(String modelo, String color, int numero) throws SQLException {
    try {
        Zapatilla zapatilla = buscarZapatillaColorNumero(modelo, color, numero);
        return zapatilla != null;
        } catch (SQLException sqle) {
            System.out.println("No se ha podido encontrar la zapatilla. Intentalo de nuevo.");
        } catch (ZapatillaNoExisteException znee) {
            System.out.println("El modelo de zapatilla no está disponible");
        }
        return false;
    }

    public boolean noExisteZapatilla (String modelo) throws SQLException {
        Optional<Zapatilla> zapatilla = buscarZapatillaModelo(modelo);
        return !zapatilla.isPresent();
    }

    private Zapatilla fromResultSet(ResultSet resultSet) throws SQLException {
        Zapatilla zapatilla = new Zapatilla();
        zapatilla.setIdZapatilla(resultSet.getInt("idzapatilla"));
        zapatilla.setModelo(resultSet.getString("modelo"));
        zapatilla.setColor(resultSet.getString("color"));
        zapatilla.setNumero(resultSet.getInt("numero"));
        zapatilla.setPrecio(resultSet.getFloat("precio"));
        return zapatilla;
    }
}
