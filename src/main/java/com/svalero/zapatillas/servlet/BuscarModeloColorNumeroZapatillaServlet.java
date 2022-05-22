package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.ZapatillaNoExisteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/buscarmodelocolornumerozapatilla")
public class BuscarModeloColorNumeroZapatillaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        int numero = Integer.parseInt(request.getParameter("numero"));

        BaseDatos baseDatos = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        try {
            ArrayList<Zapatilla> zapatillas = zapatillaDao.buscarModeloColorNumero(modelo, color, numero);
            for (Zapatilla zapatilla : zapatillas) {
                out.println("<li><p><a href='zapatilla.jsp?id=" + zapatilla.getIdZapatilla() + "'> Modelo: " + zapatilla.getModelo() + "/  Color: " + zapatilla.getColor() + "/ Numero: " + zapatilla.getNumero() + "/ Precio: " + zapatilla.getPrecio() +" €" + "</a></p></li>");
            }
            out.println("</ul>");
            baseDatos.desconectar();
        } catch (SQLException sqle) {
            out.println("<p style='color:red'>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        }
    }
}
