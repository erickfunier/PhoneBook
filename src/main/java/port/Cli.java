package port;

import java.util.Scanner;

public class Cli {
    private final Scanner input;

    public Cli() {
        input = new Scanner(System.in);
    }

    public String getCommand() {
        return input.nextLine().toLowerCase();
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printMenu() {
        System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
    }

    public void printAddMenu() {
        System.out.print("Enter the type (person, organization): ");
    }

    public String getString() { return input.nextLine(); }

}
