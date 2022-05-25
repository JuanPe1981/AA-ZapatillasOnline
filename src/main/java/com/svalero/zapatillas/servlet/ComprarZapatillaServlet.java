package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.PedidoDao;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Pedido;
import com.svalero.zapatillas.domain.Usuario;
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
import java.util.Optional;


@WebServlet("/comprar")
public class ComprarZapatillaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Usuario currentUsuario = (Usuario) request.getSession().getAttribute("currentUsuario");
        if (currentUsuario == null) {
            response.sendRedirect("accesonopermitido.jsp");
        }

        String zapatillaId = request.getParameter("id");
        BaseDatos baseDatos = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        try {
            Optional<Zapatilla> zapatilla = zapatillaDao.buscarZapatillaId(Integer.parseInt(zapatillaId));
            if (!zapatilla.isPresent()) {
                out.println("No se encuentra el modelo de zapatilla");
            }

            Pedido pedido = new Pedido();

            PedidoDao pedidoDao = new PedidoDao(baseDatos.getConnection());
            pedidoDao.addPedido(currentUsuario, List.of(zapatilla.get()));
            out.println("<p style='color:green'>Pedido realizado correctamente</p>");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
