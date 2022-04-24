package com.svalero.zapatillas.servlet;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.UsuarioNoFuncionaException;
import com.svalero.zapatillas.exception.ZapatillaNoExisteException;
import com.svalero.zapatillas.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/modificarusuario")
public class ModificarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int idusuario = Integer.parseInt(request.getParameter("idusuario"));

        String nuevoUsuario = request.getParameter("nuevousuario");
        String nuevoPassword = request.getParameter("nuevopassword");
        String nuevoNombre = request.getParameter("nuevonombre");
        String nuevoApellido = request.getParameter("nuevoapellido");
        String nuevoDni = request.getParameter("nuevodni");
        LocalDate nuevaFechaNacimiento = DateUtils.parseLocalDate(request.getParameter("nuevafechanacimiento"));
        int nuevotelefono = Integer.parseInt(request.getParameter("nuevotelefono"));

        Usuario usuarioNuevo = new Usuario(nuevoUsuario,nuevoPassword,nuevoNombre,nuevoApellido,nuevoDni,nuevaFechaNacimiento,nuevotelefono);


        BaseDatos baseDatos = new BaseDatos();
        UsuarioDao usuarioDao = new UsuarioDao(baseDatos.getConnection());
        try {
            boolean modificado = usuarioDao.modificar(idusuario, usuarioNuevo);
            if (modificado)
            out.println("<p style='color:green'>Usuario modificado correctamente</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red>Se ha producido un error al leer los datos</p>");
            sqle.printStackTrace();
        } catch (UsuarioNoFuncionaException unfe) {
            out.println("<p style='color:red'>Ya existe una zapatilla de ese modelo con ese número</p>");
        }
    }
}
