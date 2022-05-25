<%@ page import = "com.svalero.zapatillas.dao.BaseDatos" %>
<%@ page import = "com.svalero.zapatillas.dao.UsuarioDao" %>
<%@ page import = "com.svalero.zapatillas.domain.Usuario" %>
<%@ page import = "com.svalero.zapatillas.domain.Pedido" %>
<%@ page import = "com.svalero.zapatillas.dao.PedidoDao" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.svalero.zapatillas.domain.UsuarioAdministrador"%>

<%
    Usuario currentUsuario = (Usuario) session.getAttribute("currentUsuario");
    UsuarioAdministrador currentAdministrador = (UsuarioAdministrador) session.getAttribute("currentAdministrador");
%>


<html>
    <%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
    %>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h2>Listado de Pedidos</h2>
            <ul class = "list-group">
                <%
                    BaseDatos baseDatos = new BaseDatos();
                    PedidoDao pedidoDao = new PedidoDao(baseDatos.getConnection());
                        if (currentUsuario != null) {
                            try {
                                List<Pedido> pedidos = pedidoDao.getPedidoUsuario(currentUsuario);
                                for (Pedido pedido : pedidos) {
                %>
                                <li class="list-group-item">
                                    <a target="_blank" href="detallePedido.jsp?id=<%= pedido.getCode() %>"><%= pedido %></a>
               <%
                                }
                            } catch (SQLException sqle) {
                                sqle.printStackTrace();
                            }
                        } else {
                            try {
                                List<Pedido> pedidos = pedidoDao.getPedido();
                                for (Pedido pedido : pedidos) {
                %>
                                <li class="list-group-item">
                                    <a target="_blank" href="detallePedido.jsp?id=<%= pedido.getIdPedido() %>"><%= pedido %></a>
               <%
                                }
                            } catch (SQLException sqle) {
                                sqle.printStackTrace();
                            }
                        }
                %>
                    </li>
            </ul>
        </div>
    </body>
</html>