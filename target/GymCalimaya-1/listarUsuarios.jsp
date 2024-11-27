<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Map" %>
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
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
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
        }
        .back-button:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <div>
        <form method="get" action="listarUsuarios">
            <input type="text" name="filtro" placeholder="Buscar por ID o Nombre" value="${param.filtro}">
            <button type="submit">Buscar</button>
        </form>
    </div>
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
                List<Map<String, Object>> usuarios = (List<Map<String, Object>>) request.getAttribute("usuarios");
                if (usuarios != null) {
                    for (Map<String, Object> usuario : usuarios) {
                        String estado = (String) usuario.get("estado");
            %>
            <tr>
                <td><a href="detalleUsuario?id=<%= usuario.get("id") %>"><%= usuario.get("id") %></a></td>
                <td><a href="detalleUsuario?id=<%= usuario.get("id") %>"><%= usuario.get("nombre") %></a></td>
                <td><%= usuario.get("fechaExpiracion") %></td>
                <td style="color: <%= "vigente".equals(estado) ? "green" : "red" %>;">
                    <%= estado.equals("vigente") ? "Vigente" : "Vencido" %>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <div class="button-container">
        <a href="index.jsp" class="back-button">Regresar al Inicio</a>
    </div>
</body>
</html>
