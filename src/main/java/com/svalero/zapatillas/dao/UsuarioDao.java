package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.exception.UsuarioNoFuncionaException;
import com.svalero.zapatillas.exception.UsuarioYaExisteException;
import com.svalero.zapatillas.util.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public void añadirUsuario(Usuario usuario) throws SQLException, UsuarioYaExisteException {
        String sql = "INSERT INTO USUARIOS (USUARIO, PASSWORD, NOMBRE, APELLIDO, DNI, FECHA_NACIMIENTO, TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getPassword());
        statement.setString(3, usuario.getNombre());
        statement.setString(4, usuario.getApellido());
        statement.setString(5, usuario.getDni());
        statement.setDate(6, DateUtils.toSqlDate(usuario.getFechaNacimiento()));
        statement.setInt(7, usuario.getTelefono());
        statement.executeUpdate();
    }

    public Optional<Usuario> getUsuario(String nombreUsuario, String password) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?";
        Usuario usuario = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreUsuario);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(resultSet.getInt("idusuario"));
            usuario.setUsuario(resultSet.getString("usuario"));
            usuario.setPassword(resultSet.getString("password"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setDni(resultSet.getString("dni"));
            usuario.setFechaNacimiento(DateUtils.toLocalDateFromSql(resultSet.getDate("fecha_nacimiento")));
            usuario.setTelefono(resultSet.getInt("telefono"));
        }

        return Optional.ofNullable(usuario);
    }


    public Optional<Usuario> buscarUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE USUARIO = ?";
        Usuario usuario = null;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreUsuario);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(resultSet.getInt("idusuario"));
            usuario.setUsuario(resultSet.getString("usuario"));
            usuario.setPassword(resultSet.getString("password"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setDni(resultSet.getString("dni"));
            usuario.setFechaNacimiento(DateUtils.toLocalDateFromSql(resultSet.getDate("fechaNacimiento")));
            usuario.setTelefono(resultSet.getInt("telefono"));

        }

        return Optional.ofNullable(usuario);
    }

    public boolean modificar(int idUsuario, Usuario usuario) throws SQLException, UsuarioNoFuncionaException {
        String sql = "UPDATE USUARIOS USU SET USU.USUARIO = ?, USU.PASSWORD = ?, USU.NOMBRE = ?, USU.APELLIDO = ?, USU.DNI = ?, USU.FECHA_NACIMIENTO = ?, USU.TELEFONO = ? WHERE USU.IDUSUARIO = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(8, idUsuario);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getPassword());
        statement.setString(3, usuario.getNombre());
        statement.setString(4, usuario.getApellido());
        statement.setString(5, usuario.getDni());
        statement.setDate(6, DateUtils.toSqlDate(usuario.getFechaNacimiento()));
        statement.setInt(7, usuario.getTelefono());

        int ejecuciones = statement.executeUpdate();
        return ejecuciones == 1;
    }

    public boolean borrar(int idUsuario) throws SQLException, UsuarioNoFuncionaException{
        String sql = "DELETE FROM USUARIOS USU WHERE USU.IDUSUARIO = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,idUsuario);

        int ejecuciones = statement.executeUpdate();

        return ejecuciones == 1;
    }

    public boolean existeUsuario(String nombreUsuario) throws SQLException {
        Optional<Usuario> usuario = buscarUsuario(nombreUsuario);
        return usuario.isPresent();
    }





}
