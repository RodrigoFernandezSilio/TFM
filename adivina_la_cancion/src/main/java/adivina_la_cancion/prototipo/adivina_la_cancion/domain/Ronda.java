package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Ronda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @ManyToMany
    private List<Cancion> canciones;

    @NonNull
    @ManyToOne
    private Cancion cancionCorrecta;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    @NonNull
    private Instant inicioRonda;
}
