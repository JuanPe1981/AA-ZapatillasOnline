<%@ page import = "com.svalero.zapatillas.dao.BaseDatos" %>
<%@ page import = "com.svalero.zapatillas.dao.ZapatillaDao" %>
<%@ page import = "com.svalero.zapatillas.domain.Zapatilla" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.util.List" %>


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
            <h2>Listado de Zapatillas</h2>
            <ul class = "list-group">
                <%
                    BaseDatos baseDatos = new BaseDatos();
                    ZapatillaDao zapatillaDao = new ZapatillaDao(baseDatos.getConnection());
                    try {
                        List<Zapatilla> zapatillas = zapatillaDao.verTodo();
                        for (Zapatilla zapatilla : zapatillas) {
                %>
                            <li class="list-group-item">
                                <a target="_blank" href="zapatilla.jsp?id=<%= zapatilla.getIdZapatilla() %>"><%= zapatilla.getModelo() %></a>
                <%
                        }
                    } catch (SQLException sqle) {
                %>
                        <div class = "alert alert-danger" role="alert">
                            Error al conectar con la base de datos
                        </div>
                <%
                    }
                %>
            </ul>
        </div>
    </body>
</html>