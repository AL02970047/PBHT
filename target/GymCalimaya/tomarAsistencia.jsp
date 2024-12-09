<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tomar Asistencia</title>
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
        label, select, input {
            display: block;
            margin: 10px 0;
        }
        select {
            padding: 8px;
            font-size: 14px;
            width: 100%;
            max-width: 400px;
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
        .submit-button {
            background-color: #007bff;
            color: white;
        }
        .submit-button:hover {
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
            font-size: 16px;
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
    <h1>Tomar Asistencia</h1>

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

    <form action="registrarAsistencia" method="post">
        <label for="idUsuario">ID del Usuario:</label>
        <input type="number" id="idUsuario" name="idUsuario" required placeholder="Ingrese el ID del usuario">

        <label for="accion">Acción:</label>
        <select id="accion" name="accion" required>
            <option value="entrada">Registrar Entrada</option>
            <option value="salida">Registrar Salida</option>
        </select>

        <div class="button-container">
            <button type="submit" class="submit-button">Registrar Asistencia</button>
            <button type="button" class="back-button" onclick="location.href='index.jsp'">Regresar a Inicio</button>
        </div>
    </form>
</body>
</html>
