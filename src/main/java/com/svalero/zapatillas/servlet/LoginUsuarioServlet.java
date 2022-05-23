package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.domain.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login-usuario")
public class LoginUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombreusuario = request.getParameter("username");
        String password = request.getParameter("password");

        BaseDatos baseDatos = new BaseDatos();
        UsuarioDao usuarioDao = new UsuarioDao(baseDatos.getConnection());
        try {
            Optional<Usuario> usuario = usuarioDao.login(nombreusuario,password);
            if (usuario.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUsuario", usuario.get());
                System.out.println("Sesion Iniciada");
                response.sendRedirect("menuUsuario.jsp");
            } else {
                response.sendRedirect("loginerror.jsp");
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }


    }
}
