package adivina_la_cancion.prototipo.adivina_la_cancion.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Playlist;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PlaylistRepository;


@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    protected PlaylistRepository plr;

    @GetMapping
    public ResponseEntity<List<Playlist>> obtenerPlaylists() {
        return ResponseEntity.ok(plr.findAll());
    }
}
