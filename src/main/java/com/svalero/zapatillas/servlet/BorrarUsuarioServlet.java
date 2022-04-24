package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.exception.UsuarioNoFuncionaException;
import com.svalero.zapatillas.exception.ZapatillaNoExisteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/borrarusuario")
public class BorrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int idUsuario = Integer.parseInt(request.getParameter("idusuario"));


        BaseDatos baseDatos = new BaseDatos();
        UsuarioDao usuarioDao = new UsuarioDao(baseDatos.getConnection());
        try {
            usuarioDao.borrar(idUsuario);
            out.println("<p style='color:green'>Zapatilla registrada correctamente</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        } catch (UsuarioNoFuncionaException znee) {
            out.println("<p style='color:red'>Ese modelo de zapatilla no existe</p>");
            znee.printStackTrace();
        }
    }
}
