package controller;

import model.Asistencia;
import model.Usuario;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaController {

    public boolean registrarEntrada(int idUsuario) {
        String query = "INSERT INTO asistencia_calimaya (id_usuario, horario_entrada) VALUES (?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarSalida(int idUsuario) {
        String query = "UPDATE asistencia_calimaya SET horario_salida = NOW() WHERE id_usuario = ? AND horario_salida IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerUsuariosConEstado() {
        String query = "SELECT id_usuario, nombre_completo, fecha_expiracion FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                usuario.setVigente(usuario.getFechaExpiracion().isAfter(hoy));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para obtener asistencias de un usuario específico
    public List<Asistencia> obtenerAsistenciasPorUsuario(int idUsuario) {
        String query = "SELECT id_asistencia, id_usuario, horario_entrada, horario_salida FROM asistencia_calimaya WHERE id_usuario = ?";
        List<Asistencia> asistencias = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setIdAsistencia(rs.getInt("id_asistencia"));
                    asistencia.setIdUsuario(rs.getInt("id_usuario"));
                    asistencia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                    if (rs.getTimestamp("horario_salida") != null) {
                        asistencia.setHorarioSalida(rs.getTimestamp("horario_salida").toLocalDateTime());
                    }
                    asistencias.add(asistencia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asistencias;
    }

    // Nuevo método para obtener asistencias por filtro (semana, mes, año) con JOIN a usuarios
    public List<Asistencia> obtenerAsistenciasPorFiltro(String filtro) {
        int dias = 0;
        switch (filtro) {
            case "semana":
                dias = 7;
                break;
            case "mes":
                dias = 30;
                break;
            case "ano":
                dias = 365;
                break;
            default:
                dias = 7;
                break;
        }

        String query = "SELECT a.id_usuario, u.nombre_completo, a.horario_entrada, a.horario_salida " +
                       "FROM asistencia_calimaya a " +
                       "JOIN usuarios u ON a.id_usuario = u.id_usuario " +
                       "WHERE a.horario_entrada >= DATE_SUB(CURDATE(), INTERVAL ? DAY)";

        List<Asistencia> asistencias = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dias);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setIdUsuario(rs.getInt("id_usuario"));
                    asistencia.setNombreCompletoUsuario(rs.getString("nombre_completo"));
                    asistencia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                    if (rs.getTimestamp("horario_salida") != null) {
                        asistencia.setHorarioSalida(rs.getTimestamp("horario_salida").toLocalDateTime());
                    }
                    asistencias.add(asistencia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asistencias;
    }
}
