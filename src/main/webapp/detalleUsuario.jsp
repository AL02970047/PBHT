<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Asistencia" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
            flex-wrap: wrap;
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
        .view-asistencias-button {
            background-color: #28a745;
            color: white;
        }
        .view-asistencias-button:hover {
            background-color: #218838;
        }
        .reportes-button {
            background-color: #17a2b8;
            color: white;
        }
        .reportes-button:hover {
            background-color: #117a8b;
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
        table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 100%;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        table th {
            background-color: #007bff;
            color: #fff;
        }
    </style>
    <script>
        function toggleAsistencias() {
            var container = document.getElementById('asistenciasContainer');
            if (container.style.display === 'none' || container.style.display === '') {
                container.style.display = 'block';
            } else {
                container.style.display = 'none';
            }
        }
    </script>
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
            <input type="text" name="nombreCompleto" value="<%= request.getAttribute("nombre") %>">

            <label>Edad:</label>
            <input type="number" name="edad" value="<%= request.getAttribute("edad") %>">

            <label>Dirección:</label>
            <input type="text" name="direccion" value="<%= request.getAttribute("direccion") %>">

            <label>Teléfono:</label>
            <input type="text" name="telefono" value="<%= request.getAttribute("telefono") %>">

            <label>Fecha de Expiración:</label>
            <input type="date" name="fechaExpiracion" value="<%= request.getAttribute("fechaExpiracion") %>">

            <div class="button-container">
                <button type="submit" class="save-button">Guardar Cambios</button>
                <button type="button" class="back-button" onclick="location.href='listarUsuarios?filtro='">Regresar a lista de usuarios</button>
                <button type="button" class="view-asistencias-button" onclick="toggleAsistencias()">Ver Asistencias</button>
            </div>
        </div>
    </form>

    <%
        // Formateador para las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Asistencia> asistencias = (List<Asistencia>) request.getAttribute("asistencias");
    %>

    <div id="asistenciasContainer" style="display:none;">
        <%
            if (asistencias != null && !asistencias.isEmpty()) {
        %>
        <h2>Asistencias Registradas</h2>
        <table>
            <tr>
                <th>ID Asistencia</th>
                <th>Fecha/Hora Entrada</th>
                <th>Fecha/Hora Salida</th>
            </tr>
            <%
                for (Asistencia a : asistencias) {
                    String entrada = a.getHorarioEntrada().format(formatter);
                    String salida = (a.getHorarioSalida() != null) ? a.getHorarioSalida().format(formatter) : "Aún dentro";
            %>
            <tr>
                <td><%= a.getIdAsistencia() %></td>
                <td><%= entrada %></td>
                <td><%= salida %></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
            <h2>No hay asistencias registradas para este usuario</h2>
        <%
            }
        %>
    </div>
</body>
</html>
