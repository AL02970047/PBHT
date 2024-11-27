<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gym Calimaya</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f9;
        }
        h1 {
            color: #333;
        }
        button {
            margin: 10px 0;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .button-container {
            margin-top: 20px;
            display: flex;
            gap: 10px;
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
    <h1>Bienvenido al Gym Calimaya</h1>

    <!-- Mensajes dinámicos -->
    <%
        String message = request.getParameter("message");
        String error = request.getParameter("error");

        if (message != null) {
    %>
        <div class="message success"><%= message %></div>
    <%
        }

        if (error != null) {
    %>
        <div class="message error"><%= error %></div>
    <%
        }
    %>

    <!-- Opciones -->
    <div class="button-container">
        <button onclick="location.href='registrarUsuario.jsp'">Registrar Usuario</button>
        <button onclick="location.href='tomarAsistencia.jsp'">Tomar Asistencia</button>
        <button onclick="location.href='listarUsuarios?filtro='">Listar Usuarios</button>
    </div>
</body>
</html>
