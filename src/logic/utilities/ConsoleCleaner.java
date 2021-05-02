package logic.utilities;

import java.io.IOException;

public class ConsoleCleaner {
    // Clears the console/terminal based on which os the app is running on
    public static void clear() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                for (int i = 0; i < 50; ++i) {
                    System.out.println();
                }
            }
        } else {
            System.out.print("\033[H\033[2J");

            System.out.flush();
        }
    }
}
