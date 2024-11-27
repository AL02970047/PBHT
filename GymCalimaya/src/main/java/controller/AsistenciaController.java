package controller;

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

                // Determinar si el usuario está vigente
                usuario.setVigente(usuario.getFechaExpiracion().isAfter(hoy));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
