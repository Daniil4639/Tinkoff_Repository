package edu.hw3;

import java.util.ArrayList;

public class Task2 {

    private Task2() {}

    public static ArrayList<String> clusterize(String receivedString) {
        StringBuilder newArrayToken = new StringBuilder();
        ArrayList<String> arrayOfTokens = new ArrayList<>();
        int bracketCount = 0;

        for (int iterator = 0; iterator < receivedString.length(); iterator++) {
            if (receivedString.charAt(iterator) == '(') {
                bracketCount++;
            } else {
                bracketCount--;
            }

            newArrayToken.append(receivedString.charAt(iterator));

            if (bracketCount == 0) {
                arrayOfTokens.add(newArrayToken.toString());
                newArrayToken.setLength(0);
            }
        }

        return arrayOfTokens;
    }
}
