package logic.algorithms.sort;

import java.util.ArrayList;

public class QuickSort extends Sort {
    public QuickSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public QuickSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);

        divideAndSort(0, unsortedLongs.size() - 1);

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }

    private void divideAndSort(int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int partitionIndex = partition(startIndex, endIndex);

            // Recursively sorts elements before and after partition
            divideAndSort(startIndex, partitionIndex - 1);
            divideAndSort(partitionIndex + 1, endIndex);
        }
    }

    // Takes the last element as pivot
    // Copies the smaller elements than the pivot to the left of the pivot
    // Copies the larger elements than the pivot to the right of the pivot
    private int partition(int startIndex, int endIndex) {
        long pivot = sortedLongs.get(endIndex);

        int i = startIndex - 1;
        for (int j = startIndex; j < endIndex; j++) {
            if (sortedLongs.get(j) < pivot) {
                i++;

                long tmp = sortedLongs.get(i);
                sortedLongs.set(i, sortedLongs.get(j));
                sortedLongs.set(j, tmp);
            }
        }

        long tmp = sortedLongs.get(i + 1);
        sortedLongs.set(i + 1, sortedLongs.get(endIndex));
        sortedLongs.set(endIndex, tmp);

        return i + 1;
    }
}
