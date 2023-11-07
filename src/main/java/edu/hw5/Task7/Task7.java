package edu.hw5.Task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    private Task7() {}

    public static boolean firstRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-1]{2}0[0-1]*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean secondRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(0[0-1]*0)$|^(1[0-1]*1)$|^0$|^1$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean thirdRegular(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-1]{1,3}$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
