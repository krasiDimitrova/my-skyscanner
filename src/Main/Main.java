package Main;

import java.util.Scanner;

import myskyscanner.MySkyscanner;

public class Main {

    public static void main(String[] args) {
        MySkyscanner test = new MySkyscanner();
        Scanner scan = new Scanner(System.in);
        while (!test.getQuitStatus()) {
            test.getUserInput(scan);
        }
    }
}
