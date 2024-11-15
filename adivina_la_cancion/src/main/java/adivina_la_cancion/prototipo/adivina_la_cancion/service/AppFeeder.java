package adivina_la_cancion.prototipo.adivina_la_cancion.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Cancion;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Playlist;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PartidaRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PlaylistRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.UsuarioRepository;


@Component
public class AppFeeder implements CommandLineRunner {

    @Autowired
    protected PartidaRepository pr;

    @Autowired
    protected PlaylistRepository plr;

    @Autowired
    protected UsuarioRepository ur;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        feedPlaylists();

        System.out.println("Application feeded\n\n");
    }

    private void feedPlaylists() {
        Cancion cancion1 = new Cancion("Nombre de cancion1", "audioURL de cancion1");
        Cancion cancion2 = new Cancion("Nombre de cancion2", "audioURL de cancion2");
        Cancion cancion3 = new Cancion("Nombre de cancion3", "audioURL de cancion3");
        Cancion cancion4 = new Cancion("Nombre de cancion4", "audioURL de cancion4");

        Playlist playlist1 = new Playlist("Nombre de playlist1", new HashSet<>(Arrays.asList(cancion1, cancion2, cancion3, cancion4)));

        plr.save(playlist1);
    }

}
