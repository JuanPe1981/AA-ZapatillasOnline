package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.ZapatillaYaExisteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addzapatilla")
public class AddZapatillaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        int numero = Integer.parseInt(request.getParameter("numero"));
        float precio = Float.parseFloat(request.getParameter("precio"));

        Zapatilla zapatilla = new Zapatilla(modelo.trim(),color.trim(),numero,precio);

        BaseDatos baseDatos = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        try {
            zapatillaDao.añadir(zapatilla);
            out.println("<p style='color:green'>Zapatilla registrada correctamente</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        } catch (ZapatillaYaExisteException zyee) {
            out.println("<p style='color:red'>Ya existe una zapatilla de ese modelo con ese número</p>");
            zyee.printStackTrace();
        }
    }
}
