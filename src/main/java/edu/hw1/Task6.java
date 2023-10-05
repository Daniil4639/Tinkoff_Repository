package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static int cyclesCount = 0;

    public static int countK(int receivedNumber) {
        if (receivedNumber < 0) {
            return -1;
        }

        cyclesCount++;

        if (cyclesCount == 7) {
            cyclesCount = 0;
            return -7;
        }

        char[] digitsArray = Integer.toString((receivedNumber)).toCharArray();
        Arrays.sort(digitsArray);

        int lowerSortNumber = Integer.parseInt(new String(digitsArray));
        int upperSortNumber = Integer.parseInt((new StringBuilder(new String(digitsArray))).reverse().toString());

        upperSortNumber -= lowerSortNumber;

        if (upperSortNumber == 6174) {
            cyclesCount = 0;
            return 1;
        } else {
            return 1 + countK(upperSortNumber);
        }
    }
}
