package adivina_la_cancion.prototipo.adivina_la_cancion.dto;

public class PartidaDTO {

    private int numMaxUsuarios;

    private long usuarioID;

    private int numMaxRondas;

    private long playlistID;

    private boolean privada;

    private int contrasenha;

    public PartidaDTO(int numMaxUsuarios, long usuarioID, int numMaxRondas, long playlistID, boolean privada, int contrasenha) {
        this.numMaxUsuarios = numMaxUsuarios;
        this.usuarioID = usuarioID;
        this.numMaxRondas = numMaxRondas;
        this.playlistID = playlistID;
        this.privada = privada;
        this.contrasenha = contrasenha;
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

    public boolean getPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }

    public int getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(int contrasenha) {
        this.contrasenha = contrasenha;
    }
}
