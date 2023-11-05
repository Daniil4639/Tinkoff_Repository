package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task2 {

    private static final int MAGIC_13_NUMBER = 13;
    private static final int CALENDAR_YEAR_FEATURE = 1900;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Task2() {}

    public static ArrayList<String> friday13ThSearch(int year) {
        Calendar calendar = Calendar.getInstance();
        int month = 0;
        ArrayList<String> resultList = new ArrayList<>();

        while (month < MAGIC_13_NUMBER - 1) {
            calendar.set(year, month, MAGIC_13_NUMBER);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                resultList.add(DATE_FORMAT.format(new Date(year - CALENDAR_YEAR_FEATURE, month, MAGIC_13_NUMBER)));
            }

            month++;
        }

        return resultList;
    }

    public static String nextFriday13Th(String date) {
        try {
            Date currentDate = DATE_FORMAT.parse(date);

            ArrayList<String> fridays13ThOfThisYear = friday13ThSearch(currentDate.getYear() + CALENDAR_YEAR_FEATURE);
            for (int index = 0; index < fridays13ThOfThisYear.size(); index++) {
                if (fridays13ThOfThisYear.get(index).equals(date) && index != fridays13ThOfThisYear.size() - 1) {
                    return fridays13ThOfThisYear.get(index + 1);
                }
            }

            for (int addIndex = 1; ; addIndex++) {
                fridays13ThOfThisYear = friday13ThSearch(currentDate.getYear() + addIndex + CALENDAR_YEAR_FEATURE);

                if (!fridays13ThOfThisYear.isEmpty()) {
                    return fridays13ThOfThisYear.getFirst();
                }
            }

        } catch (ParseException parseException) {
            return null;
        }
    }
}
