package edu.hw3.Task4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Task4 {

    private Task4() {}

    private static final Map<Integer, String> ROMAN_DIGITS = Map.ofEntries(Map.entry(1000, "M"),
        Map.entry(900, "CM"), Map.entry(500, "D"), Map.entry(400, "CD"), Map.entry(100, "C"),
        Map.entry(90, "XC"), Map.entry(50, "L"), Map.entry(40, "XL"), Map.entry(10, "X"),
        Map.entry(9, "IX"), Map.entry(5, "V"), Map.entry(4, "IV"), Map.entry(1, "I"));

    public static String convertToRoman(int receivedNumber) {
        int number = receivedNumber;
        Integer[] valueArray = new Integer[ROMAN_DIGITS.size()];
        valueArray = ROMAN_DIGITS.keySet().toArray(valueArray);
        Arrays.sort(valueArray, Collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        int mapIterator = 0;

        while (number > 0) {
            while (valueArray[mapIterator] > number) {
                mapIterator++;
            }

            result.append(ROMAN_DIGITS.get(valueArray[mapIterator]));
            number -= valueArray[mapIterator];
        }

        return result.toString();
    }
}
