package edu.hw1;

public final class Task2 {

    private Task2() {
    }

    private static final int NUMBER_SYSTEM_SIZE = 10;

    public static int countDigits(long receivedNumber) {
        long unsignedReceivedNumber = Math.abs(receivedNumber);

        int digitsCount = 0;
        while (unsignedReceivedNumber > 0) {
            digitsCount++;
            unsignedReceivedNumber /= NUMBER_SYSTEM_SIZE;
        }

        return digitsCount;
    }
}
