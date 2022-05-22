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
                        $.post("modificarusuario", formValue, function(data) {
                            $("#result").html(data);
                        });
                });
            });
        </script>

        <div class = "container">
            <h2>Modificar Usuario</h2>
            <form action="modificarusuario" method="post">
            Id Usuario a Modificar:
            <input type="text" name="idusuario"/><br/>

            Usuario nuevo:
            <input type="text" name="nuevousuario"/><br/>
            Password nuevo:
            <input type="text" name="nuevopassword"/><br/>
            Nombre nuevo:
            <input type="text" name="nuevonombre"/><br/>
            Apellido nuevo:
            <input type="text" name="nuevoapellido"/><br/>
            DNI nuevo:
            <input type="text" name="nuevodni"/><br/>
            Fecha nacimiento nueva:
            <input type="text" name="nuevafechanacimiento"/><br/>
            Telefono nuevo:
            <input type="text" name="nuevotelefono"/><br/>
            <input type="submit" value="Modificar"/>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>