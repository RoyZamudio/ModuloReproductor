package MODELO.GESTOR_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private int id;                  // Identificador único
    private String nombre;           // Nombre del álbum
    private String artista;          // Artista del álbum
    private List<Cancion> canciones; // Lista de canciones en el álbum

    public Album(int id, String nombre, String artista) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.canciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public int obtenerDuracionTotal() {
        int duracionTotal = 0;
        for (Cancion cancion : canciones) {
            duracionTotal += cancion.getDuracion();
        }
        return duracionTotal;
    }
}