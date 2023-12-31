package edu.hw1;

public final class Task5 {

    private Task5() {
    }

    private static final int NUMBER_SYSTEM_SIZE = 10;

    public static boolean isPalindromeDescendant(long receivedNumber) {
        if (receivedNumber < 0) {
            return false;
        }

        boolean conditionSuccess = true;

        char[] digitsArray = Long.toString(receivedNumber).toCharArray();

        for (int digitIndex = 0; digitIndex < digitsArray.length / 2; digitIndex++) {
            if (digitsArray[digitIndex] != digitsArray[digitsArray.length - digitIndex - 1]) {
                conditionSuccess = false;
                break;
            }
        }

        if (conditionSuccess) {
            return true;
        } else {
            long resultNumber = 0;
            for (int digitIndex = 1; digitIndex < digitsArray.length; digitIndex += 2) {
                resultNumber = resultNumber * NUMBER_SYSTEM_SIZE
                    + Character.getNumericValue(digitsArray[digitIndex])
                    + Character.getNumericValue(digitsArray[digitIndex - 1]);
            }
            if (digitsArray.length % 2 != 0) {
                resultNumber = resultNumber * NUMBER_SYSTEM_SIZE
                    + Character.getNumericValue(digitsArray[digitsArray.length - 1]);
            }

            if (Task2.countDigits(resultNumber) < 2) {
                return false;
            }

            return isPalindromeDescendant(resultNumber);
        }
    }
}
