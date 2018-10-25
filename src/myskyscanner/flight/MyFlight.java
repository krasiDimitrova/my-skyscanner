package myskyscanner.flight;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyFlight implements Serializable {

    private static final long serialVersionUID = -8385346820002722817L;
    private String flightID;
    private String airline;
    private String fromCity;
    private String toCity;
    private LocalDateTime departure;
    private Duration duration;
    private LocalDateTime arrival;
    private double minPrice;
    private int availableSeats;
    private int takenSeats;
    private String addedBy;

    public MyFlight(String flightID, String airline, String fromCity, String toCity,
            LocalDateTime departure, Duration duration, LocalDateTime arrival, double minPrice,
            int availableSeats, int takenSeats, String addedBy) {
        super();
        this.flightID = flightID;
        this.airline = airline;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.departure = departure;
        this.duration = duration;
        this.arrival = arrival;
        this.minPrice = minPrice;
        this.availableSeats = availableSeats;
        this.takenSeats = takenSeats;
        this.addedBy = addedBy;
    }

    public String getFrom() {
        return fromCity;
    }

    public String getTo() {
        return toCity;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void printFLightInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("FLight ID: " + flightID);
        System.out.println("Airline: " + airline);
        System.out.println("From city: " + fromCity);
        System.out.println("To city: " + toCity);
        System.out.println("Departure at: " + departure.format(formatter));
        System.out.println("Arrival at: " + arrival.format(formatter));
        System.out.println("Duration: " + duration.toMinutes() + " minutes");
        System.out.println("Minimal price: " + minPrice);
        System.out.println("Available seats: " + availableSeats);
    }

    public void printFLightMinInfo() {
        System.out.println("FLight ID: " + flightID);
        System.out.println("Minimal price: " + minPrice);
    }
}
