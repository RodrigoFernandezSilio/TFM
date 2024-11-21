package adivina_la_cancion.prototipo.adivina_la_cancion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import adivina_la_cancion.prototipo.adivina_la_cancion.service.PartidaHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private PartidaHandler partidaHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Indicar en que ubicacion se encuentra el WebSocket
        registry.addHandler(partidaHandler, "/webSocketPartida");
    }

}
