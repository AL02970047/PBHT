<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Filtrar Asistencia</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f9;
    }
    label, select, button {
        display: block;
        margin: 10px 0;
    }
    select {
        padding: 8px;
        font-size: 14px;
        width: 100%;
        max-width: 200px;
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
        color: white;
    }
    button:hover {
        opacity: 0.9;
    }

    /* Botón principal (azul) */
    .submit-button {
        background-color: #007bff;
    }
    .submit-button:hover {
        background-color: #0056b3;
    }

    /* Botón rojo para regresar al inicio */
    .back-button-red {
        background-color: #dc3545;
    }
    .back-button-red:hover {
        background-color: #a71d2a;
    }
</style>
</head>
<body>
<h1>Filtrar Lista de Asistencia</h1>

<form action="AsistenciaReporte" method="get">
    <label for="filtro">Ver asistencias por:</label>
    <select name="filtro" id="filtro" required>
        <option value="">Seleccione</option>
        <option value="dia">Día</option>
        <option value="semana">Semana</option>
        <option value="mes">Mes</option>
        <option value="ano">Año</option>
    </select>

    <div class="button-container">
        <button type="submit" class="submit-button">Ver Reporte</button>
        <button type="button" class="back-button-red" onclick="location.href='index.jsp'">Regresar a Inicio</button>
    </div>
</form>
</body>
</html>
