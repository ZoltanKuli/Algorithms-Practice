package logic.utilities;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputScanner {
    // Reads any input
    public static String readString() {
        return new Scanner(System.in).nextLine();
    }

    public static long readLong(String msg, String errorMsg) {
        long input = 0;

        boolean isSuccessful = false;
        Scanner scanner = new Scanner(System.in);

        ConsoleCleaner.clear();
        System.out.print(msg);
        while (!isSuccessful) {
            try {
                input = scanner.nextLong();

                isSuccessful = true;
            } catch (NumberFormatException | InputMismatchException exception) {
                scanner.next();

                ConsoleCleaner.clear();
                System.out.print(errorMsg);
                System.out.print(msg);
            }
        }

        return input;
    }

    public static int readInt(String msg, String errorMsg) {
        int input = 0;

        boolean isSuccessful = false;
        Scanner scanner = new Scanner(System.in);

        ConsoleCleaner.clear();
        System.out.print(msg);
        while (!isSuccessful) {
            try {
                input = scanner.nextInt();

                isSuccessful = true;
            } catch (NumberFormatException | InputMismatchException exception) {
                scanner.next();

                ConsoleCleaner.clear();
                System.out.print(errorMsg);
                System.out.print(msg);
            }
        }

        return input;
    }

    // Waits for the user to press enter
    // Prevents displaying input characters
    public static void waitForEnter(String message) {
        System.out.print(message);

        Console console = System.console();

        if (console != null) {
            console.readPassword("");
        } else {
            readString();
        }
    }

    public static int parseInd(String input) {
        int index;

        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException exception) {
            index = -1;
        }

        return index;
    }
}
