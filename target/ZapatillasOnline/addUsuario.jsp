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
        <script type="text/javascript">
            $(document).ready(function(){
                $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("addusuario", formValue, function(data) {
                            $("#result").html(data);
                        });
                });
            });
        </script>
        <div class="container">
            <h2>Registrar Usuario nuevo</h2>
            <form action="addusuario" method="post">
            <div class="mb-3">
                <label for="usuario" class="form-label">Usuario:</label>
                <input type="text" class="form-control" name="usuario">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="text" class="form-control" name="password">
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" name="nombre">
            </div>
            <div class="mb-3">
                <label for="apellidos" class="form-label">Apellidos:</label>
                <input type="text" class="form-control" name="apellido">
            </div>
            <div class="mb-3">
                <label for="dni" class="form-label">Dni:</label>
                <input type="text" class="form-control" name="dni">
            </div>
            <div class="mb-3">
                <label for="nacimiento" class="form-label">Fecha de nacimiento:</label>
                <input type="text" class="form-control" name="nacimiento" aria-describedby="nacimientoHelp">
                <div id="nacimientoHelp" class="form-text">(dd.MM.yyyy)</div>
            </div>
            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" class="form-control" name="telefono">
            </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>