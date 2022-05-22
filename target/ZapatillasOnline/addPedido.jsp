<html>
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
            <h2>Hacer pedido</h2>
            <form action="addpedido" method="post">
                <div class="mb-3">
                    <label for="usuario" class="form-label">Usuario:</label>
                    <input name="usuario" type="text" class="form-control" id="usuario">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input name="password" type="text" class="form-control" id="password">
                </div>
                <div class="mb-3">
                    <label for="modelos" class="form-label">Modelos:</label>
                    <input name="modelos" type="text" class="form-control" id="modelos">
                </div>
                <input type="submit" value="Hacer Pedido"/>
            </form>
        <div id="result"></div>
        </div>
    </body>
</html>