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

@Entity
public class Ronda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<Cancion> canciones;

    @ManyToOne
    private Cancion cancionCorrecta;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    private Instant inicioRonda;

    public Ronda() {

    }

    public Ronda(List<Cancion> canciones, Cancion cancionCorrecta, List<Respuesta> respuestas) {
        this.canciones = canciones;
        this.cancionCorrecta = cancionCorrecta;
        this.respuestas = respuestas;
        this.inicioRonda = Instant.now();
    }

    public long getId() {
        return id;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Cancion getCancionCorrecta() {
        return cancionCorrecta;
    }

    public void setCancionCorrecta(Cancion cancionCorrecta) {
        this.cancionCorrecta = cancionCorrecta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Instant getInicioRonda() {
        return inicioRonda;
    }

    public void setInicioRonda(Instant inicioRonda) {
        this.inicioRonda = inicioRonda;
    }
}
