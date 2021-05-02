package logic.algorithms.sort;

import java.util.ArrayList;

abstract class Sort implements Runnable {
    private Thread thread;
    private String threadName;

    protected ArrayList<Long> unsortedLongs;
    protected ArrayList<Long> sortedLongs;

    protected long sortTimeMillis;

    protected Sort(ArrayList<Long> unsortedLongs) {
        threadName = this.getClass().getSimpleName() + " - " + System.currentTimeMillis();

        this.unsortedLongs = new ArrayList<>(unsortedLongs);
    }

    protected Sort() {
        threadName = this.getClass().getSimpleName() + " - " + System.currentTimeMillis();
    }

    // Responsible for starting the sorting processes
    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    // Halts the thread it was called from until sorting processes are finished
    public void join() {
        try {
            thread.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setValues(ArrayList<Long> unsortedLongs) {
        this.unsortedLongs = new ArrayList<>(unsortedLongs);

        sortTimeMillis = 0;
    }

    public ArrayList<Long> getUnsortedLongs() {
        return new ArrayList<>(unsortedLongs);
    }

    public ArrayList<Long> getSortedLongs() {
        return new ArrayList<>(sortedLongs);
    }

    public long getSortTimeMillis() {
        return sortTimeMillis;
    }

    public boolean isSet() {
        return unsortedLongs != null;
    }
}
