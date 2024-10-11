package tutorial.webhook.tutorial_webhook.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookRepository extends JpaRepository<Webhook, Long> {

    
}
