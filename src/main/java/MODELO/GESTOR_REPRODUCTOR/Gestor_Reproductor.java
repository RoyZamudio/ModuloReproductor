package MODELO.GESTOR_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class Gestor_Reproductor {
    private List<Cancion> listaCanciones; // Lista de canciones disponibles
    private int indiceActual; // Índice de la canción actual en reproducción
    private boolean reproduciendo; // Estado del reproductor

    // Constructor
    public Gestor_Reproductor() {
        this.listaCanciones = new ArrayList<>();
        this.indiceActual = -1; // Inicialmente no hay canción seleccionada
        this.reproduciendo = false;
    }

    // Cargar una lista de canciones
    public void cargarCanciones(List<Cancion> canciones) {
        this.listaCanciones = canciones;
        this.indiceActual = !canciones.isEmpty() ? 0 : -1; // Selecciona la primera canción si hay canciones
    }

    // Reproducir la canción actual y devolverla
    public Cancion reproducir() {
        if (indiceActual >= 0 && indiceActual < listaCanciones.size()) {
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    // Pausar la reproducción
    public void pausar() {
        reproduciendo = false;
    }

    // Reproducir la siguiente canción y devolverla
    public Cancion siguiente() {
        if (!listaCanciones.isEmpty()) {
            indiceActual = (indiceActual + 1) % listaCanciones.size();
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    // Reproducir la canción anterior y devolverla
    public Cancion anterior() {
        if (!listaCanciones.isEmpty()) {
            indiceActual = (indiceActual - 1 + listaCanciones.size()) % listaCanciones.size();
            reproduciendo = true;
            return listaCanciones.get(indiceActual);
        }
        return null;
    }

    // Obtener la canción actual
    public Cancion obtenerCancionActual() {
        if (indiceActual >= 0 && indiceActual < listaCanciones.size()) {
            return listaCanciones.get(indiceActual);
        }
        return null; // Si no hay canción seleccionada
    }

    // Verificar si se está reproduciendo una canción
    public boolean estaReproduciendo() {
        return reproduciendo;
    }
}