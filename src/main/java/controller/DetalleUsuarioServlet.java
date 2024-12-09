package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.Asistencia;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/detalleUsuario")
public class DetalleUsuarioServlet extends HttpServlet {

    private final UsuarioController usuarioController = new UsuarioController();
    private final AsistenciaController asistenciaController = new AsistenciaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configuración de codificación
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = usuarioController.obtenerUsuarioPorId(id);

            if (usuario != null) {
                // Obtener asistencias del usuario
                List<Asistencia> asistencias = asistenciaController.obtenerAsistenciasPorUsuario(id);

                // Enviar datos del usuario y sus asistencias a la JSP
                request.setAttribute("id", usuario.getId());
                request.setAttribute("nombre", usuario.getNombreCompleto());
                request.setAttribute("edad", usuario.getEdad());
                request.setAttribute("direccion", usuario.getDireccion());
                request.setAttribute("telefono", usuario.getTelefono());
                request.setAttribute("fechaExpiracion", usuario.getFechaExpiracion());
                request.setAttribute("asistencias", asistencias);

                request.getRequestDispatcher("detalleUsuario.jsp").forward(request, response);
            } else {
                response.sendRedirect("listarUsuarios?error=" + URLEncoder.encode("Usuario no encontrado", StandardCharsets.UTF_8));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=" + URLEncoder.encode("ID de usuario inválido", StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=" + URLEncoder.encode("Error al cargar el usuario", StandardCharsets.UTF_8));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configuración de codificación
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombreCompleto = request.getParameter("nombreCompleto");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            LocalDate fechaExpiracion = LocalDate.parse(request.getParameter("fechaExpiracion"));

            // Aquí se asume que el método actualizarUsuarioCompleto existe en UsuarioController y realiza el UPDATE.
            boolean actualizado = usuarioController.actualizarUsuarioCompleto(
                    id, nombreCompleto, edad, direccion, telefono, fechaExpiracion
            );

            if (actualizado) {
                String message = URLEncoder.encode("Datos del usuario actualizados con éxito", StandardCharsets.UTF_8);
                response.sendRedirect("detalleUsuario?id=" + id + "&message=" + message);
            } else {
                String error = URLEncoder.encode("Error al actualizar los datos del usuario", StandardCharsets.UTF_8);
                response.sendRedirect("detalleUsuario?id=" + id + "&error=" + error);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String error = URLEncoder.encode("Datos inválidos", StandardCharsets.UTF_8);
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + error);
        } catch (Exception e) {
            e.printStackTrace();
            String error = URLEncoder.encode("Error al procesar la solicitud", StandardCharsets.UTF_8);
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + error);
        }
    }
}
