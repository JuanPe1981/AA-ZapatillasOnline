package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioAdministradorDao;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.UsuarioAdministrador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/buscar-usuario-administrador")
public class BuscarUsuarioAdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchText = request.getParameter("searchtext");

        BaseDatos baseDatos = new BaseDatos();
        UsuarioAdministradorDao usuarioAdministradorDao = new UsuarioAdministradorDao(baseDatos.getConnection());
        try {
            ArrayList<UsuarioAdministrador> usuariosAdministrador = usuarioAdministradorDao.buscarTodo(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group'>");
            for (UsuarioAdministrador usuarioAdministrador : usuariosAdministrador) {
                result.append("<li class='list-group-item'>").append(usuarioAdministrador.getUsuario()).append("</li>");
            }
            result.append("</ul>");
            out.println(result);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error durante la búsqueda</div>");
            sqle.printStackTrace();
        }
    }
}
