/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/listarUsuarios")
public class ListarUsuariosServlet extends HttpServlet {

    private UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String filtro = request.getParameter("filtro");

            List<Map<String, Object>> usuarios;
            if (filtro != null && !filtro.trim().isEmpty()) {
                usuarios = usuarioController.buscarUsuarios(filtro);
            } else {
                usuarios = usuarioController.obtenerUsuariosConEstado();
            }

            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Error al listar usuarios");
        }
    }
}
