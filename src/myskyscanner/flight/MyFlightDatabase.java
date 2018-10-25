package myskyscanner.flight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.naming.directory.InvalidAttributesException;

public class MyFlightDatabase {

    private Map<String, MyFlight> flights;

    public MyFlightDatabase() {
        flights = new HashMap<String, MyFlight>();
    }

    public boolean addFlight(String airline, String fromCity, String toCity,
            LocalDateTime departure, LocalDateTime arrival, double minPrice, int availableSeats,
            int takenSeats, String addedBy) throws InvalidAttributesException {
        Duration duration = Duration.between(departure, arrival);
        String flightID = airline.substring(0, 1) + fromCity.charAt(0) + departure.getDayOfMonth()
                + duration.toMinutes() + arrival.getHour() + toCity.charAt(0);
        MyFlight toAdd = new MyFlight(flightID, airline, fromCity, toCity, departure, duration,
                arrival, minPrice, availableSeats, takenSeats, addedBy);
        if (flights.putIfAbsent(flightID, toAdd) == null) {
            return true;
        } else {
            throw new InvalidAttributesException("Such Flight already exists!");
        }
    }

    public boolean removeFlight(String flightID) throws InvalidAttributesException {
        if (flights.remove(flightID) != null) {
            return true;
        } else {
            throw new InvalidAttributesException(
                    "Flight with id: " + flightID + " does not exist!");
        }
    }

    public void printFlight(String flightID) {
        flights.get(flightID).printFLightInfo();
    }

    public void printFlightFromTo(String fromCity, String toCity) {
        System.out.println("Flights from " + fromCity + " to " + toCity);
        List<MyFlight> fList = new ArrayList<MyFlight>(flights.values());
        fList.stream().filter(
                flight -> flight.getFrom().equals(fromCity) && flight.getTo().equals(toCity))
                .sorted((f1, f2) -> Double.compare(f1.getMinPrice(), f2.getMinPrice()))
                .forEach(flight -> flight.printFLightMinInfo());
    }

    public void saveData() {
        File file = new File(".\\flightDatabase.dat");
        try (ObjectOutputStream output = new ObjectOutputStream(
                new GZIPOutputStream(new FileOutputStream(file)))) {
            output.writeObject(flights);
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }
    }

    public void loadData() throws IOException {
        File file = new File(".\\flightDatabase.dat");
        Object readObject;
        try (ObjectInputStream input = new ObjectInputStream(
                new GZIPInputStream(new FileInputStream(file)));) {
            readObject = input.readObject();
            if (readObject instanceof HashMap) {
                flights = (HashMap<String, MyFlight>) readObject;
            } else {
                throw new IOException("Data is not a hashmap");
            }
        } catch (ClassNotFoundException e) {
        }
    }
}
