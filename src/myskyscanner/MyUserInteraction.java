package myskyscanner;

import java.util.Scanner;

import password.PasswordEncryption;

public class MyUserInteraction {

    private MyUserDatabase accounts;
    private String currentUser;

    public MyUserInteraction(MyUserDatabase accounts) {
        this.accounts = accounts;
        currentUser = null;
    }

    public void printUsers() {
        accounts.printUsers();
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

    private void signIn(String username) {
        currentUser = username;
        System.out.println("Welcome, " + username);
    }

    private boolean signUp(String username, char[] password, String email, String firstName,
            String lastName, String phone) {
        if (accounts.addUser(username, password, email, firstName, lastName, phone)) {
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

    private void getSignInInfo(Scanner scanner) {
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
            signIn(username);
        }
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
        signUp(username, password, email, firstname, lastname, phone);
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

    private void getUserInput(Scanner scanner, String command) {
        switch (command) {
            case "signIn": {
                getSignInInfo(scanner);
                break;
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
            case "print": {
                printUsers();
                break;
            }
            default: {
                System.out.println("MySkyscanner does not support such option, yet!");
            }
        }
    }

    public void getUserCommand(Scanner scanner, String command) {
        getUserInput(scanner, command);
    }
}
