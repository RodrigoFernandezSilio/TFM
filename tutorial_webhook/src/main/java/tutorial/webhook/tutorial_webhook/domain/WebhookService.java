package tutorial.webhook.tutorial_webhook.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WebhookService {

    @Autowired
    private WebhookRepository webhookRepository;

    @Autowired
    private WebhookExecutionService webhookExecutionService;

    public void registerWebhook(String url) {
        // Validación de la URL si es necesario
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("Error URL");
        }

        // Crea una nueva instancia de Webhook
        Webhook webhook = new Webhook();
        webhook.setUrl(url);
        
        // Guarda el nuevo webhook en la base de datos
        webhookRepository.save(webhook);
    }

    public void updateWebhook(Long webhookId, String newUrl) {
        // Encuentra el webhook por su ID
        Webhook webhook = webhookRepository.findById(webhookId)
                .orElseThrow(() -> new EntityNotFoundException("Webhook no encontrado"));

        // Validación de la nueva URL si es necesario
        if (newUrl == null || newUrl.isEmpty()) {
            throw new IllegalArgumentException("La nueva URL no puede estar vacía");
        }

        // Actualiza la URL del webhook
        webhook.setUrl(newUrl);
        
        // Guarda el webhook actualizado en la base de datos
        webhookRepository.save(webhook);
    }

    public void triggerUpdate(Long webhookId, String payload) {
        Webhook webhook = webhookRepository.findById(webhookId)
                .orElseThrow(() -> new EntityNotFoundException("Webhook not found"));

        webhookExecutionService.executeWebhook(webhook, payload);
    }

    public List<Webhook> getAllWebhooks() {
        return webhookRepository.findAll();
    }
    
}