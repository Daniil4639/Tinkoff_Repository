package edu.hw5.Task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    private static final String FIRST_REGULAR_PATTERN = "^[0-1]{2}0[0-1]*$";
    private static final String SECOND_REGULAR_PATTERN = "^(0[0-1]*0)$|^(1[0-1]*1)$|^0$|^1$";
    private static final String THIRD_REGULAR_PATTERN = "^[0-1]{1,3}$";

    private Task7() {}

    public static boolean firstRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(FIRST_REGULAR_PATTERN);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean secondRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(SECOND_REGULAR_PATTERN);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean thirdRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(THIRD_REGULAR_PATTERN);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
