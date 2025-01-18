package CONTROLADOR;

import MODELO.GESTOR_REPRODUCTOR.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controlador_Gestor_Reproductor")
public class Controlador_Gestor_Reproductor extends HttpServlet {

    private Gestor_Reproductor gestorReproductor;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorReproductor = new Gestor_Reproductor();
        gestorReproductor.cargarCancionesDesdeBD(); // Carga las canciones desde la base de datos
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
        Cancion cancionActual = null;

        if (accion != null) {
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
            }
        }

        request.setAttribute("cancionActual", gestorReproductor.obtenerCancionActual());
        request.setAttribute("estado", gestorReproductor.estaReproduciendo() ? "Reproduciendo" : "Pausado");

        request.getRequestDispatcher("/WEB-INF/IU_Gestor_Reproductor.jsp").forward(request, response);
    }
}