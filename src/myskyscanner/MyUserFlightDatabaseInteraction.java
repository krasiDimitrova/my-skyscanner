package myskyscanner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

import myskyscanner.flight.MyFlightDatabase;

public class MyUserFlightDatabaseInteraction {

    private MyFlightDatabase flights;

    public MyUserFlightDatabaseInteraction() {
        flights = new MyFlightDatabase();
        try {
            flights.loadData();
        } catch (IOException e) {
            System.out.println("Data couldnot be loaded");
        }
    }

    public void saveData() {
        flights.saveData();
    }

    private boolean addFlight(String airline, String fromCity, String toCity,
            LocalDateTime departure, LocalDateTime arrival, double minPrice, int availableSeats,
            int takenSeats, String cUsername) {
        try {
            flights.addFlight(airline, fromCity, toCity, departure, arrival, minPrice,
                    availableSeats, takenSeats, cUsername);
        } catch (InvalidAttributesException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean removeFlight(String flightID) {
        try {
            flights.removeFlight(flightID);
        } catch (InvalidAttributesException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void showFlightsBetween(String from, String to) {
        flights.printFlightFromTo(from, to);
    }

    private void showFlight(String flightID) {
        flights.printFlight(flightID);
    }

    private void getAddFlightInfo(Scanner scanner, String cUsername) {
        try {
            System.out.println("Enter the airline:");
            String airline = scanner.nextLine();
            System.out.println("Enter departure city:");
            String fromCity = scanner.nextLine();
            System.out.println("Enter city of arrival: ");
            String toCity = scanner.nextLine();
            System.out.println("Enter departure time in format yyyy-MM-dd HH:mm: ");
            String departureT = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime departure = LocalDateTime.parse(departureT, formatter);
            System.out.println("Enter arrival time in format yyyy-MM-dd HH:mm: ");
            String arrivalT = scanner.nextLine();
            LocalDateTime arrival = LocalDateTime.parse(arrivalT, formatter);
            System.out.println("Minimal price:");
            double minPrice = scanner.nextDouble();
            System.out.println("Available seats:");
            int availableSeats = scanner.nextInt();
            addFlight(airline, fromCity, toCity, departure, arrival, minPrice, availableSeats, 0,
                    cUsername);
        } catch (Exception e) {
            System.out.println("Try again to enter a flightSi");
        }
    }

    private void getUserInput(Scanner scanner, String command, String cUsername) {
        switch (command) {
            case "addFlight": {
                getAddFlightInfo(scanner, cUsername);
                break;
            }
            case "removeFlight": {
                System.out.println("Enter flight ID: ");
                String FlightID = scanner.nextLine();
                removeFlight(FlightID);
                break;
            }
            case "showFlightsBetween": {
                System.out.println("Enter city for departure: ");
                String fromCity = scanner.nextLine();
                System.out.println("Enter city for arrival: ");
                String toCity = scanner.nextLine();
                showFlightsBetween(fromCity, toCity);
                break;
            }
            case "showFlight": {
                System.out.println("Enter flight ID: ");
                String FlightID = scanner.nextLine();
                showFlight(FlightID);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void getUserCommand(Scanner scanner, String command, String cUsername) {
        getUserInput(scanner, command, cUsername);
    }

    public void printNotifyMeAbout(List<String> locations) {
        for (int i = 0; i < locations.size(); i += 2)
            flights.notifyAboutFlights(locations.get(i), locations.get(i + 1));
    }
}
