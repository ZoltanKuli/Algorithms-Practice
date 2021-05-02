package logic.algorithms.sort;

import java.util.ArrayList;

public class HeapSort extends Sort {
    public HeapSort(ArrayList<Long> unsortedLongs) {
        super(unsortedLongs);
    }

    public HeapSort() {
        super();
    }

    @Override
    public void run() {
        sortTimeMillis = System.currentTimeMillis();

        sortedLongs = new ArrayList<>(unsortedLongs);
        int heapSize = sortedLongs.size();

        // Builds a heap
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            rearrangeSortedLongsIntoMaxHeap(heapSize, i);
        }

        // One by one extracts the largest element from the heap
        for (int i = heapSize - 1; i >= 0; i--) {
            long tmp = sortedLongs.get(0);
            sortedLongs.set(0, sortedLongs.get(i));
            sortedLongs.set(i, tmp);

            rearrangeSortedLongsIntoMaxHeap(i, 0);
        }

        sortTimeMillis = System.currentTimeMillis() - sortTimeMillis;
    }

    void rearrangeSortedLongsIntoMaxHeap(int heapSize, int rootIndex)
    {
        int largestElementIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        if (leftChildIndex < heapSize && sortedLongs.get(leftChildIndex) > sortedLongs.get(largestElementIndex)) {
            largestElementIndex = leftChildIndex;
        }

        if (rightChildIndex < heapSize && sortedLongs.get(rightChildIndex) > sortedLongs.get(largestElementIndex)) {
            largestElementIndex = rightChildIndex;
        }

        if (largestElementIndex != rootIndex)
        {
            long tmp = sortedLongs.get(rootIndex);
            sortedLongs.set(rootIndex, sortedLongs.get(largestElementIndex));
            sortedLongs.set(largestElementIndex, tmp);

            rearrangeSortedLongsIntoMaxHeap(heapSize, largestElementIndex);
        }
    }
}
