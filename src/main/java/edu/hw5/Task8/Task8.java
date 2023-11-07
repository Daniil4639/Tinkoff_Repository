package edu.hw5.Task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    private Task8() {}

    public static boolean regularOddLength(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-1]([0-1]{2})*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularZeroWithOddLenOrOneWithEvenLen(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0([0-1]{2})*$|^1[0-1]([0-1]{2})*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularZeroMultiplyThree(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^1*((1*01*){3})*1*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularNo11No111(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(?!11$)(?!111$)[0-1]*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularEveryOddSymbolIsOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^1([0-1](1)?)*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularWithTwoOrMoreZeroAndWithOneOrLessOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0{2,}$|^0{2,}10*$|^0*10{2,}$|^0+10+$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularWithoutOrderedOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0*((10+)*1?)?$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
