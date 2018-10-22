package myskyscanner;

import java.io.IOException;
import java.util.Scanner;

public class MySkyscanner {

    private MyUserDatabase accounts;
    private MyUserInteraction interact;
    private boolean quit;

    public MySkyscanner() {
        quit = false;
        accounts = new MyUserDatabase();
        try {
            accounts.loadData();
        } catch (IOException e) {
            System.out.println("Data couldnot be loaded");
        }
        interact = new MyUserInteraction(accounts);
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
            accounts.saveData();
        } else {
            interact.getUserCommand(scanner, command);
        }
    }

}
