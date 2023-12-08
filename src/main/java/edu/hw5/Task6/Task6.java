package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {}

    public static boolean isSubsequence(String string, String sub) {
        if (string == null || sub == null) {
            return false;
        }

        StringBuilder subSequence = new StringBuilder();
        for (char symbol: sub.toCharArray()) {
            subSequence.append(".*").append(symbol);
        }
        subSequence.append(".*");

        Pattern pattern = Pattern.compile(subSequence.toString());
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
