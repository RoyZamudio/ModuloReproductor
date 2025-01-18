package CONTROLADOR;

import MODELO.GESTOR_PLAYLISTS.*;
import MODELO.GESTOR_REPRODUCTOR.Cancion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Controlador_Gestor_Playlists")
public class Controlador_Gestor_Playlists extends HttpServlet {

    private Gestor_Playlists gestorPlaylists;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorPlaylists = new Gestor_Playlists();
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

        try {
            Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
            if (idUsuario == null) throw new Exception("Usuario no autenticado.");

            switch (accion) {
                case "crearPlaylist":
                    crearPlaylist(request, idUsuario);
                    break;
                case "agregarCancion":
                    agregarCancionAPlaylist(request);
                    break;
                case "verPlaylist":
                    cargarCancionesDePlaylist(request);
                    break;
                default:
                    request.setAttribute("error", "Acción no válida.");
                    break;
            }

            List<Playlist> playlists = gestorPlaylists.obtenerPlaylists(idUsuario);
            request.setAttribute("playlists", playlists);

        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/IU_Gestor_Playlists.jsp").forward(request, response);
    }

    private void crearPlaylist(HttpServletRequest request, int idUsuario) throws Exception {
        String nombrePlaylist = request.getParameter("nombrePlaylist");

        if (nombrePlaylist != null && !nombrePlaylist.isEmpty()) {
            gestorPlaylists.crearPlaylist(nombrePlaylist, idUsuario);
        } else {
            throw new Exception("El nombre de la playlist no puede estar vacío.");
        }
    }

    private void agregarCancionAPlaylist(HttpServletRequest request) throws Exception {
        String idCancionParam = request.getParameter("idCancion");
        String idPlaylistParam = request.getParameter("idPlaylist");

        if (idCancionParam != null && idPlaylistParam != null) {
            int idCancion = Integer.parseInt(idCancionParam);
            int idPlaylist = Integer.parseInt(idPlaylistParam);
            gestorPlaylists.agregarCancionAPlaylist(idCancion, idPlaylist);
        } else {
            throw new Exception("ID de canción o playlist no válido.");
        }
    }

    private void cargarCancionesDePlaylist(HttpServletRequest request) throws Exception {
        String idPlaylistParam = request.getParameter("idPlaylist");

        if (idPlaylistParam != null) {
            int idPlaylist = Integer.parseInt(idPlaylistParam);
            List<Cancion> canciones = gestorPlaylists.obtenerCancionesDePlaylist(idPlaylist);
            request.setAttribute("cancionesPlaylist", canciones);
        } else {
            throw new Exception("ID de playlist no válido.");
        }
    }
}