package edu.hw1;

public final class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int receivedNumber, int shift) {
        if (shift < 0 || receivedNumber < 0) {
            return receivedNumber;
        }

        char[] binNumberArray = Integer.toBinaryString(receivedNumber).toCharArray();

        int shiftWithoutCycle = shift % binNumberArray.length;

        char[] resultNumber = new char[binNumberArray.length];
        for (int digitIndex = 0; digitIndex < binNumberArray.length; digitIndex++) {
            int newPosIndex = digitIndex - shiftWithoutCycle;

            if (newPosIndex < 0) {
                newPosIndex += binNumberArray.length;
            }

            resultNumber[newPosIndex] = binNumberArray[digitIndex];
        }

        return Integer.parseInt(new String(resultNumber), 2);
    }

    public static int rotateRight(int receivedNumber, int shift) {
        if (shift < 0 || receivedNumber < 0) {
            return receivedNumber;
        }

        char[] binNumberArray = Integer.toBinaryString(receivedNumber).toCharArray();

        int shiftWithoutCycle = shift % binNumberArray.length;

        char[] resultNumber = new char[binNumberArray.length];
        for (int digitIndex = 0; digitIndex < binNumberArray.length; digitIndex++) {
            int newPosIndex = digitIndex + shiftWithoutCycle;

            if (newPosIndex >= binNumberArray.length) {
                newPosIndex -= binNumberArray.length;
            }

            resultNumber[newPosIndex] = binNumberArray[digitIndex];
        }

        return Integer.parseInt(new String(resultNumber), 2);
    }
}
