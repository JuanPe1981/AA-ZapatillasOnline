package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.PedidoDao;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Pedido;
import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.UsuarioNoFuncionaException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addpedido")
public class AddPedidoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nombreUsuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String modelos = request.getParameter("modelos");


        Pedido pedidonuevo = new Pedido();

        BaseDatos baseDatos = new BaseDatos();

        ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
        PedidoDao pedidoDao = new PedidoDao(baseDatos.getConnection());
        UsuarioDao usuarioDao = new UsuarioDao(baseDatos.getConnection());

        try {
            String [] zapatillaArray = modelos.split(",");
            List<Zapatilla> zapatillas = new ArrayList<>();
            for (String zapatillaModelo : zapatillaArray) {
                Zapatilla zapatilla = zapatillaDao.buscarZapatillaModelo(zapatillaModelo.trim()).get();
                zapatillas.add(zapatilla);
            }

            Usuario currenUser;

            currenUser = usuarioDao.getUsuario(nombreUsuario,password)
                    .orElseThrow(UsuarioNoFuncionaException::new);

            pedidoDao.addPedido(currenUser,zapatillas);
            out.println("<p style='color:green'>Pedido registrado correctamente</p>");
        }catch (SQLException sqle){
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        }catch (UsuarioNoFuncionaException unfe) {
            System.out.println(unfe.getMessage());
        }
    }
}
