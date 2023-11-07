package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {}

    public static boolean isSubstring(String string, String sub) {
        if (string == null || sub == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(".*" + sub + ".*");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
