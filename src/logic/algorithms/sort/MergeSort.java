package logic.algorithms.sort;

import java.util.ArrayList;

public class MergeSort extends Sort {
    public MergeSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public MergeSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);

        divideAndSort(0, sortedLongs.size() - 1);

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }

    private void divideAndSort(int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            // Finds middle point
            int middleIndex = (startIndex + endIndex) / 2;

            // Finds two halves
            divideAndSort(startIndex, middleIndex);
            divideAndSort(middleIndex + 1, endIndex);

            // Merges the sorted halves
            merge(startIndex, middleIndex, endIndex);
        }
    }

    private void merge(int startIndex, int middleIndex, int endIndex) {
        // Copies the already sorted elements that are left to the middle element (inclusive)
        int leftLongsSize = middleIndex - startIndex + 1;
        long[] leftLongs = new long[leftLongsSize];
        for (int i = 0; i < leftLongsSize; i++) {
            leftLongs[i] = sortedLongs.get(i + startIndex);
        }

        // Copies the already sorted elements that are right to the middle element
        int rightLongsSize = endIndex - middleIndex;
        long[] rightLongs = new long[rightLongsSize];
        for (int i = 0; i < rightLongsSize; i++) {
            rightLongs[i] = sortedLongs.get(i + middleIndex + 1);
        }

        // Copies the elements from the two halves to the main ArrayList in the right order
        int i = 0;
        int j = 0;
        int k = startIndex;
        while (i < leftLongsSize && j < rightLongsSize) {
            if (leftLongs[i] <= rightLongs[j]) {
                sortedLongs.set(k, leftLongs[i]);
                i++;
            } else {
                sortedLongs.set(k, rightLongs[j]);
                j++;
            }

            k++;
        }

        // Copies the remaining elements from the left side to the main ArrayList
        while (i < leftLongsSize) {
            sortedLongs.set(k, leftLongs[i]);
            i++;
            k++;
        }

        // Copies the remaining elements from the right side to the main ArrayList
        while (j < rightLongsSize) {
            sortedLongs.set(k, rightLongs[j]);
            j++;
            k++;
        }
    }
}
