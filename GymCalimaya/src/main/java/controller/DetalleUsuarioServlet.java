package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/detalleUsuario")
public class DetalleUsuarioServlet extends HttpServlet {

    private final UsuarioController usuarioController = new UsuarioController();

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
                request.setAttribute("id", usuario.getId());
                request.setAttribute("nombre", usuario.getNombreCompleto());
                request.setAttribute("edad", usuario.getEdad());
                request.setAttribute("direccion", usuario.getDireccion());
                request.setAttribute("telefono", usuario.getTelefono());
                request.setAttribute("fechaExpiracion", usuario.getFechaExpiracion());

                request.getRequestDispatcher("detalleUsuario.jsp").forward(request, response);
            } else {
                response.sendRedirect("listarUsuarios?error=Usuario no encontrado");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=ID de usuario inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=Error al cargar el usuario");
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
            LocalDate fechaExpiracion = LocalDate.parse(request.getParameter("fechaExpiracion"));

            boolean actualizado = usuarioController.actualizarFechaExpiracion(id, fechaExpiracion);

            if (actualizado) {
                String message = "Fecha de expiración actualizada con éxito";
                response.sendRedirect("detalleUsuario?id=" + id + "&message=" + java.net.URLEncoder.encode(message, "UTF-8"));
            } else {
                String error = "Error al actualizar la fecha";
                response.sendRedirect("detalleUsuario?id=" + id + "&error=" + java.net.URLEncoder.encode(error, "UTF-8"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String error = "ID de usuario inválido";
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + java.net.URLEncoder.encode(error, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al procesar la solicitud";
            response.sendRedirect("detalleUsuario?id=" + request.getParameter("id") + "&error=" + java.net.URLEncoder.encode(error, "UTF-8"));
        }
    }
}

