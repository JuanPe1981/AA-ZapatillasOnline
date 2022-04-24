<%
    int id = Integer.parseInt(request.getParameter("id"));
    out.println("<p>Consultando la zapatilla con id " + id);
    // Conecto con la base de datos
    // ....
%>
<p>Datos de Zapatilla<%= id %></p>