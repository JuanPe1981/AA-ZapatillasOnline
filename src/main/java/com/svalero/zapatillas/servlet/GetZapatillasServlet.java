package com.svalero.zapatillas.servlet;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/catalogo")
public class GetZapatillasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        BaseDatos baseDatos = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        try {
            out.println("<ul>");
            List<Zapatilla> zapatillas = zapatillaDao.verTodo();
            for (Zapatilla zapatilla : zapatillas) {
                out.println("<li><p><a href='zapatilla.jsp?id=" + zapatilla.getIdZapatilla() + "'> Modelo: " + zapatilla.getModelo() + "/  Color: " + zapatilla.getColor() + "</a></p></li>");
            }
            out.println("</ul>");
            baseDatos.desconectar();
        }catch (SQLException sqle) {
            out.println("<p style='color:red'>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        }
    }


}
