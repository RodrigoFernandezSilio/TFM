package adivina_la_cancion.prototipo.adivina_la_cancion.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    // https://www.baeldung.com/jpa-persisting-enums-in-jpa#string
    @Enumerated(EnumType.STRING)
    private EstadoPartida estado;

    private int numMaxUsuarios;

    @ManyToMany
    private List<Usuario> usuarios;

    private int numMaxRondas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ronda> rondas;

    @ManyToOne
    private Playlist playlist;

    public Partida() {
    }

    public Partida(int numMaxUsuarios, List<Usuario> usuarios, int numMaxRondas, List<Ronda> rondas, Playlist playlist) {
        this.numMaxUsuarios = numMaxUsuarios;
        this.usuarios = usuarios;
        this.numMaxRondas = numMaxRondas;
        this.rondas = rondas;
        this.playlist = playlist;
    }

    public long getId() {
        return id;
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
    }

    public int getNumMaxUsuarios() {
        return numMaxUsuarios;
    }

    public void setNumMaxUsuarios(int numMaxUsuarios) {
        this.numMaxUsuarios = numMaxUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getNumMaxRondas() {
        return numMaxRondas;
    }

    public void setNumMaxRondas(int numMaxRondas) {
        this.numMaxRondas = numMaxRondas;
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
        } else {
            return null;
        }
    }

    /**
     * Añade un usuario a la partida.
     * 
     * Este método añade el usuario a la partida si la partida no está iniciada,
     * la partida no ha alcanzado el número máximo de usuarios y el usuario no se ha
     * unido ya.
     * 
     * @param usuario El usuario que se añade a la partida
     * @return true si se añade el usuario, false sino
     */
    public boolean anhadirUsuario(Usuario usuario) {
        if (estado == EstadoPartida.NO_INICIADA && usuarios.size() < numMaxUsuarios && !usuarios.contains(usuario)) {
            usuarios.add(usuario);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Genera las rondas de la partida y actualiza el estado de la partida a "INICIADA".
     */
    public void iniciarPartida() {
        generarRondas();
        estado = EstadoPartida.INICIADA;
    }

    /**
     * Genera las rondas de la partida.
     * 
     * Cada ronda se compone de un conjunto de canciones (4 seleccionadas aleatoriamente)
     * y una canción correcta elegida al azar de esas 4.
     */
    private void generarRondas() {
        Random random = new Random();

        // Obtener todas las canciones de la playlist
        List<Cancion> todasLasCanciones = new ArrayList<Cancion>(playlist.getCanciones());

        for (int numRonda = 0; numRonda < numMaxRondas; numRonda++) {
            // Barajar las canciones para obtener 4 al azar
            Collections.shuffle(todasLasCanciones);
            List<Cancion> cancionesSeleccionadas = todasLasCanciones.subList(0, 4);

            // Elegir una canción correcta al azar de las 4 seleccionadas
            int indiceCancionCorrecta = random.nextInt(4); // Genera un número entre 0 (incluido) y 4 (excluido)
            Cancion cancionCorrecta = cancionesSeleccionadas.get(indiceCancionCorrecta);

            // Crear la nueva ronda
            Ronda ronda = new Ronda(cancionesSeleccionadas, cancionCorrecta, new ArrayList<>());

            // Añadir la ronda a la lista de rondas
            rondas.add(ronda);
        }
    }
}
