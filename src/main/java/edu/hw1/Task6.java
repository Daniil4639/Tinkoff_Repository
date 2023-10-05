package edu.hw1;

import java.util.Arrays;

public final class Task6 {

    private Task6() {
    }

    private static final int CYCLE_LIMIT = 7;

    private static final int K_NUMBER = 6174;

    private static int cyclesCount = 0;

    public static int countK(int receivedNumber) {
        if (receivedNumber < 0) {
            return -1;
        }

        cyclesCount++;

        if (cyclesCount == CYCLE_LIMIT) {
            cyclesCount = 0;
            return -CYCLE_LIMIT;
        }

        char[] digitsArray = Integer.toString((receivedNumber)).toCharArray();
        Arrays.sort(digitsArray);

        int lowerSortNumber = Integer.parseInt(new String(digitsArray));
        int upperSortNumber = Integer.parseInt((new StringBuilder(new String(digitsArray))).reverse().toString());

        upperSortNumber -= lowerSortNumber;

        if (upperSortNumber == K_NUMBER) {
            cyclesCount = 0;
            return 1;
        } else {
            return 1 + countK(upperSortNumber);
        }
    }
}
