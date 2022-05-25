<html>
    <%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
    %>
    <%@ page import = "com.svalero.zapatillas.domain.UsuarioAdministrador"%>
    <%@ page import = "com.svalero.zapatillas.dao.BaseDatos" %>
    <%@ page import = "com.svalero.zapatillas.dao.ZapatillaDao" %>
    <%@ page import = "com.svalero.zapatillas.domain.Zapatilla" %>
    <%@ page import="java.util.Optional" %>
    <%@ page import="java.sql.SQLException" %>

    <%
        UsuarioAdministrador currentAdministrador = (UsuarioAdministrador) session.getAttribute("currentAdministrador");
        if (currentAdministrador == null) {
            response.sendRedirect("accesonopermitido.jsp");
        }

        String zapatillaId = request.getParameter("id");
        Zapatilla zapatilla = null;
        if (zapatillaId != null) {
            BaseDatos baseDatos = new BaseDatos();
            ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
            try {
                Optional<Zapatilla> optionalZapatilla = zapatillaDao.buscarZapatillaId(Integer.parseInt(zapatillaId));
                zapatilla = optionalZapatilla.get();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    %>

    <head>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function(){
                $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("modificarzapatilla", formValue, function(data) {
                            $("#result").html(data);
                        });
                });
            });
        </script>

        <div class = "container">
            <h2>Registrar nuevo modelo de zapatilla</h2>
            <form action="modificarzapatilla" method="post">
                <div class="mb-2">
                    <label for="text" class="form-label">Modelo Actual:</label>
                    <input name="modelo" type="text" class="form-control w-25" id="modelo" value="<% if (zapatilla != null) out.print (zapatilla.getModelo()); %>">
                </div>
                <div class="mb-2">
                    <label for="text" class="form-label">Numero Actual:</label>
                    <input name="numero" type="text" class="form-control w-25" id="numero" value="<% if (zapatilla != null) out.print (zapatilla.getNumero()); %>">
                </div>
                <br></br>
                <div class="mb-2">
                    <label for="text" class="form-label">Modelo Nuevo:</label>
                    <input name="nuevomodelo" type="text" class="form-control w-25" id="nuevomodelo">
                </div>
                <div class="mb-2">
                    <label for="text" class="form-label">Color Nuevo:</label>
                    <input name="nuevocolor" type="text" class="form-control w-25" id="nuevocolor">
                </div>
                <div class="mb-2">
                    <label for="text" class="form-label">Numero nuevo:</label>
                    <input name="nuevonumero" type="text" class="form-control w-25" id="nuevonumero">
                </div>
                <div class="mb-2">
                    <label for="text" class="form-label">Precio nuevo:</label>
                    <input name="nuevoprecio" type="text" class="form-control w-25" id="nuevoprecio">
                </div>
                    <button type="submit" class="btn btn-primary" value="Modificar">Modificar</button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>