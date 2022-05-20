<html lang="es">
    <%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
    %>
    <head>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <div class = "container">
            <h2>Menú Administrador</h2>
            <h3>Opciones de catalogo</h3>
            <p><a href="catalogo">Ver catálogo</a></p>
            <p><a href="buscarZapatillasM.jsp">Buscar Zapatilla por modelo</a></p>
            <p><a href="buscarZapatillasT.jsp">Buscar Zapatilla por talla</a></p>
            <p><a href="buscarZapatillasMCT.jsp">Buscar por modelo, color y talla</a></p>
            <p><a href="addZapatilla.jsp">Añadir Zapatilla al catalogo</a></p>
            <p><a href="borrarZapatilla.jsp">Borrar Zapatilla del catalogo</a></p>
            <p><a href="modificarZapatilla.jsp">Modificar Zapatilla del catalogo</a></p>
            <h3>Opciones de gestión usuarios</h3>
            <p><a href="addUsuario.jsp">Añadir Usuario nuevo</a></p>
            <p><a href="modificarUsuario.jsp">Modificar Usuario</a></p>
            <p><a href="borrarUsuario.jsp">Eliminar Usuario</a></p>
             <h3>Opciones de gestión usuarios administradores</h3>
            <p><a href="addUsuarioAdministrador.jsp">Añadir Usuario Administrador nuevo</a></p>
            <p><a href="modificarUsuarioAdministrador.jsp">Modificar Usuario</a></p>
            <p><a href="eliminarUsuarioAdministrador.jsp">Modificar Usuario</a></p>
            <h3>Gestión de pedidos</h3>
            <p><a href="pedidoZapatillas.jsp">Hacer pedido</a></p>
            <p><a href="pagarPedido.jsp">Marcar pedido como pagado</a></p>
            <p><a href="consultarTodosPedidos.jsp">Consultar todos los pedidos de todos los usuarios</a></p>
            <p><a href="consultarPedidosEntreFechas.jsp">Consultar pedidos entre fechas de todos los usuarios</a></p>
        </div>
    </body>
</html>