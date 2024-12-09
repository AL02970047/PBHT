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

public class UsuarioController {

    // Método para registrar un nuevo usuario
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

    // Método para obtener un usuario específico por ID
    public Usuario obtenerUsuarioPorId(int id) {
        String query = "SELECT id_usuario, nombre_completo, edad, direccion, telefono, telefono_emergencia, fecha_expiracion FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUsuario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para actualizar la fecha de expiración de un usuario
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

    // Método para obtener todos los usuarios con su estado (vigente o vencido)
    public List<Usuario> obtenerUsuariosConEstado() {
        String query = "SELECT id_usuario, nombre_completo, fecha_expiracion FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                System.err.println("Error: No se pudo establecer la conexión a la base de datos.");
                return usuarios; // Devuelve vacío si no hay conexión
            }

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());

                // Determinar el estado del usuario
                usuario.setVigente(usuario.getFechaExpiracion().isAfter(fechaActual));
                usuarios.add(usuario);
            }

            System.out.println("Usuarios encontrados: " + usuarios.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para buscar usuarios por filtro (ID o nombre)
    public List<Usuario> buscarUsuarios(String filtro) {
        String query = "SELECT id_usuario, nombre_completo, fecha_expiracion FROM usuarios WHERE id_usuario LIKE ? OR nombre_completo LIKE ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            LocalDate fechaActual = LocalDate.now();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());

                // Determinar el estado del usuario
                usuario.setVigente(usuario.getFechaExpiracion().isAfter(fechaActual));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para buscar usuarios por coincidencia parcial (para autocompletado)
    public List<Usuario> buscarUsuariosDinamicamente(String termino) {
        String query = "SELECT id_usuario, nombre_completo FROM usuarios WHERE nombre_completo LIKE ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + termino + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Nuevo método para actualizar todos los datos del usuario
    public boolean actualizarUsuarioCompleto(int id, String nombreCompleto, int edad, String direccion, String telefono, LocalDate fechaExpiracion) {
        String query = "UPDATE usuarios SET nombre_completo = ?, edad = ?, direccion = ?, telefono = ?, fecha_expiracion = ? WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreCompleto);
            stmt.setInt(2, edad);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setDate(5, java.sql.Date.valueOf(fechaExpiracion));
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear ResultSet a objeto Usuario
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setNombreCompleto(rs.getString("nombre_completo"));
        usuario.setEdad(rs.getInt("edad"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setTelefonoEmergencia(rs.getString("telefono_emergencia"));
        usuario.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());

        LocalDate fechaActual = LocalDate.now();
        usuario.setVigente(usuario.getFechaExpiracion().isAfter(fechaActual));

        return usuario;
    }
}
