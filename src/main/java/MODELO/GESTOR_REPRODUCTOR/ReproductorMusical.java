package MODELO.GESTOR_REPRODUCTOR;

public class ReproductorMusical {
    private Cancion cancionActual;   // Canción que se está reproduciendo
    private boolean enReproduccion; // Estado de reproducción

    public ReproductorMusical() {
        this.cancionActual = null;
        this.enReproduccion = false;
    }

    public Cancion getCancionActual() {
        return cancionActual;
    }

    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }

    public void reproducir(Cancion cancion) {
        this.cancionActual = cancion;
        this.enReproduccion = true;
        System.out.println("Reproduciendo: " + cancion.getTitulo());
    }

    public void pausar() {
        this.enReproduccion = false;
        System.out.println("Pausado: " + (cancionActual != null ? cancionActual.getTitulo() : "Ninguna canción"));
    }

    public boolean estaReproduciendo() {
        return enReproduccion;
    }
}