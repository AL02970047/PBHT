package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
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
        // Manejar solicitudes normales para cargar la página
        String filtro = request.getParameter("filtro");

        if (filtro != null) { // Si es una solicitud de búsqueda (AJAX)
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
        } else { // Si es una solicitud para cargar la página de asistencia
            try {
                request.getRequestDispatcher("tomarAsistencia.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("index.jsp?error=Error al cargar los usuarios");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String accion = request.getParameter("accion");

            // Validar usuario
            Usuario usuario = usuarioController.obtenerUsuarioPorId(idUsuario);

            if (usuario == null) {
                response.sendRedirect("registrarAsistencia?error=Usuario no encontrado");
                return;
            }

            if (!usuario.isVigente()) {
                response.sendRedirect("registrarAsistencia?error=Este usuario tiene su suscripción vencida desde el día: " + usuario.getFechaExpiracion());
                return;
            }

            // Registrar entrada o salida
            boolean resultado = "entrada".equalsIgnoreCase(accion)
                    ? asistenciaController.registrarEntrada(idUsuario)
                    : asistenciaController.registrarSalida(idUsuario);

            if (resultado) {
                response.sendRedirect("registrarAsistencia?message=Asistencia registrada correctamente");
            } else {
                response.sendRedirect("registrarAsistencia?error=No se pudo registrar la asistencia");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrarAsistencia?error=Datos inválidos");
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
