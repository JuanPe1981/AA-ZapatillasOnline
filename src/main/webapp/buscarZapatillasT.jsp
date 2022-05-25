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
                        $.post("buscarnumerozapatilla", formValue, function(data) {
                            $("#result").html(data);
                        });
                });
            });
        </script>

        <div class ="container">
            <h2>Buscar modelo de zapatilla</h2>
            <form action="buscarnumerozapatilla" method="post">
                <div class="mb-2">
                    <label for="text" class="form-label">Numero:</label>
                    <input name="numero" type="text" class="form-control w-25" id="numero">
                </div>
                    <button type="submit" class="btn btn-primary" value="Buscar">Buscar</button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>