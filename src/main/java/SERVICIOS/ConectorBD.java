package SERVICIOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import MODELO.GESTOR_REPRODUCTOR.Cancion;

public class ConectorBD {

    private static final Logger LOGGER = Logger.getLogger(ConectorBD.class.getName());

    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/SoundSphere";
    private static final String USER = "root";
    private static final String PASSWORD = "Major@sTears0fW!nd_&%$#"; // Cambiar por tu contraseña

    /**
     * Establece la conexión a la base de datos.
     */
    public Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Obtiene todas las canciones disponibles en la base de datos.
     */
    public List<Cancion> obtenerCanciones() {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT c.id_cancion, c.titulo, a.nombre_artistico AS artista, al.titulo AS album, " +
                "c.url_archivo, c.duracion, c.genero " +
                "FROM Canciones c " +
                "JOIN Artista a ON c.id_artista = a.id_artista " +
                "LEFT JOIN Albumes al ON c.id_artista = al.id_artista";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cancion cancion = new Cancion(
                        rs.getInt("id_cancion"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getString("album"),
                        rs.getString("url_archivo"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                );
                canciones.add(cancion);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener canciones", e);
        }
        return canciones;
    }

    /**
     * Obtiene todas las playlists de la base de datos.
     */
    public List<String> obtenerPlaylists() {
        List<String> playlists = new ArrayList<>();
        String sql = "SELECT nombre FROM Playlist";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                playlists.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener playlists", e);
        }
        return playlists;
    }

    /**
     * Obtiene las canciones de una playlist específica por su nombre.
     */
    public List<Cancion> obtenerCancionesDePlaylist(String playlistNombre) {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT c.id_cancion, c.titulo, a.nombre_artistico AS artista, al.titulo AS album, " +
                "c.url_archivo, c.duracion, c.genero " +
                "FROM Canciones c " +
                "JOIN CancionesPlaylist cp ON c.id_cancion = cp.id_Cancion " +
                "JOIN Playlist p ON cp.id_Playlist = p.id_playlist " +
                "JOIN Artista a ON c.id_artista = a.id_artista " +
                "LEFT JOIN Albumes al ON c.id_artista = al.id_artista " +
                "WHERE p.nombre = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, playlistNombre);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cancion cancion = new Cancion(
                            rs.getInt("id_cancion"),
                            rs.getString("titulo"),
                            rs.getString("artista"),
                            rs.getString("album"),
                            rs.getString("url_archivo"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    );
                    canciones.add(cancion);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener canciones de la playlist: " + playlistNombre, e);
        }
        return canciones;
    }
}
