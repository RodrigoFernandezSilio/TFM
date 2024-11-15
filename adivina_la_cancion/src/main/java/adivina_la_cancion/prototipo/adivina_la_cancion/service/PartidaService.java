
package adivina_la_cancion.prototipo.adivina_la_cancion.service;


import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Partida;
import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Ronda;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PartidaRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.PlaylistRepository;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;


@Service
public class PartidaService {

    @Autowired
    protected PartidaRepository pr;

    @Autowired
    protected PlaylistRepository plr;

    @Autowired
    protected UsuarioRepository ur;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Partida partidaActual;

    int rondasCreadas = 0; // Debería ser un mapa id partida y numero de rondas creadas
    private ScheduledFuture<?> future;
    private Duration INTERVALO = Duration.ofSeconds(5);




    // TODO: Solo puede iniciar partida el anfitrion
    public RespuestaService<Partida> iniciarPartidaPorAnfitrion(long partidaID) {
        Optional<Partida> partidaOptional = pr.findById(partidaID);

        if (partidaOptional.isPresent()) {
            partidaActual = partidaOptional.get();
            future = taskScheduler.scheduleAtFixedRate(() -> crearRonda(1234), INTERVALO);

            return new RespuestaService<>(true, "Partida iniciada", partidaActual);
        } else {
            return new RespuestaService<>(false, "Partida no encontrada", null);
        }
    }

    @Transactional
    private void crearRonda(int idRonda) {

        // partidaActual.crearRonda();
        // pr.save(partidaActual);
        // if (partidaActual.getRondas().size() <= 5) {
        //     try {
        //         Thread.sleep(10000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     } 
        //     crearRonda();
        // }

        if (partidaActual != null) {
            if (rondasCreadas < 5) {
                System.out.println("Ejecutando método crearRonda, ronda número " + rondasCreadas);
                System.out.println(partidaActual.getRondas().size());
                new Ronda();
                pr.save(partidaActual);
                rondasCreadas++;
            } else {
                future.cancel(false);
                System.out.println("Ejecución completada de método crearRonda " + rondasCreadas + " veces, terminando tarea.");
            }
        } else {
            future.cancel(false);
            System.out.println("Ejecución completada de método crearRonda " + rondasCreadas + " veces, terminando tarea.");
        }
    }

}
