package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    private Task5() {}

    public static boolean carNumberIsCorrect(String number) {
        if (number == null) {
            return false;
        }

        Pattern numberPattern = Pattern.compile("^([А-Я])([0-9]{3})([А-Я]{2})([0-9]{3})$");
        Matcher numberMatcher = numberPattern.matcher(number);

        return numberMatcher.find();
    }
}
