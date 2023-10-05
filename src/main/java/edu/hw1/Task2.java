package edu.hw1;

public class Task2 {

    public static int countDigits(long receivedNumber) {
        receivedNumber = Math.abs(receivedNumber);

        int digitsCount = 0;
        while (receivedNumber > 0) {
            digitsCount++;
            receivedNumber /= 10;
        }

        return digitsCount;
    }
}
