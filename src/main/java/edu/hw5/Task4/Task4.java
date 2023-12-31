package edu.hw5.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    private static final String PATTERN = "[~!@#$%^&*|]";

    private Task4() {}

    public static boolean passwordIsCorrect(String password) {
        if (password == null) {
            return false;
        }

        Pattern special = Pattern.compile(PATTERN);
        Matcher matcher = special.matcher(password);

        return matcher.find();
    }
}
