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

@WebServlet("/detalleUsuario")
public class DetalleUsuarioServlet extends HttpServlet {

    private UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = usuarioController.obtenerUsuarioPorId(id);

            if (usuario != null) {
                request.setAttribute("id", usuario.getId());
                request.setAttribute("nombre", usuario.getNombreCompleto());
                request.setAttribute("edad", usuario.getEdad());
                request.setAttribute("direccion", usuario.getDireccion());
                request.setAttribute("telefono", usuario.getTelefono());
                request.setAttribute("fechaExpiracion", usuario.getFechaExpiracion());

                request.getRequestDispatcher("detalleUsuario.jsp").forward(request, response);
            } else {
                String error = URLEncoder.encode("Usuario no encontrado", StandardCharsets.UTF_8.toString());
                response.sendRedirect("listarUsuarios?error=" + error);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String error = URLEncoder.encode("ID de usuario inválido", StandardCharsets.UTF_8.toString());
            response.sendRedirect("listarUsuarios?error=" + error);
        } catch (Exception e) {
            e.printStackTrace();
            String error = URLEncoder.encode("Error al cargar el usuario", StandardCharsets.UTF_8.toString());
            response.sendRedirect("listarUsuarios?error=" + error);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            LocalDate fechaExpiracion = LocalDate.parse(request.getParameter("fechaExpiracion"));

            boolean actualizado = usuarioController.actualizarFechaExpiracion(id, fechaExpiracion);

            if (actualizado) {
                String message = URLEncoder.encode("Fecha de expiración actualizada con éxito", StandardCharsets.UTF_8.toString());
                response.sendRedirect("detalleUsuario?id=" + id + "&message=" + message);
            } else {
                String error = URLEncoder.encode("Error al actualizar la fecha", StandardCharsets.UTF_8.toString());
                response.sendRedirect("detalleUsuario?id=" + id + "&error=" + error);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String error = URLEncoder.encode("ID de usuario inválido", StandardCharsets.UTF_8.toString());
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + error);
        } catch (Exception e) {
            e.printStackTrace();
            String error = URLEncoder.encode("Error al procesar la solicitud", StandardCharsets.UTF_8.toString());
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + error);
        }
    }
}
