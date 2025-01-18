<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="MODELO.GESTOR_REPRODUCTOR.Cancion" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reproductor de Música</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/reproductor.css">
    <script defer src="${pageContext.request.contextPath}/scripts/controles_reproductor.js"></script>
</head>
<body>
<div class="layout">
    <!-- Barra superior -->
    <div class="top-bar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/imagenes/logo.png" alt="Soundsphere Logo">
        </div>
        <div class="search">
            <label for="search-input">Buscar:</label>
            <input id="search-input" type="text" placeholder="Buscar música, artistas, álbumes...">
        </div>
        <div class="menu-buttons">
            <a href="${pageContext.request.contextPath}/Tienda">
                <img src="${pageContext.request.contextPath}/imagenes/tienda.jpg" alt="Tienda">
            </a>
            <a href="${pageContext.request.contextPath}/Streaming">
                <img src="${pageContext.request.contextPath}/imagenes/streaming.png" alt="Streaming">
            </a>
            <a href="${pageContext.request.contextPath}/Rec">
                <img src="${pageContext.request.contextPath}/imagenes/rec.webp" alt="Rec">
            </a>
            <a href="${pageContext.request.contextPath}/Reproductor">
                <img src="${pageContext.request.contextPath}/imagenes/play.webp" alt="Reproductor">
            </a>
            <a href="${pageContext.request.contextPath}/Home">
                <img src="${pageContext.request.contextPath}/imagenes/home.png" alt="Inicio">
            </a>
            <a href="${pageContext.request.contextPath}/Perfil">
                <img src="${pageContext.request.contextPath}/imagenes/user.png" alt="Perfil">
            </a>
        </div>
    </div>

    <!-- Barra lateral -->
    <aside class="sidebar">
        <h2>Tu Biblioteca</h2>
        <ul>
            <li><a href="#">Playlists</a></li>
            <li><a href="#">Álbumes</a></li>
            <li><a href="#">Artistas</a></li>
        </ul>
    </aside>

    <!-- Panel principal -->
    <main class="main">
        <div class="playlist-header">
            <h1>Playlist Activa</h1>
            <p>
                <%
                    Object obj = request.getAttribute("listaCanciones");
                    List<Cancion> listaCanciones = obj instanceof List<?> ? (List<Cancion>) obj : new java.util.ArrayList<>();
                    int totalCanciones = listaCanciones.size();
                    int totalDuracion = listaCanciones.stream().mapToInt(Cancion::getDuracion).sum();
                %>
                <%= totalCanciones %> canciones,
                <%= totalDuracion / 60 %> min <%= totalDuracion % 60 %> seg
            </p>
        </div>

        <!-- Lista de canciones -->
        <div class="song-list">
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Título</th>
                    <th>Artista</th>
                    <th>Álbum</th>
                    <th>Duración</th>
                </tr>
                </thead>
                <tbody>
                <%
                    int index = 1;
                    for (Cancion cancion : listaCanciones) {
                %>
                <tr>
                    <td><%= index++ %></td>
                    <td><%= cancion.getTitulo() %></td>
                    <td><%= cancion.getArtista() %></td>
                    <td><%= cancion.getAlbum() %></td>
                    <td><%= cancion.getDuracion() / 60 %>:<%= String.format("%02d", cancion.getDuracion() % 60) %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </main>

    <!-- Barra de reproducción -->
    <footer class="player-bar">
        <div class="song-info">
            <%
                Cancion cancionActual = (Cancion) request.getAttribute("cancionActual");
                String mensajeCancion = (cancionActual != null)
                        ? cancionActual.getTitulo() + " - " + cancionActual.getArtista()
                        : "No hay canción en reproducción.";
            %>
            <p><%= mensajeCancion %></p>
        </div>
        <div class="player-controls">
            <button id="prev-button" class="control-button">&lt;</button>
            <button id="play-pause-button" class="control-button">||</button>
            <button id="next-button" class="control-button">&gt;</button>
        </div>
    </footer>
</div>
</body>
</html>