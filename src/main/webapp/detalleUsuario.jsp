<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        h1 {
            color: #333;
        }
        .form-container {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input {
            padding: 10px;
            width: 300px;
            margin-bottom: 10px;
        }
        .button-container {
            margin-top: 20px;
            display: flex;
            gap: 10px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .save-button {
            background-color: #007bff;
            color: white;
        }
        .save-button:hover {
            background-color: #0056b3;
        }
        .back-button {
            background-color: #dc3545;
            color: white;
        }
        .back-button:hover {
            background-color: #a71d2a;
        }
        .message {
            margin: 20px 0;
            padding: 10px;
            border-radius: 5px;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
        }
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
    <h1>Detalle del Usuario</h1>

    <%-- Mensajes dinámicos --%>
    <%
        String message = request.getParameter("message");
        String error = request.getParameter("error");
        if (message != null) {
    %>
        <div class="message success"><%= message %></div>
    <%
        } else if (error != null) {
    %>
        <div class="message error"><%= error %></div>
    <%
        }
    %>

    <form method="post" action="detalleUsuario">
        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
        <div class="form-container">
            <label>Nombre Completo:</label>
            <input type="text" value="<%= request.getAttribute("nombre") %>" disabled>

            <label>Edad:</label>
            <input type="text" value="<%= request.getAttribute("edad") %>" disabled>

            <label>Dirección:</label>
            <input type="text" value="<%= request.getAttribute("direccion") %>" disabled>

            <label>Teléfono:</label>
            <input type="text" value="<%= request.getAttribute("telefono") %>" disabled>

            <label>Fecha de Expiración:</label>
            <input type="date" name="fechaExpiracion" value="<%= request.getAttribute("fechaExpiracion") %>">

            <div class="button-container">
                <button type="submit" class="save-button">Guardar Cambios</button>
                <button type="button" class="back-button" onclick="location.href='listarUsuarios'">Volver a la Lista</button>
            </div>
        </div>
    </form>
</body>
</html>
