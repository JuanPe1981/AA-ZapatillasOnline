package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.UsuarioAdministrador;
import com.svalero.zapatillas.exception.UsuarioYaExisteException;
import com.svalero.zapatillas.util.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UsuarioAdministradorDao {

    private Connection connection;

    public UsuarioAdministradorDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<UsuarioAdministrador> login(String nombreUsuario, String password) throws SQLException {
        String sql = "SELECT * FROM USUARIOS_ADMINISTRADOR WHERE USUARIO = ? AND CONTRASEÑA = ?";
        UsuarioAdministrador usuarioAdministrador = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreUsuario);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            usuarioAdministrador = fromResultSet(resultSet);
        }

        return Optional.ofNullable(usuarioAdministrador);
    }

    public void añadirUsuarioAdministrador(UsuarioAdministrador usuarioAdministrador) throws SQLException {
        String sql = "INSERT INTO USUARIOS_ADMINISTRADOR (USUARIO, CONTRASEÑA, NOMBRE, APELLIDO, DNI, FECHA_NACIMIENTO, TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuarioAdministrador.getUsuario());
        statement.setString(2, usuarioAdministrador.getPassword());
        statement.setString(3, usuarioAdministrador.getNombre());
        statement.setString(4, usuarioAdministrador.getApellido());
        statement.setString(5, usuarioAdministrador.getDni());
        statement.setDate(6, DateUtils.toSqlDate(usuarioAdministrador.getFechaNacimiento()));
        statement.setInt(7, usuarioAdministrador.getTelefono());
        statement.executeUpdate();
    }

    public ArrayList<UsuarioAdministrador> verTodo () throws SQLException{
        String sql = "SELECT * FROM USUARIOS_ADMINISTRADOR ORDER BY IDEMPLEADO";

        ArrayList<UsuarioAdministrador> usuarios = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            UsuarioAdministrador usuarioAdministrador = new UsuarioAdministrador();
            usuarioAdministrador.setNumeroEmpleado(resultSet.getInt("idempleado"));
            usuarioAdministrador.setUsuario(resultSet.getString("usuario"));
            usuarioAdministrador.setNombre(resultSet.getString("nombre"));
            usuarioAdministrador.setApellido(resultSet.getString("apellido"));
            usuarioAdministrador.setDni(resultSet.getString("dni"));
            usuarioAdministrador.setFechaNacimiento(DateUtils.toLocalDateFromSql(resultSet.getDate("fecha_nacimiento")));
            usuarioAdministrador.setTelefono(resultSet.getInt("telefono"));
            usuarios.add(usuarioAdministrador);
        }

        return usuarios;
    }


    public UsuarioAdministrador fromResultSet(ResultSet resultSet) throws SQLException {
        UsuarioAdministrador usuarioAdministrador = new UsuarioAdministrador();
        usuarioAdministrador.setIdUsuario(resultSet.getInt("idempleado"));
        usuarioAdministrador.setUsuario(resultSet.getString("usuario"));
        usuarioAdministrador.setNombre(resultSet.getString("nombre"));

        return usuarioAdministrador;
    }


}
