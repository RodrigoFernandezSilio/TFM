package tutorial.webhook.tutorial_webhook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import tutorial.webhook.tutorial_webhook.domain.Flight;
import tutorial.webhook.tutorial_webhook.domain.FlightRepository;

@Component
public class AppFeeder implements CommandLineRunner {

    @Autowired
	protected FlightRepository fr;

	@Override
	@Transactional
    public void run(String... args) throws Exception {
        feedFlights();

        System.out.println("\n\nApplication feeded\n\n");
    }

    private void feedFlights() {
        Flight f1 = new Flight("Madrid", "Barcelona");

        fr.save(f1);
    }

}
