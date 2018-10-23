package myskyscanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MyUserDatabase {

    private Map<String, MyUser> accounts;

    public MyUserDatabase() {
        accounts = new HashMap<String, MyUser>();
    }

    public boolean addUser(String username, char[] password, String email, String firstName,
            String lastName, String phone) {
        MyUser toAdd = new MyUser(username, password, email, firstName, lastName, phone);
        if (accounts.putIfAbsent(username, toAdd) == null) {
            return true;
        }
        return false;
    }

    public boolean delete(String username) {
        return accounts.remove(username) != null;
    }

    public boolean hasUser(String username) {
        return accounts.containsKey(username);
    }

    public boolean checkPass(String username, char[] password) {
        return accounts.get(username).validatePass(password);
    }

    public void printUsers() {
        for (String s : accounts.keySet()) {
            System.out.println(s);
        }
    }

    public void showUserInfo(String username) {
        accounts.get(username).print();
    }

    public void saveData() {
        File file = new File(".\\database.dat");
        try (ObjectOutputStream output = new ObjectOutputStream(
                new GZIPOutputStream(new FileOutputStream(file)))) {
            output.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }
    }

    public void loadData() throws IOException {
        File file = new File(".\\database.dat");
        Object readObject;
        try (ObjectInputStream input = new ObjectInputStream(
                new GZIPInputStream(new FileInputStream(file)));) {
            readObject = input.readObject();
            if (readObject instanceof HashMap) {
                accounts = (HashMap<String, MyUser>) readObject;
            } else {
                throw new IOException("Data is not a hashmap");
            }
        } catch (ClassNotFoundException e) {
        }
    }
}
