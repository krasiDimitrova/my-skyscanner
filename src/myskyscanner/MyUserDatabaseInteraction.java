package myskyscanner;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import myskyscanner.user.MyUserDatabase;
import myskyscanner.user.Role;
import password.PasswordEncryption;

public class MyUserDatabaseInteraction {

    private MyUserDatabase accounts;
    private String currentUser;

    public MyUserDatabaseInteraction() {
        accounts = new MyUserDatabase();
        try {
            accounts.loadData();
        } catch (IOException e) {
            System.out.println("Data couldnot be loaded");
        }
        currentUser = null;
    }

    public boolean currentUserIsAdmin() {
        return accounts.userIsAdmin(currentUser);
    }

    public void saveData() {
        accounts.saveData();
    }

    public void printUsers() {
        accounts.printUsers();
    }

    public String getCurUsername() {
        return currentUser;
    }

    private char[] encryptPass(char[] pass) {
        return PasswordEncryption.encryptPassword(pass);
    }

    private boolean hasUserName(String username) {
        return accounts.hasUser(username);
    }

    private boolean verifyPass(String username, char[] pass) {
        return accounts.checkPass(username, pass);
    }

    private boolean signIn(String username) {
        currentUser = username;
        System.out.println("Welcome, " + username);
        return true;
    }

    private boolean signUp(String username, char[] password, Role role, String email,
            String firstName, String lastName, String phone) {
        if (accounts.addUser(username, password, role, email, firstName, lastName, phone)) {
            System.out.println("Your account is ready. Sign in");
            return true;
        } else {
            System.out.println("Try again!");
            return false;
        }
    }

    private boolean deleteAccount() {

        if (accounts.delete(currentUser)) {
            System.out.println("Sorry, " + currentUser);
            logout();
            return true;
        } else {
            System.out.println("Somethinh happend");
            return false;
        }

    }

    private void logout() {
        System.out.println("Goodbye, " + currentUser);
        this.currentUser = null;
    }

    private boolean getPass(Scanner scanner, String username) {
        System.out.println("Enter password: ");
        char[] password;
        password = scanner.nextLine().toCharArray();
        password = encryptPass(password);
        int count = 1;
        while (!verifyPass(username, password) && count <= 3) {
            System.out.println("Invalid password! Try again! /n Enter password: ");
            password = scanner.nextLine().toCharArray();
            password = encryptPass(password);
            count++;
        }
        if (count == 4) {
            return false;
        } else {
            return true;
        }
    }

    private boolean getSignInInfo(Scanner scanner) {
        System.out.println("Enter username: ");
        String username;
        username = scanner.nextLine();
        while (!hasUserName(username)) {
            System.out.println("Invalid username! Try again! /n Enter username: ");
            username = scanner.nextLine();
        }
        if (!getPass(scanner, username)) {
            System.out.println("Invalid password! If you do not own this account create new one!");
        } else {
            return signIn(username);
        }
        return false;
    }

    private void getSignUpInfo(Scanner scanner) {
        System.out.println("Enter username: ");
        String username;
        username = scanner.nextLine();
        while (hasUserName(username)) {
            System.out.println("Username already exists! Try again! /n Enter username: ");
            username = scanner.nextLine();
        }
        System.out.println("Enter password: ");
        char[] password;
        password = scanner.nextLine().toCharArray();
        password = encryptPass(password);
        System.out.println("Choose role. Enter c for customer or a for admin:");
        String r = scanner.nextLine();
        Role role;
        if (r.equals("a")) {
            role = Role.Admin;
        } else {
            role = Role.Customer;
        }
        System.out.println("The next fields are optional. If you want to pass them press enter");
        System.out.println("Enter email: ");
        String email;
        email = scanner.nextLine();
        System.out.println("Enter First name: ");
        String firstname;
        firstname = scanner.nextLine();
        System.out.println("Enter Last name: ");
        String lastname;
        lastname = scanner.nextLine();
        System.out.println("Enter Phone: ");
        String phone;
        phone = scanner.nextLine();
        signUp(username, password, role, email, firstname, lastname, phone);
    }

    private void getDeletePass(Scanner scanner) {
        if (!getPass(scanner, currentUser)) {
            System.out.println(
                    "Invalid password! If you do not own this account you cannot delete it!");
        } else {
            deleteAccount();
        }
    }

    private void showUserInfo() {
        accounts.showUserInfo(currentUser);
    }

    private void notifyMeAbout(Scanner scanner) {
        System.out.println("Enter departure point:");
        String from = scanner.nextLine();
        System.out.println("Enter arrival point:");
        String to = scanner.nextLine();
        accounts.addStartEndLoc(currentUser, from, to);
    }

    private boolean getUserInput(Scanner scanner, String command) {
        switch (command) {
            case "signIn": {
                return getSignInInfo(scanner);
            }
            case "logout": {
                logout();
                break;
            }
            case "deleteMyProfile": {
                getDeletePass(scanner);
                break;
            }
            case "signUp": {
                getSignUpInfo(scanner);
                break;
            }
            case "show": {
                showUserInfo();
                break;
            }
            case "notifyMeAbout": {
                notifyMeAbout(scanner);
                break;
            }
            case "print": {
                printUsers();
                break;
            }
            default: {
                break;
            }
        }
        return false;
    }

    public boolean getUserCommand(Scanner scanner, String command) {
        return getUserInput(scanner, command);
    }

    public List<String> getNotifyList() {
        return accounts.getLocations(currentUser);
    }
}
