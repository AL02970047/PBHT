<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f9;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .no-usuarios {
            margin-top: 20px;
            padding: 10px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
        }
        .button-container {
            margin-top: 20px;
        }
        .back-button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #a71d2a; /* Un tono más oscuro de rojo */
        }
    </style>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <form method="get" action="listarUsuarios">
        <input type="text" name="filtro" placeholder="Buscar por ID o Nombre" value="${param.filtro}">
        <button type="submit">Buscar</button>
    </form>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    %>

    <%-- Mostrar tabla si hay usuarios; mensaje si no --%>
    <%
        if (usuarios != null && !usuarios.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha de Expiración</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Usuario usuario : usuarios) {
                        String estado = usuario.isVigente() ? "Vigente" : "Vencido";
                        String color = usuario.isVigente() ? "green" : "red";
                %>
                <tr>
                    <td><a href="detalleUsuario?id=<%= usuario.getId() %>"><%= usuario.getId() %></a></td>
                    <td><a href="detalleUsuario?id=<%= usuario.getId() %>"><%= usuario.getNombreCompleto() %></a></td>
                    <td><%= usuario.getFechaExpiracion() %></td>
                    <td style="color: <%= color %>;"><%= estado %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <div class="no-usuarios">No hay usuarios disponibles</div>
    <%
        }
    %>

    <%-- Botón para regresar al inicio --%>
    <div class="button-container">
        <a href="index.jsp" class="back-button">Regresar al Inicio</a>
    </div>
</body>
</html>
