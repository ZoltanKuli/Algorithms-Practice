package logic.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Writer implements Runnable {
    private Thread thread;
    private String threadName;
    private String path;
    private ArrayList<Long> unsortedLongs;
    private ArrayList<Long> sortedLongs;

    public Writer(String path, String sortName, long sortTimeMillis,
                  ArrayList<Long> unsortedLongs, ArrayList<Long> sortedLongs) {
        // Gets the current date and time, and initializes its formatter
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.yyyy, h.mm.ss a");

        // Gives a unique name to the current thread
        // Uses the sorting algorithm's name, the size of unsortedLongs, sortTimeMillis, and the current date and time
        // Uses the same for the name of the file run() writes to and the file's header line
        threadName = String.format("%s (%d Longs in %d millis) - %s", sortName, unsortedLongs.size(),
                sortTimeMillis, dateTimeFormatter.format(now));
        this.path = path + "\\" + threadName + ".txt";

        this.unsortedLongs = new ArrayList<>();
        this.unsortedLongs.addAll(unsortedLongs);
        this.sortedLongs = new ArrayList<>();
        this.sortedLongs.addAll(sortedLongs);
    }

    // Responsible for starting the writing processes
    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    // Halts the thread it was called from until writing processes are finished
    public void join() {
        try {
            thread.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Writes to a new file with a predefined name
    // Writes information and both the unsorted and sorted longs
    @Override
    public void run() {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                FileWriter myWriter = new FileWriter(path);

                myWriter.write(threadName);

                myWriter.write("\n\nUnsorted Longs:\n");

                for (Long singleLong: unsortedLongs) {
                    myWriter.write(singleLong + "\n");
                }

                myWriter.write("\nSorted Longs:\n");

                for (Long singleLong: sortedLongs) {
                    myWriter.write(singleLong + "\n");
                }

                myWriter.close();
            } else {
                throw new IOException(String.format("   %s.txt already exists", threadName));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
