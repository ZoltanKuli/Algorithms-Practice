package logic.algorithms.sort;

import java.util.ArrayList;

public class InsertionSort extends Sort {
    public InsertionSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public InsertionSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);

        for (int i = 1; i < sortedLongs.size(); i++) {
            Long longToBeInserted = sortedLongs.get(i);
            int j = i;

            // Inserts the current element into the proper position in the sorted part of the ArrayList
            for ( ; j > 0 && sortedLongs.get(j - 1) > longToBeInserted; j--) {
                sortedLongs.set(j, sortedLongs.get(j - 1));
            }

            sortedLongs.set(j, longToBeInserted);
        }

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }
}
