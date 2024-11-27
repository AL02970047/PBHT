package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    private UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                response.sendRedirect("index.jsp?message=Usuario registrado con éxito");
            } else {
                response.sendRedirect("index.jsp?error=Error al registrar usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Datos inválidos");
        }
    }
}
