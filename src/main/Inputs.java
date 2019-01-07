package main;

import java.util.Scanner;

public class Inputs {
    Scanner scan;

    public Inputs(Scanner scan) {
        this.scan = scan;
    }

    // Methods to easily get input
    public int getIntInput() {
        return getIntInput(0, 0);
    }

    public int getIntInput(String msg) {
        return getIntInput(msg, 0, 0);
    }

    public int getIntInput(int start, int end) {
        return getIntInput("Choose", start, end);
    }

    public int getIntInput(String msg, int start, int end) {
        int input;
        if (end - start != 0) {
            System.out.printf("%s: (%d-%d):\n", msg, start, end);
        } else {
            System.out.printf("%s:\n", msg);
        }
        input = Integer.parseInt(scan.nextLine());

        return input;
    }

    public String getStringInput() {
        return getStringInput(0, 0);
    }

    public String getStringInput(String msg) {
        return getStringInput(msg, 0, 0);
    }

    public String getStringInput(int start, int end) {
        return getStringInput("Choose", start, end);
    }

    public String getStringInput(String msg, int start, int end) {
        String input;
        if (end - start != 0) {
            System.out.printf("%s: (%d-%d):\n", msg, start, end);
        } else {
            System.out.printf("%s:\n", msg);
        }
        input = scan.nextLine();

        return input;
    }
}
