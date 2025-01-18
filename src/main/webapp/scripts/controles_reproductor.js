document.addEventListener("DOMContentLoaded", function () {
    const playPauseButton = document.getElementById("play-pause-button");

    let isPlaying = false;

    playPauseButton.addEventListener("click", function () {
        isPlaying = !isPlaying;
        playPauseButton.innerHTML = isPlaying ? "||" : "&gt;"; // Cambia entre pausa (||) y reproducir (>)
        console.log(isPlaying ? "Reproduciendo" : "Pausado");
    });
});
