<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Asistencia" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Reporte de Asistencia</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f9;
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
    .button-container {
        margin-top: 20px;
        display: flex;
        gap: 10px;
    }
    a, button {
        padding: 10px 20px;
        font-size: 16px;
        text-decoration: none;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .download-button {
        background-color: #28a745;
    }
    .download-button:hover {
        background-color: #218838;
    }
    .back-button-red {
        background-color: #dc3545;
    }
    .back-button-red:hover {
        background-color: #a71d2a;
    }
</style>
</head>
<body>
<h1>Reporte de Asistencia - <%= request.getAttribute("filtroSeleccionado") %></h1>

<%
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    List<Asistencia> asistencias = (List<Asistencia>) request.getAttribute("asistencias");
    if (asistencias != null && !asistencias.isEmpty()) {
%>
    <table>
        <tr>
            <th>ID Usuario</th>
            <th>Nombre Completo</th>
            <th>Fecha/Hora Entrada</th>
            <th>Fecha/Hora Salida</th>
        </tr>
        <%
            for (Asistencia a : asistencias) {
                String entrada = a.getHorarioEntrada().format(formatter);
                String salida = (a.getHorarioSalida() != null) ? a.getHorarioSalida().format(formatter) : "Aún dentro";
        %>
        <tr>
            <td><%= a.getIdUsuario() %></td>
            <td><%= a.getNombreCompletoUsuario() %></td>
            <td><%= entrada %></td>
            <td><%= salida %></td>
        </tr>
        <% } %>
    </table>
    <div class="button-container">
        <a href="AsistenciaReporte?filtro=<%= request.getAttribute("filtroSeleccionado") %>&action=download" target="_blank" class="download-button">Descargar CSV</a>
        <a href="index.jsp" class="back-button-red">Regresar a Inicio</a>
    </div>
<%
    } else {
%>
    <h2>No hay asistencias para este periodo</h2>
    <a href="index.jsp" class="back-button-red">Regresar a Inicio</a>
<%
    }
%>
</body>
</html>
