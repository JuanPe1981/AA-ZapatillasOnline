package com.svalero.zapatillas.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-usuario-administrador")
public class LoginAdministradorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreusuario = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(nombreusuario);
    }
}
