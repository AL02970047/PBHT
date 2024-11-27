package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrarAsistencia")
public class AsistenciaServlet extends HttpServlet {
    private final UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la lista de usuarios con su estado
        List<Map<String, Object>> usuarios = usuarioController.obtenerUsuariosConEstado();

        // Depuración: Asegúrate de que los usuarios están siendo enviados
        System.out.println("Usuarios enviados al JSP:");
        for (Map<String, Object> usuario : usuarios) {
            System.out.println(usuario);
        }

        // Enviar usuarios al JSP
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("tomarAsistencia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String accion = request.getParameter("accion"); // "entrada" o "salida"

            AsistenciaController asistenciaController = new AsistenciaController();
            boolean resultado = false;

            // Registrar entrada o salida según la acción
            if ("entrada".equalsIgnoreCase(accion)) {
                resultado = asistenciaController.registrarEntrada(idUsuario);
            } else if ("salida".equalsIgnoreCase(accion)) {
                resultado = asistenciaController.registrarSalida(idUsuario);
            }

            // Redirigir según el resultado
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
}
