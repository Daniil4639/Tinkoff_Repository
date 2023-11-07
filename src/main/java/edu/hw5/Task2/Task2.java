package edu.hw5.Task2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task2 {

    private static final int FRIDAY_SPECIAL_NUMBER = 13;
    private static final int CALENDAR_YEAR_FEATURE = 1900;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Task2() {}

    public static ArrayList<String> friday13ThSearch(int year) {
        Calendar calendar = Calendar.getInstance();
        int month = 0;
        ArrayList<String> resultList = new ArrayList<>();

        while (month < FRIDAY_SPECIAL_NUMBER) {
            calendar.set(year, month, FRIDAY_SPECIAL_NUMBER);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                resultList.add(DATE_FORMAT.format(new Date(year - CALENDAR_YEAR_FEATURE, month,
                    FRIDAY_SPECIAL_NUMBER)));
            }

            month++;
        }

        return resultList;
    }

    public static LocalDate nextFriday13Th(LocalDate currentFriday) {
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(currentDate -> {

            ArrayList<String> fridays13ThOfThisYear = friday13ThSearch(currentDate.getYear());

            for (int index = 0; index < fridays13ThOfThisYear.size(); index++) {

                if (LocalDate.parse(fridays13ThOfThisYear.get(index)).equals(currentDate)
                    && index != fridays13ThOfThisYear.size() - 1) {

                    return LocalDate.parse(fridays13ThOfThisYear.get(index + 1));
                }
            }

            for (int addIndex = 1; ; addIndex++) {
                fridays13ThOfThisYear = friday13ThSearch(currentDate.getYear() + addIndex);

                if (!fridays13ThOfThisYear.isEmpty()) {
                    return LocalDate.parse(fridays13ThOfThisYear.getFirst());
                }
            }
        });

        return currentFriday.with(temporalAdjuster);
    }
}
