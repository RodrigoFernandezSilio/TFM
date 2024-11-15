package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Cancion cancionSeleccionada;

    private Instant instanteRespuesta;

    public Respuesta(Usuario usuario, Cancion cancionSeleccionada, Instant instanteRespuesta) {
        this.usuario = usuario;
        this.cancionSeleccionada = cancionSeleccionada;
        this.instanteRespuesta = instanteRespuesta;
    }

    public Respuesta() {

    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancion getCancionSeleccionada() {
        return cancionSeleccionada;
    }

    public void setCancionSeleccionada(Cancion cancionSeleccionada) {
        this.cancionSeleccionada = cancionSeleccionada;
    }

    public Instant getInstanteRespuesta() {
        return instanteRespuesta;
    }

    public void setInstanteRespuesta(Instant instanteRespuesta) {
        this.instanteRespuesta = instanteRespuesta;
    }
}
