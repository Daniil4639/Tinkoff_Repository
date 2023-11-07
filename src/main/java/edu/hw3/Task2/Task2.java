package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {}

    public static List<String> clusterize(String receivedString) {
        StringBuilder newArrayToken = new StringBuilder();
        List<String> arrayOfTokens = new ArrayList<>();
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
