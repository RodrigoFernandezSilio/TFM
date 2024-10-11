package tutorial.webhook.tutorial_webhook.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        // Implement logic to get a list of flights
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<String> addOrUpdateFlight(@RequestBody Flight flight) {
        flightService.addOrUpdateFlight(flight);
        return ResponseEntity.ok("Flight added or updated successfully");
    }

    // Add other endpoints for flight management
}
