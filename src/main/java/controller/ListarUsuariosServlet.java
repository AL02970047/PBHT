package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/listarUsuarios")
public class ListarUsuariosServlet extends HttpServlet {

    private final UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener filtro de búsqueda
            String filtro = request.getParameter("filtro");

            // Lista de usuarios a enviar al JSP
            List<Usuario> usuarios;

            // Si hay un filtro, buscar usuarios que coincidan; de lo contrario, cargar todos
            if (filtro != null && !filtro.trim().isEmpty()) {
                usuarios = usuarioController.buscarUsuarios(filtro);
            } else {
                usuarios = usuarioController.obtenerUsuariosConEstado();
            }

            // Enviar lista de usuarios al JSP
            request.setAttribute("usuarios", usuarios);

            // Redirigir al JSP con los datos
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Error al listar usuarios");
        }
    }
}
