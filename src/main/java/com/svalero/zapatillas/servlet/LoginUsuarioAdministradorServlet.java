package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioAdministradorDao;
import com.svalero.zapatillas.domain.UsuarioAdministrador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login-administrador")
public class LoginUsuarioAdministradorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombreusuario = request.getParameter("username");
        String password = request.getParameter("password");

        BaseDatos baseDatos = new BaseDatos();
        UsuarioAdministradorDao usuarioAdministradorDao = new UsuarioAdministradorDao(baseDatos.getConnection());
        try {
            Optional<UsuarioAdministrador> usuarioAdministrador = usuarioAdministradorDao.login(nombreusuario,password);
            if (usuarioAdministrador.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentAdministrador", usuarioAdministrador.get());
                System.out.println("Sesion Iniciada");
                response.sendRedirect("menuAdmin.jsp");
            } else {
                response.sendRedirect("loginerror.jsp");
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
