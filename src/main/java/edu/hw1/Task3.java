package edu.hw1;

public final class Task3 {

    private Task3() {
    }

    private static long minLongArray(long[] receivedArray) {
        long minNumber = Long.MAX_VALUE;

        for (long element : receivedArray) {
            if (element < minNumber) {
                minNumber = element;
            }
        }

        return minNumber;
    }

    private static long maxLongArray(long[] receivedArray) {
        long maxNumber = Long.MIN_VALUE;

        for (long element : receivedArray) {
            if (element > maxNumber) {
                maxNumber = element;
            }
        }

        return maxNumber;
    }

    public static boolean isNestable(long[] nestableArray, long[] containArray) {
        if (nestableArray == null || containArray == null || nestableArray.length == 0 || containArray.length == 0) {
            return false;
        }

        if (minLongArray(nestableArray) > minLongArray(containArray)
            && maxLongArray(nestableArray) < maxLongArray(containArray)) {
            return true;
        }
        return false;
    }
}
