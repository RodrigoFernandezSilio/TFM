package adivina_la_cancion.prototipo.adivina_la_cancion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("SELECT p FROM Partida p LEFT JOIN FETCH p.usuarios WHERE p.id = :id")
    Partida findByIdWithUsuarios(Long id);

}
