package myskyscanner;

public class myUser {

    String username;
    char[] password;
    String email;
    String firstName;
    String lastName;
    String phone;

    public myUser(String username, char[] password, String email, String firstName, String lastName,
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
            System.out.println("First name" + firstName);
        }
        if (lastName != null) {
            System.out.println("Last name" + lastName);
        }
        if (phone != null) {
            System.out.println("Phone" + phone);
        }
    }
}
