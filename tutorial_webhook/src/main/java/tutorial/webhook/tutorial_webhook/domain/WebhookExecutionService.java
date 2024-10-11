package tutorial.webhook.tutorial_webhook.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookExecutionService {

    @Autowired
    private RestTemplate restTemplate;

    public void executeWebhook(Webhook webhook, String payload) {
        System.out.println();
        System.out.println(payload);
        System.out.println();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        restTemplate.postForObject(webhook.getUrl(), request, String.class);
    }
}
