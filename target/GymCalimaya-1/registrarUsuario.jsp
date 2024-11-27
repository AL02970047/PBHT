<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
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
        label, input {
            display: block;
            margin: 10px 0;
        }
        input, select {
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
        button:hover {
            opacity: 0.9;
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
    <h1>Registrar Usuario</h1>

    <!-- Formulario para registrar usuario -->
    <form action="registrarUsuario" method="post">
        <label for="nombreCompleto">Nombre Completo:</label>
        <input type="text" id="nombreCompleto" name="nombreCompleto" required>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required>

        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required>

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono" required>

        <label for="telefonoEmergencia">Teléfono Emergencia:</label>
        <input type="text" id="telefonoEmergencia" name="telefonoEmergencia" required>

        <label for="fechaExpiracion">Fecha de Expiración:</label>
        <input type="date" id="fechaExpiracion" name="fechaExpiracion" required>

        <!-- Contenedor de botones -->
        <div class="button-container">
            <button type="submit" class="submit-button">Registrar Usuario</button>
            <button type="button" class="back-button" onclick="location.href='index.jsp'">Regresar a Inicio</button>
        </div>
    </form>
</body>
</html>
