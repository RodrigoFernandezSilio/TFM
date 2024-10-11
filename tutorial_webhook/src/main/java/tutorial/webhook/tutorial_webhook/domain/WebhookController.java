package tutorial.webhook.tutorial_webhook.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    @PostMapping
    public ResponseEntity<String> registerWebhook(@RequestBody String webhookUrl) {
        // Implement webhook registration logic
        webhookService.registerWebhook(webhookUrl);
        return ResponseEntity.ok("Webhook registered successfully");
    }

    // Add other endpoints for webhook management
}