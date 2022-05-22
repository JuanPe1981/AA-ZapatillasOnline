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

@WebServlet("/borrarzapatilla")
public class BorrarZapatillaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String modelo = request.getParameter("modelo");
        int numero = Integer.parseInt(request.getParameter("numero"));


        BaseDatos baseDatos = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        try {
            zapatillaDao.borrar(modelo, numero);
            out.println("<p style='color:green'>Zapatilla borrada correctamente</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        } catch (ZapatillaNoExisteException znee) {
            out.println("<p style='color:red'>Ese modelo de zapatilla no existe</p>");
            znee.printStackTrace();
        }
    }
}
