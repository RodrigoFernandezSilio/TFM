package adivina_la_cancion.prototipo.adivina_la_cancion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class PartidaHandler extends TextWebSocketHandler {

    /**
     * CopyOnWriteArrayList:
     * Tipo de lista diseñada para ser eficiente en entornos donde hay concurrencia,
     * pero no en constante modificación
     */
    // private final CopyOnWriteArrayList<WebSocketSession> sessions = new
    // CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<Long, List<WebSocketSession>> sesiones = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        ConcurrentHashMap<String, Object> attributes = (ConcurrentHashMap<String, Object>) session.getAttributes();

        // Obtener la lista de sesiones asociadas con el partidaID
        Long partidaID = (Long) attributes.get("partidaID");
        List<WebSocketSession> sesionesPartida = sesiones.get(partidaID);

        // Comprobar si no existe una lista de sesiones para el partidaID
        if (sesionesPartida == null) {
            // Crear la lista de sesiones para el partidaID
            sesionesPartida = new ArrayList<>();
            sesiones.put(partidaID, sesionesPartida);
        }

        // Agregar la sesion actual a la lista correspondiente
        sesionesPartida.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        
        ConcurrentHashMap<String, Object> attributes = (ConcurrentHashMap<String, Object>) session.getAttributes();

        // Obtener la lista de sesiones asociadas con el partidaID
        Long partidaID = (Long) attributes.get("partidaID");

        // Eliminar la sesion de la lista de sesiones de esa partida
        List<WebSocketSession> sesionesPartida = sesiones.get(partidaID);
        if (sesionesPartida != null) {
            sesionesPartida.remove(session);
            if (sesionesPartida.isEmpty()) {
                sesiones.remove(partidaID); // Eliminar la entrada del mapa si no quedan mas sesiones
            }
        }
    }

    /**
     * Cuando desde el cliente (navegador) llegue un mensaje aquí,
     * se va a enviar a todas las sesiones que se encuentren activas (sessiones que
     * estén en la lista)
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       
        ConcurrentHashMap<String, Object> attributes = (ConcurrentHashMap<String, Object>) session.getAttributes();

        // Obtener la lista de sesiones asociadas con el partidaID
        Long partidaID = (Long) attributes.get("partidaID");

        List<WebSocketSession> sesionesPartida = sesiones.get(partidaID);

        // Enviar el mensaje a todas las sesiones conectadas a esta partida
        if (sesionesPartida != null) {
            for (WebSocketSession partidaSession : sesionesPartida) {
                partidaSession.sendMessage(message);
            }
        }
    }
}
