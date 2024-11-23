
package adivina_la_cancion.prototipo.adivina_la_cancion.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Partida;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Playlist;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Usuario;
import adivina_la_cancion.prototipo.adivina_la_cancion.dto.PartidaDTO;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PartidaRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PlaylistRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.UsuarioRepository;

@Service
public class PartidaService {

    @Autowired
    protected PartidaRepository partidaRepo;

    @Autowired
    protected PlaylistRepository playlistRepo;

    @Autowired
    protected UsuarioRepository ur;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Partida partidaActual;

    int rondasCreadas = 0; // Debería ser un mapa id partida y numero de rondas creadas
    private ScheduledFuture<?> future;
    private Duration INTERVALO = Duration.ofSeconds(5);

    /*
     * No lo uso, solo sirve como ejemplo de uso de taskScheduler
     * // TODO: Solo puede iniciar partida el anfitrion
     * public RespuestaService<Partida> iniciarPartidaPorAnfitrion(long partidaID) {
     * Optional<Partida> partidaOptional = partidaRepo.findById(partidaID);
     * 
     * if (partidaOptional.isPresent()) {
     * partidaActual = partidaOptional.get();
     * future = taskScheduler.scheduleAtFixedRate(() -> crearRonda(1234),
     * INTERVALO);
     * 
     * return new RespuestaService<>(true, "Partida iniciada", partidaActual);
     * } else {
     * return new RespuestaService<>(false, "Partida no encontrada", null);
     * }
     * }
     * 
     * @Transactional
     * private void crearRonda(int idRonda) {
     * if (partidaActual != null) {
     * if (rondasCreadas < 5) {
     * System.out.println("Ejecutando método crearRonda, ronda número " +
     * rondasCreadas);
     * System.out.println(partidaActual.getRondas().size());
     * new Ronda();
     * partidaRepo.save(partidaActual);
     * rondasCreadas++;
     * } else {
     * future.cancel(false);
     * System.out.println("Ejecución completada de método crearRonda " +
     * rondasCreadas + " veces, terminando tarea.");
     * }
     * } else {
     * future.cancel(false);
     * System.out.println("Ejecución completada de método crearRonda " +
     * rondasCreadas + " veces, terminando tarea.");
     * }
     * }
     */

    public List<Partida> obtenerPartidas() {
        return partidaRepo.findAll();
    }

    public ResponseEntity<Long> crearPartida(PartidaDTO partidaDTO) {
        Optional<Playlist> playlistOptional = playlistRepo.findById(partidaDTO.getPlaylistID());
        Optional<Usuario> usuarioOptional = ur.findById(partidaDTO.getUsuarioID());

        if (playlistOptional.isPresent() && usuarioOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            Usuario usuario = usuarioOptional.get();

            Partida partida = new Partida(partidaDTO.getNumMaxUsuarios(), new ArrayList<>(Arrays.asList(usuario)),
                    partidaDTO.getNumMaxRondas(), new ArrayList<>(), playlist, partidaDTO.isPrivada(),
                    partidaDTO.getCodigoAcceso());

            partidaRepo.save(partida);
            return new ResponseEntity<>(partida.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> anhadirUsuario(Long partidaID, Long usuarioID) {
        Optional<Partida> partidaOptional = partidaRepo.findById(partidaID);
        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);

        if (partidaOptional.isPresent() && usuarioOptional.isPresent()) {
            Partida partida = partidaOptional.get();
            Usuario usuario = usuarioOptional.get();

            if (partida.anhadirUsuario(usuario)) {
                // Tras añadir al usuario, comprobar si la partida se ha llenado
                if (partida.getUsuarios().size() == partida.getNumMaxUsuarios()) {
                    // Si la partida se ha llenado, se inicia
                    iniciarPartidaAsync(partida);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    protected void iniciarPartida(Partida partida) {
        partida.iniciarPartida();
    }

    @Async
    protected void iniciarPartidaAsync(Partida partida) {
        iniciarPartida(partida);
    }

    public ResponseEntity<Void> iniciarPartidaPorAnfitrion(Long partidaID, Long usuarioID) {
        Optional<Partida> partidaOptional = partidaRepo.findById(partidaID);
        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);

        if (partidaOptional.isPresent() && usuarioOptional.isPresent()) {
            Partida partida = partidaOptional.get();
            Usuario usuario = usuarioOptional.get();

            // Comprobar que el usuario que inicia la partida es el anfitrion
            if (partida.getUsuarios().get(0) == usuario) {
                // Si es el anfitrion se inicia la partida
                iniciarPartida(partida);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
