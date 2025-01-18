package MODELO.GESTOR_REPRODUCTOR;

public class Cancion {
    private int id;
    private String titulo;
    private String artista;
    private String album;
    private String urlArchivo;
    private int duracion; // en segundos
    private String genero;

    // Constructor
    public Cancion(int id, String titulo, String artista, String album, String urlArchivo, int duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.urlArchivo = urlArchivo;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getAlbum() { return album; }
    public String getUrlArchivo() { return urlArchivo; }
    public int getDuracion() { return duracion; }
    public String getGenero() { return genero; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setArtista(String artista) { this.artista = artista; }
    public void setAlbum(String album) { this.album = album; }
    public void setUrlArchivo(String urlArchivo) { this.urlArchivo = urlArchivo; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setGenero(String genero) { this.genero = genero; }
}