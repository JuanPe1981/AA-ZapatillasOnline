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
        <div class ="container">
            <h2>Registrar nuevo modelo de zapatilla</h2>
            <form action="addzapatilla" method="post">
                <div class="mb-3">
                    <label for="modelo" class="form-label">Modelo:</label>
                    <input name="modelo" type="text" class="form-control" id="modelo">
                </div>
                <div class="mb-3">
                    <label for="color" class="form-label">Color:</label>
                    <input name="color" type="text" class="form-control" id="color">
                </div>
                <div class="mb-3">
                    <label for="numero" class="form-label">Número:</label>
                    <input name="numero" type="text" class="form-control" id="nombre">
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label">Precio:</label>
                    <input name="precio" type="text" class="form-control" id="precio">
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>