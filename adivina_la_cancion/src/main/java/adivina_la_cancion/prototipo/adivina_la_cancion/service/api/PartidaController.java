package adivina_la_cancion.prototipo.adivina_la_cancion.service.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Partida;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Playlist;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Usuario;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PartidaRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PlaylistRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.UsuarioRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.service.PartidaService;
import adivina_la_cancion.prototipo.adivina_la_cancion.service.RespuestaService;


@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    protected PartidaService ps;

    @Autowired
    protected PartidaRepository pr;

    @Autowired
    protected PlaylistRepository plr;

    @Autowired
    protected UsuarioRepository ur;

    @GetMapping
    public ResponseEntity<List<Partida>> obtenerPartidas() {
        return ResponseEntity.ok(pr.findAll());
    }

    // Posiblemente, cuando pase el número de rondas, si es privada, etc. tenga que pasarlo por el cuerpo
    @PostMapping("/{playlistID}")
    @Transactional
    public ResponseEntity<Partida> crearPartida(Long playlistID) {
        Optional<Playlist> playlistOptional = plr.findById(playlistID);

        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            Partida partida = new Partida(new HashSet<>(), new ArrayList<>(), playlist);
            return ResponseEntity.ok(pr.save(partida));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{partidaID}/{usuarioID}/anhadirUsuario")
    @Transactional
    public ResponseEntity<String> anhadirUsuario(Long partidaID, Long usuarioID) {
        Optional<Partida> partidaOptional = pr.findById(partidaID);
        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);

        if (partidaOptional.isPresent() && usuarioOptional.isPresent()) {
            Partida partida = partidaOptional.get();
            Usuario usuario = usuarioOptional.get();

            partida.anhadirUsuario(usuario);

            return ResponseEntity.ok("Usuario añadido a la partida");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{partidaID}/iniciarPartida")
    @Transactional
    public ResponseEntity<String> iniciarPartida(Long partidaID) {
        RespuestaService<Partida> respuestaService = ps.iniciarPartidaPorAnfitrion(partidaID);

        if (respuestaService.getExito() == true) {
            return ResponseEntity.ok(respuestaService.getMensaje());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
