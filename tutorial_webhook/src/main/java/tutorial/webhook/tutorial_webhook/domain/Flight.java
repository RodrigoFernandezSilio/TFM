package tutorial.webhook.tutorial_webhook.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String departure;   // Ciudad de salida
    private String destination;  // Ciudad de destino

    public Flight() {
    }

    public Flight(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}

