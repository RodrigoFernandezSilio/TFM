package adivina_la_cancion.prototipo.adivina_la_cancion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
