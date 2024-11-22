package adivina_la_cancion.prototipo.adivina_la_cancion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartidaDTO {

    private int numMaxUsuarios;

    private long usuarioID;

    private int numMaxRondas;

    private long playlistID;

    private boolean privada;

    private int codigoAcceso;
}
