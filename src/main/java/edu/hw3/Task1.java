package edu.hw3;

public class Task1 {

    private Task1() {}

    private static final int NUMBER97 = 97;
    private static final int NUMBER122 = 122;
    private static final int NUMBER25 = 25;
    private static final int NUMBER194 = 194;
    private static final int NUMBER65 = 65;
    private static final int NUMBER90 = 90;
    private static final int NUMBER130 = 130;

    public static String atBash(String receivedString) {
        char[] symbolsArray = receivedString.toCharArray();

        for (int iterator = 0; iterator < symbolsArray.length; iterator++) {
            if (symbolsArray[iterator] >= NUMBER97 && symbolsArray[iterator] <= NUMBER122) {
                symbolsArray[iterator] = (char) (NUMBER25 - (int) (symbolsArray[iterator]) + NUMBER194);
            } else if (symbolsArray[iterator] >= NUMBER65 && symbolsArray[iterator] <= NUMBER90) {
                symbolsArray[iterator] = (char) (NUMBER25 - (int) (symbolsArray[iterator]) + NUMBER130);
            }
        }

        return new String(symbolsArray);
    }
}
