package logic.algorithms.sort;

import java.util.ArrayList;

public class BubbleSort extends Sort {
    public BubbleSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public BubbleSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);

        boolean isSorted = false;
        for (int i = 0; !isSorted && i < sortedLongs.size(); i++) {
            isSorted = true;

            // Swaps the elements in the unsorted part of the ArrayList until the largest element "rises" to the top
            for (int j = 1; j < sortedLongs.size() - i; j++) {
                if (sortedLongs.get(j - 1) > sortedLongs.get(j)) {
                    isSorted = false;

                    long tmp = sortedLongs.get(j - 1);
                    sortedLongs.set(j - 1, sortedLongs.get(j));
                    sortedLongs.set(j, tmp);
                }
            }
        }

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }
}
