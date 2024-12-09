package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Asistencia;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/AsistenciaReporte")
public class AsistenciaReporteServlet extends HttpServlet {

    private final AsistenciaController asistenciaController = new AsistenciaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtro = request.getParameter("filtro"); // semana, mes, ano
        String action = request.getParameter("action"); // null o "download"

        // Obtener asistencias según filtro
        List<Asistencia> asistencias = asistenciaController.obtenerAsistenciasPorFiltro(filtro);

        if ("download".equalsIgnoreCase(action)) {
            // Descargar el reporte en CSV
            response.setContentType("text/csv; charset=UTF-8");
            String fileName = "reporte_asistencia_" + filtro + ".csv";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");

            PrintWriter out = response.getWriter();
            // Encabezados del CSV
            out.println("ID Usuario,Nombre Completo,Fecha/Hora Entrada,Fecha/Hora Salida");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Asistencia a : asistencias) {
                String entrada = a.getHorarioEntrada().format(formatter);
                String salida = (a.getHorarioSalida() != null) ? a.getHorarioSalida().format(formatter) : "Aún dentro";
                out.println(a.getIdUsuario() + "," + a.getNombreCompletoUsuario() + "," + entrada + "," + salida);
            }

            out.flush();
            out.close();

        } else {
            // Mostrar en pantalla
            request.setAttribute("asistencias", asistencias);
            request.setAttribute("filtroSeleccionado", filtro);

            request.getRequestDispatcher("verAsistenciaReporte.jsp").forward(request, response);
        }
    }
}
