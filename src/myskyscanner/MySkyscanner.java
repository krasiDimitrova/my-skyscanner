package myskyscanner;

import java.util.Scanner;

public class MySkyscanner {

    private MyUserDatabaseInteraction databaseInteract;
    private boolean quit;

    public MySkyscanner() {
        quit = false;
        databaseInteract = new MyUserDatabaseInteraction();
        System.out.println("Welcome to MySkyscanner." + "\nIf you are a new user, you can signUp."
                + "\nIf you already have an account go ahead and signIn!"
                + "\nCurrently available commands are:"
                + "\nsignUp, signIn, show, deleteMyProfile, logout, quit ");
    }

    public boolean getQuitStatus() {
        return quit;
    }

    public void getUserInput(Scanner scanner) {
        String command = scanner.nextLine();
        if (command.equals("quit")) {
            quit = true;
            databaseInteract.saveData();
        } else if (command.equals("signUp") || command.equals("signIn") || command.equals("show")
                || command.equals("deleteMyProfile") || command.equals("logout")) {
            databaseInteract.getUserCommand(scanner, command);
        } else {
            System.out.println("MySkyscanner does not support such option, yet!");
        }
    }

}
