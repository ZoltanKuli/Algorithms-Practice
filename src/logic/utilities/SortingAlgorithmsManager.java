package logic.utilities;

import logic.algorithms.generator.RandomNumbersGenerator;
import logic.algorithms.sort.*;
import logic.output.Writer;

import java.util.ArrayList;

public class SortingAlgorithmsManager {
    public void sort(String outputPath) {
        // Prompts the user to specify sorting algorithms to use
        displaySortingAlgorithmsSelector();
        System.out.print("\n\n   Which sorting algorithms would you like to execute? (divide by \",\") ");

        boolean isRunning = true;
        while (isRunning) {
            String[] inputs = InputScanner.readString().toLowerCase().split(", ");
            boolean isInputGood = checkInput(inputs);

            if (isInputGood) {
                // Prompts the user to specify the parameters of random longs
                int size = InputScanner.readInt(
                        "\n   (Beware of too large sizes because the program may never terminate)" +
                                "\n   Specify the size of random longs: ",
                        "\n   Wrong input");
                long lowestLong = InputScanner.readLong("\n   Specify the lowest long: ",
                        "\n   Wrong input");
                long largestLong = InputScanner.readLong("\n   Specify the largest long: ",
                        "\n   Wrong input");

                ConsoleCleaner.clear();

                // Generates random longs for all of the sorting algorithms to use
                System.out.println(String.format("\n   Generating %d random longs between %d - %d", size, lowestLong, largestLong));
                ArrayList<Long> longs = RandomNumbersGenerator.generateLongs(size, lowestLong, largestLong);

                // Initializes sort algorithms for methods to use if called for
                BubbleSort bubbleSort = new BubbleSort();
                SelectionSort selectionSort = new SelectionSort();
                InsertionSort insertionSort = new InsertionSort();
                MergeSort mergeSort = new MergeSort();
                QuickSort quickSort = new QuickSort();
                HeapSort heapSort = new HeapSort();

                System.out.println();

                startSortingAlgorithms(bubbleSort, selectionSort, insertionSort,
                        mergeSort, quickSort, heapSort, inputs, longs);

                System.out.println();

                writeToFiles(bubbleSort, selectionSort, insertionSort,
                        mergeSort, quickSort, heapSort, outputPath);

                System.out.println("\n   Sorting is complete\n");

                isRunning = false;
            } else {
                // Prompts the user to specify sorting algorithms to use
                displaySortingAlgorithmsSelector();
                System.out.print("\n\n   Incorrect input, please try again!\n   Which sorting algorithms would you like to execute? (divide by \",\") ");
            }
        }
    }

    private void displaySortingAlgorithmsSelector() {
        ConsoleCleaner.clear();

        // Uses the SortingAlgorithms enum
        System.out.print("\n   {0} Sorting Algorithms Selector\n");
        for (SortingAlgorithms sortingAlgorithm: SortingAlgorithms.values()) {
            System.out.print(String.format("\n   [%d] %sSort", sortingAlgorithm.getValue() + 1, sortingAlgorithm.toString()));
        }
    }

    // Checks if input format is correct and the input exists among possible options
    private boolean checkInput(String[] inputs) {
        boolean isInputGood = true;

        SortingAlgorithms[] sortingAlgorithms = SortingAlgorithms.values();
        for (String input: inputs) {
            boolean isInputFound = false;

            String[] words = input.split(" ");
            int index = InputScanner.parseInd(input);
            for (SortingAlgorithms sortingAlgorithm: sortingAlgorithms) {

                if (words[0].equals(sortingAlgorithm.toString().toLowerCase()) ||
                        index == sortingAlgorithm.getValue()) {
                    isInputFound = true;
                }
            }

            if (!isInputFound) {
                isInputGood = false;
            }
        }

        return isInputGood;
    }

    // Starts chosen sorting algorithms with possibility to specify unique messages for each algorithm
    private void startSortingAlgorithms(BubbleSort bubbleSort, SelectionSort selectionSort, InsertionSort insertionSort,
                                        MergeSort mergeSort, QuickSort quickSort, HeapSort heapSort,
                                        String[] inputs, ArrayList<Long> longs) {
        for (String input : inputs) {
            int index = InputScanner.parseInd(input);
            String[] inputWords = input.split(" ");
            if (inputWords.length == 1 || inputWords[1].equals("sort")) {
                if (inputWords[0].equals(SortingAlgorithms.BUBBLE.toString().toLowerCase()) ||
                        index == SortingAlgorithms.BUBBLE.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.BUBBLE.toString()));

                    bubbleSort.setValues(longs);
                    bubbleSort.start();
                } else if (inputWords[0].equals(SortingAlgorithms.SELECTION.toString().toLowerCase()) ||
                        index == SortingAlgorithms.SELECTION.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.SELECTION.toString()));

                    selectionSort.setValues(longs);
                    selectionSort.start();
                } else if (inputWords[0].equals(SortingAlgorithms.INSERTION.toString().toLowerCase()) ||
                        index == SortingAlgorithms.INSERTION.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.INSERTION.toString()));

                    insertionSort.setValues(longs);
                    insertionSort.start();
                } else if (inputWords[0].equals(SortingAlgorithms.MERGE.toString().toLowerCase()) ||
                        index == SortingAlgorithms.MERGE.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.MERGE.toString()));

                    mergeSort.setValues(longs);
                    mergeSort.start();
                } else if (inputWords[0].equals(SortingAlgorithms.QUICK.toString().toLowerCase()) ||
                        index == SortingAlgorithms.QUICK.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.QUICK.toString()));

                    quickSort.setValues(longs);
                    quickSort.start();
                } else if (inputWords[0].equals(SortingAlgorithms.HEAP.toString().toLowerCase()) ||
                        index == SortingAlgorithms.HEAP.getValue()) {
                    System.out.println(String.format("   Sorting random longs with %sSort",
                            SortingAlgorithms.HEAP.toString()));

                    heapSort.setValues(longs);
                    heapSort.start();
                }
            }
        }
    }

    // Writes sorting results to new files
    private void writeToFiles(BubbleSort bubbleSort, SelectionSort selectionSort, InsertionSort insertionSort,
                              MergeSort mergeSort, QuickSort quickSort, HeapSort heapSort, String path) {
        // Uses different writers for all of the algorithms for the possibility of multithreading
        Writer bubbleSortWriter = null;
        Writer selectionSortWriter = null;
        Writer insertionSortWriter = null;
        Writer mergeSortWriter = null;
        Writer quickSortWriter = null;
        Writer heapSortWriter = null;

        if (bubbleSort.isSet()) {
            bubbleSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.BUBBLE.toString()));

            bubbleSortWriter = new logic.output.Writer(path, bubbleSort.getClass().getSimpleName(),
                    bubbleSort.getSortTimeMillis(), bubbleSort.getUnsortedLongs(), bubbleSort.getSortedLongs());
            bubbleSortWriter.start();
        }

        if (selectionSort.isSet()) {
            selectionSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.SELECTION.toString()));

            selectionSortWriter = new logic.output.Writer(path, selectionSort.getClass().getSimpleName(),
                    selectionSort.getSortTimeMillis(), selectionSort.getUnsortedLongs(), selectionSort.getSortedLongs());
            selectionSortWriter.start();
        }

        if (insertionSort.isSet()) {
            insertionSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.INSERTION.toString()));

            insertionSortWriter = new logic.output.Writer(path, insertionSort.getClass().getSimpleName(),
                    insertionSort.getSortTimeMillis(), insertionSort.getUnsortedLongs(), insertionSort.getSortedLongs());
            insertionSortWriter.start();
        }

        if (mergeSort.isSet()) {
            mergeSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.MERGE.toString()));

            mergeSortWriter = new logic.output.Writer(path, mergeSort.getClass().getSimpleName(),
                    mergeSort.getSortTimeMillis(), mergeSort.getUnsortedLongs(), mergeSort.getSortedLongs());
            mergeSortWriter.start();
        }

        if (quickSort.isSet()) {
            quickSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.QUICK.toString()));

            quickSortWriter = new logic.output.Writer(path, quickSort.getClass().getSimpleName(),
                    quickSort.getSortTimeMillis(), quickSort.getUnsortedLongs(), quickSort.getSortedLongs());
            quickSortWriter.start();
        }

        if (heapSort.isSet()) {
            heapSort.join();

            System.out.println(String.format("   Saving %sSort results",
                    SortingAlgorithms.HEAP.toString()));

            heapSortWriter = new logic.output.Writer(path, heapSort.getClass().getSimpleName(),
                    heapSort.getSortTimeMillis(), heapSort.getUnsortedLongs(), heapSort.getSortedLongs());
            heapSortWriter.start();
        }

        // Wait for writer methods to finish
        if (bubbleSortWriter != null) {
            bubbleSortWriter.join();
        }

        if (selectionSortWriter != null) {
            selectionSortWriter.join();
        }

        if (insertionSortWriter != null) {
            insertionSortWriter.join();
        }

        if (mergeSortWriter != null) {
            mergeSortWriter.join();
        }

        if (quickSortWriter != null) {
            quickSortWriter.join();
        }

        if (heapSortWriter != null) {
            heapSortWriter.join();
        }
    }
}
