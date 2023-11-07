package edu.hw3.Task7;

import java.util.Comparator;

public class Task7 {

    private Task7() {}

    public static final class ComparatorWithNullFirst<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T obj1, T obj2) {
            if (obj1 == null && obj2 == null) {
                return 0;
            } else if (obj1 == null) {
                return -1;
            } else if (obj2 == null) {
                return 1;
            } else {
                return obj1.compareTo(obj2);
            }
        }
    }
}
