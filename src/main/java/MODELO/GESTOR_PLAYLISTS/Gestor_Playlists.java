package MODELO.GESTOR_PLAYLISTS;

import MODELO.GESTOR_REPRODUCTOR.Cancion;
import SERVICIOS.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Playlists {

    private static final Logger LOGGER = Logger.getLogger(Gestor_Playlists.class.getName());
    private final ConectorBD conectorBD;

    public Gestor_Playlists() {
        this.conectorBD = new ConectorBD();
    }

    // Obtener todas las playlists
    public List<Playlist> obtenerPlaylists(int idUsuario) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE id_usuario = ?";

        try (Connection conn = conectorBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Playlist playlist = new Playlist();
                    playlist.setIdPlaylist(rs.getInt("id_playlist"));
                    playlist.setNombre(rs.getString("nombre"));
                    playlist.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime().toLocalDate());
                    playlist.setIdUsuario(rs.getInt("id_usuario"));
                    playlists.add(playlist);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener playlists para el usuario con ID " + idUsuario, e);
        }
        return playlists;
    }

    // Crear una nueva playlist
    public boolean crearPlaylist(String nombre, int idUsuario) {
        String query = "INSERT INTO playlist (nombre, fecha_creacion, id_usuario) VALUES (?, CURRENT_TIMESTAMP, ?)";

        try (Connection conn = conectorBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setInt(2, idUsuario);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear la playlist: " + nombre, e);
        }
        return false;
    }

    // Agregar una canción a una playlist
    public boolean agregarCancionAPlaylist(int idCancion, int idPlaylist) {
        String query = "INSERT INTO cancionesplaylist (id_cancion, id_playlist) VALUES (?, ?)";

        try (Connection conn = conectorBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCancion);
            stmt.setInt(2, idPlaylist);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la canción " + idCancion + " a la playlist " + idPlaylist, e);
        }
        return false;
    }

    // Obtener canciones de una playlist
    public List<Cancion> obtenerCancionesDePlaylist(int idPlaylist) {
        List<Cancion> canciones = new ArrayList<>();
        String query = "SELECT c.* FROM canciones c " +
                "INNER JOIN cancionesplaylist cp ON c.id_cancion = cp.id_cancion " +
                "WHERE cp.id_playlist = ?";

        try (Connection conn = conectorBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPlaylist);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cancion cancion = new Cancion(
                            rs.getInt("id_cancion"),
                            rs.getString("titulo"),
                            rs.getString("artista"),
                            null, // Ajusta si necesitas incluir el álbum
                            rs.getString("url_archivo"),
                            rs.getInt("duracion"), // La duración ya está en segundos
                            rs.getString("genero")
                    );
                    canciones.add(cancion);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener canciones de la playlist con ID " + idPlaylist, e);
        }
        return canciones;
    }
}
