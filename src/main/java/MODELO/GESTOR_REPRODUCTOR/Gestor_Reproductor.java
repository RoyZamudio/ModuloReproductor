package MODELO.GESTOR_REPRODUCTOR;

import SERVICIOS.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Gestor_Reproductor {
    private List<Cancion> listaCanciones;
    private int indiceActual;
    private boolean reproduciendo;

    public Gestor_Reproductor() {
        this.listaCanciones = new ArrayList<>();
        this.indiceActual = -1;
        this.reproduciendo = false;
    }

    public void cargarCancionesDesdeBD() {
        String query = "SELECT c.id_cancion, c.titulo, a.nombre_artistico AS artista, al.titulo AS album, " +
                "c.url_archivo, c.duracion, c.genero " +
                "FROM Canciones c " +
                "JOIN Artista a ON c.id_artista = a.id_artista " +
                "LEFT JOIN Albumes al ON c.id_artista = al.id_artista";

        try (ConectorBD conector = new ConectorBD();
             Connection conn = conector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
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
                listaCanciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cancion reproducir() {
        if (indiceActual >= 0 && indiceActual < listaCanciones.size()) {
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    public void pausar() {
        reproduciendo = false;
    }

    public Cancion siguiente() {
        if (!listaCanciones.isEmpty()) {
            indiceActual = (indiceActual + 1) % listaCanciones.size();
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    public Cancion anterior() {
        if (!listaCanciones.isEmpty()) {
            indiceActual = (indiceActual - 1 + listaCanciones.size()) % listaCanciones.size();
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    public Cancion obtenerCancionActual() {
        if (indiceActual >= 0 && indiceActual < listaCanciones.size()) {
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    public boolean estaReproduciendo() {
        return reproduciendo;
    }
}