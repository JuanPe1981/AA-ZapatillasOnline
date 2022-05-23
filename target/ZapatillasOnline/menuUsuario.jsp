<html lang="es">
    <%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"

        import="com.svalero.zapatillas.domain.Usuario"
    %>

    <head>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <%
        Usuario currentUsuario = (Usuario) session.getAttribute("currentUsuario");
        if (currentUsuario == null) {
            response.sendRedirect("loginUsuario.jsp");
        }
    %>

    <jsp:include page = "header.jsp"/>

    <body>
        <div class = "container">
            <h2>Menú de usuario</h2>
            <ul>
            <li><a href="catalogo">Ver catálogo</a></li>
            <li><a href="buscarZapatillasM.jsp">Buscar Zapatilla por modelo</a></li>
            <li><a href="buscarZapatillasT.jsp">Buscar Zapatilla por talla</a></li>
            <li><a href="buscarZapatillasMCT.jsp">Buscar por modelo, color y talla</a></li>
            <li><a href="addPedido.jsp">Hacer pedido</a></li>
            <li><a href="pagarPedido.jsp">Pagar pedido</a></li>
            <li><a href="consultarPedidos.jsp">Consultar todos los pedidos</a></li>
            <li><a href="consultarPedidosEntreFechas.jsp">Consultar pedidos entre fechas</a></li>
            <li><a href="logout-Usuario">Cerrar sesión</a></li>
            </ul>
            <div class="alert alert-success" role="alert">
                Bienvenid@ <% if (currentUsuario != null) out.print(currentUsuario.getNombre()); %>
            </div>

        </div>
    </body>
</html>