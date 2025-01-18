package MODELO.GESTOR_PLAYLISTS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import MODELO.GESTOR_REPRODUCTOR.Cancion;

public class Playlist {

    private int idPlaylist; // Consistente con la base de datos
    private String nombre;
    private LocalDate fechaCreacion; // Consistente con la base de datos
    private int idUsuario; // Consistente con la base de datos
    private List<Cancion> canciones; // Lista de canciones en la playlist

    // Constructor vacío
    public Playlist() {
        this.canciones = new ArrayList<>();
    }

    // Constructor con parámetros básicos
    public Playlist(int idPlaylist, String nombre, LocalDate fechaCreacion, int idUsuario) {
        this.idPlaylist = idPlaylist;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
        this.canciones = new ArrayList<>();
    }

    // Constructor completo con lista de canciones
    public Playlist(int idPlaylist, String nombre, LocalDate fechaCreacion, int idUsuario, List<Cancion> canciones) {
        this.idPlaylist = idPlaylist;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
        this.canciones = canciones != null ? canciones : new ArrayList<>();
    }

    // Métodos getters y setters
    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
                "idPlaylist=" + idPlaylist +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", idUsuario=" + idUsuario +
                ", canciones=" + canciones +
                '}';
    }
}