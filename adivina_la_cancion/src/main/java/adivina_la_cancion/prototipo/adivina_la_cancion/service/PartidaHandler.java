package adivina_la_cancion.prototipo.adivina_la_cancion.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class PartidaHandler extends TextWebSocketHandler {

    /**
     * CopyOnWriteArrayList:
     * Tipo de lista diseñada para ser eficiente en entornos donde hay concurrencia, pero no en constante modificación
     */
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    /**
     * Cuando desde el cliente (navegador) llegue un mensaje aquí, 
     * se va a enviar a todas las sesiones que se encuentren activas (sessiones que estén en la lista)
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(message);
        }
    }


}
