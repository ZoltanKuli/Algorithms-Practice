package logic.algorithms.sort;

public enum SortingAlgorithms {
    BUBBLE(0),
    SELECTION(1),
    INSERTION(2),
    MERGE(3),
    QUICK(4),
    HEAP(5);

    private final int value;

    SortingAlgorithms(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        String str = this.name();

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
