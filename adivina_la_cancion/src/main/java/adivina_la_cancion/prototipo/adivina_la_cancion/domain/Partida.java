package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // 0 = NO_INICIADA, 1 = INICIADA, 2 = FINALIZADA. TODO: Usar un ENUM
    private int estado = 0;

    @ManyToMany
    private Set<Usuario> usuarios;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ronda> rondas;

    @ManyToOne
    private Playlist playlist;

    public Partida() { }

    public Partida(Set<Usuario> usuarios, List<Ronda> rondas, Playlist playlist) {
        this.usuarios = usuarios;
        this.rondas = rondas;
        this.playlist = playlist;
    }

    public long getId() {
        return id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Ronda crearRonda() {
        if (rondas.size() < 5) {
            // Obtener todas las canciones de la playlist
            List<Cancion> todasLasCanciones = new ArrayList<Cancion>(playlist.getCanciones());

            // Barajar las canciones para obtener 4 al azar
            Collections.shuffle(todasLasCanciones);
            List<Cancion> cancionesSeleccionadas = todasLasCanciones.subList(0, 4);

            // Elegir una canción correcta al azar de las 4 seleccionadas
            // TODO: Aqui mejor elegir una del 0 al 4 al azar. que no sea siempre la 0
            Collections.shuffle(cancionesSeleccionadas);
            Cancion cancionCorrecta = cancionesSeleccionadas.get(0);

            // Crear la nueva ronda
            Ronda ronda = new Ronda(cancionesSeleccionadas, cancionCorrecta, new ArrayList<>());

            // Añadir la ronda a la lista de rondas
            System.out.println("Clase: Partida. Se añade la ronda");
            rondas.add(ronda);

            return ronda;
        }
        else {
            return null;
        }
    }

    public void anhadirUsuario(Usuario usuario) {
        // TODO: Comprobar que la partida no esté empezada

        usuarios.add(usuario);
    }
}
