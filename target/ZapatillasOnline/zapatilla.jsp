<%@ page import = "com.svalero.zapatillas.dao.BaseDatos" %>
<%@ page import = "com.svalero.zapatillas.dao.ZapatillaDao" %>
<%@ page import = "com.svalero.zapatillas.domain.Zapatilla" %>
<%@ page import="java.util.Optional" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "com.svalero.zapatillas.domain.Usuario"%>
<%@ page import = "com.svalero.zapatillas.domain.UsuarioAdministrador"%>

<%
    Usuario currentUsuario = (Usuario) session.getAttribute("currentUsuario");
    UsuarioAdministrador currentAdministrador = (UsuarioAdministrador) session.getAttribute("currentAdministrador");
%>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <%
        String idZapatilla = request.getParameter("id");
        BaseDatos bd = new BaseDatos();
        ZapatillaDao zapatillaDao = new ZapatillaDao(bd.getConnection());
        Zapatilla zapatilla = null;
        try {
            Optional<Zapatilla> optionalZapatilla = zapatillaDao.buscarZapatillaId(Integer.parseInt(idZapatilla));
            zapatilla = optionalZapatilla.get();

    %>
    <div class="container">
        <div class="card text-center">
          <div class="card-header">
            Detalles de la Zapatilla
          </div>
          <div class="card-body">
            <h5 class="card-title"><%= zapatilla.getModelo() %></h5>
            <p class="card-text">Color: <strong><%= zapatilla.getColor() %></strong></p>
            <p class="card-text">Precio: <strong><%= zapatilla.getPrecio() %></strong></p>
            <%
                if (currentUsuario != null) {
            %>
            <a href="comprar?id=<%= zapatilla.getIdZapatilla() %>" class="btn btn-primary">Comprar</a>
            <%
                }
            %>
          </div>
          <div class="card-footer text-muted">
            Numero:  <strong><%= zapatilla.getNumero() %></strong>
          </div>
        </div>
    </div>
    <%
        } catch (SQLException sqle) {
    %>
        <div class='alert alert-danger' role='alert'>Se ha producido al cargar los datos de la zapatilla</div>
    <%
        }
    %>
</body>
</html>