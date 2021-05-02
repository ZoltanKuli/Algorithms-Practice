package logic.algorithms.generator;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumbersGenerator {
    // Uses nextFloat() to generate longs in a range
    // Exclusive
    public static ArrayList<Long> generateLongs(int size, long lowestLong, long largestLong) {
        ArrayList<Long> longs = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            longs.add(lowestLong + (long) ((largestLong - lowestLong) * random.nextFloat()));
        }

        return longs;
    }

    // Uses nextLong() to generate longs
    // Cannot generate every possible long value
    public static ArrayList<Long> generateLongs(int size) {
        ArrayList<Long> longs = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            longs.add(random.nextLong());
        }

        return longs;
    }
}
