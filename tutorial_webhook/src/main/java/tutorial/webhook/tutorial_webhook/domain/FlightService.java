package tutorial.webhook.tutorial_webhook.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private WebhookService webhookService;

    public List<Flight> getAllFlights() {
        // Implement logic to get a list of flights
        return flightRepository.findAll();
    }

    public void addOrUpdateFlight(Flight flight) {
        // Implement logic to add or update a flight
        flightRepository.save(flight);

        // Trigger webhooks with the updated flight information
        List<Webhook> webhooks = webhookService.getAllWebhooks();
        String payload = "New flight information: " + flight.toString();

        for (Webhook webhook : webhooks) {
            webhookService.triggerUpdate(webhook.getId(), payload);
        }
    }
}
