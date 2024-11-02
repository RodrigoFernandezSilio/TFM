package tutorial.websockets.tutorial_websockets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import tutorial.websockets.tutorial_websockets.service.ChatHandler;

/* Clase donde se define el Socket o servidor Socket */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Indicar en que ubicacion se encuentra el WebSocket
        registry.addHandler(chatHandler, "/chat");
    }

}
