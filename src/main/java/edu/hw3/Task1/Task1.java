package edu.hw3.Task1;

public class Task1 {

    private static final int CODE_OF_BIG_A = 97;
    private static final int CODE_OF_BIG_Z = 122;
    private static final int BIG_LETTERS_BORDER = 219;
    private static final int CODE_OF_LITTLE_A = 65;
    private static final int CODE_OF_LITTLE_Z = 90;
    private static final int LITTLE_LETTERS_BORDER = 155;

    private Task1() {}

    public static String atBash(String receivedString) {
        char[] symbolsArray = receivedString.toCharArray();

        for (int iterator = 0; iterator < symbolsArray.length; iterator++) {
            if (symbolsArray[iterator] >= CODE_OF_BIG_A && symbolsArray[iterator] <= CODE_OF_BIG_Z) {
                symbolsArray[iterator] = (char) (BIG_LETTERS_BORDER - (int) (symbolsArray[iterator]));
            } else if (symbolsArray[iterator] >= CODE_OF_LITTLE_A && symbolsArray[iterator] <= CODE_OF_LITTLE_Z) {
                symbolsArray[iterator] = (char) (LITTLE_LETTERS_BORDER - (int) (symbolsArray[iterator]));
            }
        }

        return new String(symbolsArray);
    }
}
