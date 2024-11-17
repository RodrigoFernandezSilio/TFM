package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @ManyToOne
    private Usuario usuario;

    @NonNull
    @ManyToOne
    private Cancion cancionSeleccionada;

    @NonNull
    private Instant instanteRespuesta;
}
