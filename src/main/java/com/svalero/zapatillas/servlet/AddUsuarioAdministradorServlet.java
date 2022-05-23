package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioAdministradorDao;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.domain.UsuarioAdministrador;
import com.svalero.zapatillas.exception.UsuarioYaExisteException;
import com.svalero.zapatillas.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/addusuario-administrador")
public class AddUsuarioAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        LocalDate nacimiento = DateUtils.parseLocalDate(request.getParameter("nacimiento"));
        int telefono = Integer.parseInt(request.getParameter("telefono"));

        UsuarioAdministrador usuarioAdministradorNuevo = new UsuarioAdministrador(usuario,password,nombre,apellido,dni,nacimiento,telefono);

        BaseDatos baseDatos = new BaseDatos();
        UsuarioAdministradorDao usuarioAdministradorDao = new UsuarioAdministradorDao(baseDatos.getConnection());

        try {
            usuarioAdministradorDao.añadirUsuarioAdministrador(usuarioAdministradorNuevo);
            out.println("<p style='color:green'>Nuevo usuario administrador registrado correctamente</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        }
    }
}
