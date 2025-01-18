package CONTROLADOR;

import MODELO.GESTOR_REPRODUCTOR.*;
import SERVICIOS.ConectorBD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Controlador_Gestor_Reproductor")
public class Controlador_Gestor_Reproductor extends HttpServlet {

    private Gestor_Reproductor gestorReproductor;
    private List<Cancion> listaCanciones;
    private List<String> playlists; // Lista de nombres de playlists

    @Override
    public void init() throws ServletException {
        super.init();
        gestorReproductor = new Gestor_Reproductor();

        // Cargar canciones y playlists desde la base de datos
        ConectorBD conectorBD = new ConectorBD();
        listaCanciones = conectorBD.obtenerCanciones();
        playlists = conectorBD.obtenerPlaylists(); // Método que devuelve una lista de nombres de playlists
        gestorReproductor.cargarCanciones(listaCanciones);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manejarAccion(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manejarAccion(request, response);
    }

    private void manejarAccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String playlistSeleccionada = request.getParameter("playlist");
        List<Cancion> cancionesPlaylist = null; // Canciones de la playlist seleccionada

        // Si se seleccionó una playlist, cargar las canciones correspondientes
        if (playlistSeleccionada != null && !playlistSeleccionada.isEmpty()) {
            ConectorBD conectorBD = new ConectorBD();
            cancionesPlaylist = conectorBD.obtenerCancionesDePlaylist(playlistSeleccionada); // Método en ConectorBD
            request.setAttribute("cancionesPlaylist", cancionesPlaylist);
            request.setAttribute("nombrePlaylist", playlistSeleccionada);
        }

        Cancion cancionActual = null;

        if (accion != null && !accion.isEmpty()) {
            switch (accion) {
                case "reproducir":
                    cancionActual = gestorReproductor.reproducir();
                    break;
                case "pausar":
                    gestorReproductor.pausar();
                    break;
                case "siguiente":
                    cancionActual = gestorReproductor.siguiente();
                    break;
                case "anterior":
                    cancionActual = gestorReproductor.anterior();
                    break;
                default:
                    request.setAttribute("error", "Acción no válida.");
                    break;
            }
        }

        // Configurar datos para la vista
        request.setAttribute("estado", gestorReproductor.estaReproduciendo() ? "Reproduciendo" : "Pausado");
        request.setAttribute("cancionActual", gestorReproductor.obtenerCancionActual());
        request.setAttribute("listaCanciones", listaCanciones);
        request.setAttribute("playlists", playlists);

        // Redirigir a la vista principal
        request.getRequestDispatcher("/WEB-INF/IU_Gestor_Reproductor.jsp").forward(request, response);
    }
}