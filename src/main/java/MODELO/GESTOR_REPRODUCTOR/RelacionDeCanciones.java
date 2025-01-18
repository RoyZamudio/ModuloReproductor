package MODELO.GESTOR_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class RelacionDeCanciones {
    private String criterio;            // Criterio de relación (e.g., "Artista: A")
    private List<Cancion> canciones;   // Lista de canciones relacionadas

    public RelacionDeCanciones(String criterio) {
        this.criterio = criterio;
        this.canciones = new ArrayList<>();
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public List<Cancion> filtrarCanciones(String filtro) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getTitulo().startsWith(filtro)) {
                resultado.add(cancion);
            }
        }
        return resultado;
    }
}