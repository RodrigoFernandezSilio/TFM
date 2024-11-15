package adivina_la_cancion.prototipo.adivina_la_cancion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

}
