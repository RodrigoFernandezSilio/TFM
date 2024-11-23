package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import adivina_la_cancion.prototipo.adivina_la_cancion.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PartidaPreview.class})
    private long id;

    @NonNull
    @JsonView({Views.PartidaPreview.class})
    private String nombre;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Cancion> canciones;
}
