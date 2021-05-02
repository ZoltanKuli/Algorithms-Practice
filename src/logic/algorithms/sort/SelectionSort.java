package logic.algorithms.sort;

import java.util.ArrayList;

public class SelectionSort extends Sort {
    public SelectionSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public SelectionSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);

        for (int i = 0; i < sortedLongs.size() - 1; i++) {
            int minLongIndex = i;

            // Selects the smallest element from the unsorted part of the ArrayList
            for (int j = i + 1; j < sortedLongs.size(); j++) {
                if (sortedLongs.get(j) < sortedLongs.get(minLongIndex)) {
                    minLongIndex = j;
                }
            }

            // Swaps the smallest element from the unsorted part with the current element
            long tmp = sortedLongs.get(i);
            sortedLongs.set(i, sortedLongs.get(minLongIndex));
            sortedLongs.set(minLongIndex, tmp);
        }

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }
}
