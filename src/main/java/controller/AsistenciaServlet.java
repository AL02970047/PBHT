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
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;

@WebServlet("/registrarAsistencia")
public class AsistenciaServlet extends HttpServlet {

    private final UsuarioController usuarioController = new UsuarioController();
    private final AsistenciaController asistenciaController = new AsistenciaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtro = request.getParameter("filtro");

        if (filtro != null) { // Búsqueda AJAX
            try {
                List<Usuario> usuarios = usuarioController.buscarUsuarios(filtro);
                List<UsuarioDTO> usuariosDTO = usuarios.stream()
                        .map(u -> new UsuarioDTO(u.getId(), u.getNombreCompleto()))
                        .collect(Collectors.toList());

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(usuariosDTO));
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al buscar usuarios");
            }
        } else { // Cargar la página de asistencia
            try {
                request.getRequestDispatcher("tomarAsistencia.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                String errorMsg = URLEncoder.encode("Error al cargar los usuarios", StandardCharsets.UTF_8);
                response.sendRedirect("index.jsp?error=" + errorMsg);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String accion = request.getParameter("accion");

            Usuario usuario = usuarioController.obtenerUsuarioPorId(idUsuario);

            if (usuario == null) {
                String errorMsg = URLEncoder.encode("Usuario no encontrado", StandardCharsets.UTF_8);
                response.sendRedirect("registrarAsistencia?error=" + errorMsg);
                return;
            }

            if (!usuario.isVigente()) {
                String mensaje = "Este usuario tiene su suscripción vencida desde el día: " + usuario.getFechaExpiracion();
                String encodedMessage = URLEncoder.encode(mensaje, StandardCharsets.UTF_8);
                response.sendRedirect("registrarAsistencia?error=" + encodedMessage);
                return;
            }

            boolean resultado = "entrada".equalsIgnoreCase(accion)
                    ? asistenciaController.registrarEntrada(idUsuario)
                    : asistenciaController.registrarSalida(idUsuario);

            if (resultado) {
                String message = URLEncoder.encode("Asistencia registrada correctamente", StandardCharsets.UTF_8);
                response.sendRedirect("registrarAsistencia?message=" + message);
            } else {
                String errorMsg = URLEncoder.encode("No se pudo registrar la asistencia", StandardCharsets.UTF_8);
                response.sendRedirect("registrarAsistencia?error=" + errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = URLEncoder.encode("Datos inválidos", StandardCharsets.UTF_8);
            response.sendRedirect("registrarAsistencia?error=" + errorMsg);
        }
    }

    // DTO para simplificar la respuesta JSON
    private static class UsuarioDTO {
        private int id;
        private String nombre;

        public UsuarioDTO(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
