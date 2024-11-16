package adivina_la_cancion.prototipo.adivina_la_cancion.dto;

public class PartidaDTO {

    private int numMaxUsuarios;

    private long usuarioID;

    private int numMaxRondas;

    private long playlistID;

    public PartidaDTO(int numMaxUsuarios, long usuarioID, int numMaxRondas, long playlistID) {
        this.numMaxUsuarios = numMaxUsuarios;
        this.usuarioID = usuarioID;
        this.numMaxRondas = numMaxRondas;
        this.playlistID = playlistID;
    }

    public int getNumMaxUsuarios() {
        return numMaxUsuarios;
    }

    public long getUsuarioID() {
        return usuarioID;
    }

    public int getNumMaxRondas() {
        return numMaxRondas;
    }

    public long getPlaylistID() {
        return playlistID;
    }
}
