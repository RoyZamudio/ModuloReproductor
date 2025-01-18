package MODELO.GESTOR_PLAYLISTS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import MODELO.GESTOR_REPRODUCTOR.Cancion;

public class Playlist {

    private int id_playlist; // Cambiado para coincidir con la base de datos
    private String nombre;
    private LocalDate fecha_creacion; // Consistencia con la base de datos
    private int id_usuario; // Cambiado para coincidir con la base de datos
    private List<Cancion> canciones; // Lista de canciones en la playlist

    // Constructor vacío
    public Playlist() {
        this.canciones = new ArrayList<>();
    }

    // Constructor con parámetros básicos
    public Playlist(int id_playlist, String nombre, LocalDate fecha_creacion, int id_usuario) {
        this.id_playlist = id_playlist;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.id_usuario = id_usuario;
        this.canciones = new ArrayList<>();
    }

    // Constructor completo con lista de canciones
    public Playlist(int id_playlist, String nombre, LocalDate fecha_creacion, int id_usuario, List<Cancion> canciones) {
        this.id_playlist = id_playlist;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.id_usuario = id_usuario;
        this.canciones = canciones != null ? canciones : new ArrayList<>();
    }

    // Métodos getters y setters
    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones != null ? canciones : new ArrayList<>();
    }

    // Métodos adicionales
    public void agregarCancion(Cancion cancion) {
        if (canciones == null) {
            canciones = new ArrayList<>();
        }
        this.canciones.add(cancion);
    }

    public void eliminarCancion(Cancion cancion) {
        if (canciones != null) {
            this.canciones.remove(cancion);
        }
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id_playlist=" + id_playlist +
                ", nombre='" + nombre + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", id_usuario=" + id_usuario +
                ", canciones=" + canciones +
                '}';
    }
}