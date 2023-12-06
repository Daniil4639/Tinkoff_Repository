package edu.hw5.Task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    private static final String ODD_STRING_REGULAR = "^[0-1]([0-1]{2})*$";
    private static final String ZERO_WITH_ODD_LEN_OR_ONE_WITH_EVEN_LEN_STRING_REGULAR
        = "^0([0-1]{2})*$|^1[0-1]([0-1]{2})*$";
    private static final String ZERO_MULTIPLY_THREE_STRING_REGULAR = "^1*((1*01*){3})*1*$";
    private static final String NO_11_NO_111_STRING_REGULAR = "^(?!11$)(?!111$)[0-1]*$";
    private static final String EVERY_ODD_SYMBOL_IS_ONE_STRING_REGULAR = "^1([0-1](1)?)*$";
    private static final String TWO_OR_MORE_ZEROES_AND_ONE_OR_LESS_ONE_STRING_REGULAR
        = "^0{2,}$|^0{2,}10*$|^0*10{2,}$|^0+10+$";
    private static final String WITHOUT_ORDERED_ONE_STRING_REGULAR = "^0*((10+)*1?)?$";

    private Task8() {}

    public static boolean regularOddLength(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(ODD_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularZeroWithOddLenOrOneWithEvenLen(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(ZERO_WITH_ODD_LEN_OR_ONE_WITH_EVEN_LEN_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularZeroMultiplyThree(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(ZERO_MULTIPLY_THREE_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularNo11No111(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(NO_11_NO_111_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularEveryOddSymbolIsOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EVERY_ODD_SYMBOL_IS_ONE_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularWithTwoOrMoreZeroAndWithOneOrLessOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(TWO_OR_MORE_ZEROES_AND_ONE_OR_LESS_ONE_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public static boolean regularWithoutOrderedOne(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(WITHOUT_ORDERED_ONE_STRING_REGULAR);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
