package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    private UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar la codificación UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try {
            // Registrar un nuevo usuario
            String nombreCompleto = request.getParameter("nombreCompleto");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String telefonoEmergencia = request.getParameter("telefonoEmergencia");
            LocalDate fechaExpiracion = LocalDate.parse(request.getParameter("fechaExpiracion"));

            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setEdad(edad);
            usuario.setDireccion(direccion);
            usuario.setTelefono(telefono);
            usuario.setTelefonoEmergencia(telefonoEmergencia);
            usuario.setFechaExpiracion(fechaExpiracion);

            boolean resultado = usuarioController.registrarUsuario(usuario);

            if (resultado) {
                // Codificar mensaje para evitar problemas de caracteres especiales
                String message = URLEncoder.encode("Usuario registrado con éxito", StandardCharsets.UTF_8);
                response.sendRedirect("index.jsp?message=" + message);
            } else {
                String error = URLEncoder.encode("Error al registrar usuario", StandardCharsets.UTF_8);
                response.sendRedirect("index.jsp?error=" + error);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String error = URLEncoder.encode("Datos inválidos", StandardCharsets.UTF_8);
            response.sendRedirect("index.jsp?error=" + error);
        }
    }
}
