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
                        $.post("addzapatilla", formValue, function(data) {
                            $("#result").html(data);
                        });
                });
            });
        </script>

        <div class = "container">
            <h2>Registrar nuevo modelo de zapatilla</h2>
            <form action="modificarzapatilla" method="post">
            Modelo a modificar:
            <input type="text" name="modelo"/><br/>
            Número a modificar:
            <input type="text" name="numero"/><br/>

            Modelo nuevo:
            <input type="text" name="nuevomodelo"/><br/>
            Color nuevo:
            <input type="text" name="nuevocolor"/><br/>
            Número nuevo:
            <input type="text" name="nuevonumero"/><br/>
            Precio nuevo:
            <input type="text" name="nuevoprecio"/><br/>
            <input type="submit" value="Modificar"/>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>