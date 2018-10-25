package myskyscanner;

import java.util.Scanner;

public class MySkyscanner {

    private MyUserDatabaseInteraction databaseInteract;
    private MyUserFlightDatabaseInteraction flightDatabase;
    private boolean quit;

    public MySkyscanner() {
        quit = false;
        databaseInteract = new MyUserDatabaseInteraction();
        flightDatabase = new MyUserFlightDatabaseInteraction();
        System.out.println("Welcome to MySkyscanner." + "\nIf you are a new user, you can signUp."
                + "\nIf you already have an account go ahead and signIn!"
                + "\nCurrently available commands are:"
                + "\nsignUp, signIn, show, deleteMyProfile, logout, quit "
                + "\nshowFlightsBetween, showFlight and the admin options addFlight, removeFlight");
    }

    public boolean getQuitStatus() {
        return quit;
    }

    public void getUserInput(Scanner scanner) {
        System.out.println(">");
        String command = scanner.nextLine();
        if (command.equals("quit")) {
            quit = true;
            databaseInteract.saveData();
            flightDatabase.saveData();
        } else if (command.equals("signUp") || command.equals("signIn") || command.equals("show")
                || command.equals("deleteMyProfile") || command.equals("logout")
                || command.equals("print")) {
            databaseInteract.getUserCommand(scanner, command);
        } else if (command.equals("addFlight") || command.equals("removeFlight")) {
            if (databaseInteract.currentUserIsAdmin()) {
                flightDatabase.getUserCommand(scanner, command, databaseInteract.getCurUsername());
            } else {
                System.out.println("You do not have admin rights and cannot use this options!");
            }
        } else if (command.equals("showFlightsBetween") || command.equals("showFlight")) {
            flightDatabase.getUserCommand(scanner, command, databaseInteract.getCurUsername());
        } else {
            System.out.println("MySkyscanner does not support such option, yet!");
        }
    }

}
