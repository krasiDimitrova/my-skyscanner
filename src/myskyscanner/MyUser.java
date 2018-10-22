package myskyscanner;

import java.io.Serializable;
import java.util.Arrays;

public class MyUser implements Serializable {

    private static final long serialVersionUID = -2043085192957959900L;
    private String username;
    private char[] password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public MyUser(String username, char[] password, String email, String firstName, String lastName,
            String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public void print() {
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        if (firstName != null) {
            System.out.println("First name " + firstName);
        }
        if (lastName != null) {
            System.out.println("Last name " + lastName);
        }
        if (phone != null) {
            System.out.println("Phone " + phone);
        }
    }

    public boolean validatePass(char[] passToCheck) {
        return Arrays.equals(password, passToCheck);
    }
}
