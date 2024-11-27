package controller;

import model.Usuario;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {

    public boolean registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre_completo, edad, direccion, telefono, telefono_emergencia, fecha_expiracion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setInt(2, usuario.getEdad());
            stmt.setString(3, usuario.getDireccion());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getTelefonoEmergencia());
            stmt.setDate(6, java.sql.Date.valueOf(usuario.getFechaExpiracion()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> obtenerUsuariosConEstado() {
        String query = "SELECT id_usuario, nombre_completo, fecha_expiracion FROM usuarios";
        List<Map<String, Object>> usuarios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                Map<String, Object> usuario = new HashMap<>();
                usuario.put("id", rs.getInt("id_usuario"));
                usuario.put("nombre", rs.getString("nombre_completo"));
                usuario.put("fechaExpiracion", rs.getDate("fecha_expiracion").toLocalDate());

                if (rs.getDate("fecha_expiracion").toLocalDate().isAfter(fechaActual)) {
                    usuario.put("estado", "vigente");
                } else {
                    usuario.put("estado", "vencido");
                }

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public List<Map<String, Object>> buscarUsuarios(String filtro) {
        String query = "SELECT id_usuario, nombre_completo, fecha_expiracion FROM usuarios WHERE id_usuario LIKE ? OR nombre_completo LIKE ?";
        List<Map<String, Object>> usuarios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                Map<String, Object> usuario = new HashMap<>();
                usuario.put("id", rs.getInt("id_usuario"));
                usuario.put("nombre", rs.getString("nombre_completo"));
                usuario.put("fechaExpiracion", rs.getDate("fecha_expiracion").toLocalDate());

                if (rs.getDate("fecha_expiracion").toLocalDate().isAfter(fechaActual)) {
                    usuario.put("estado", "vigente");
                } else {
                    usuario.put("estado", "vencido");
                }

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String query = "SELECT id_usuario, nombre_completo, edad, direccion, telefono, telefono_emergencia, fecha_expiracion FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setTelefonoEmergencia(rs.getString("telefono_emergencia"));
                usuario.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizarFechaExpiracion(int id, LocalDate nuevaFecha) {
        String query = "UPDATE usuarios SET fecha_expiracion = ? WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(nuevaFecha));
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
