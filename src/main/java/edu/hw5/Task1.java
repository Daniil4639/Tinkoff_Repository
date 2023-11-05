package edu.hw5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private static final String TIME_FORMAT = "yyyy-MM-dd, HH:mm";
    private static final int SEC_IN_DAY = 86400;
    private static final int SEC_IN_HOUR = 3600;
    private static final int SEC_IN_MIN = 60;

    private Task1() {}

    public static String averageTimeOfPlay(Collection<String> intervals) {
        Pattern timePattern = Pattern.compile("^(.*) - (.*)$");
        int intervalsCount = 0;
        long secondsCount = 0;

        for (String element: intervals) {
            Matcher timeMatcher = timePattern.matcher(element);
            if (timeMatcher.groupCount() == 2 && timeMatcher.find()) {

                LocalDateTime date1 = DateTimeFormatter.ofPattern(TIME_FORMAT)
                    .parse(timeMatcher.group(1), LocalDateTime::from);
                LocalDateTime date2 = DateTimeFormatter.ofPattern(TIME_FORMAT)
                    .parse(timeMatcher.group(2), LocalDateTime::from);

                if (date2.isAfter(date1)) {
                    intervalsCount++;
                    secondsCount += date1.until(date2, ChronoUnit.SECONDS);
                }
            }
        }

        if (intervalsCount == 0) {
            return null;
        } else {

            long averageSecondsCount = secondsCount / intervalsCount;

            StringBuilder result = new StringBuilder();
            if (averageSecondsCount / SEC_IN_DAY > 0) {
                result.append(averageSecondsCount / SEC_IN_DAY).append("д ");
                averageSecondsCount %= SEC_IN_DAY;
            }
            if (averageSecondsCount / SEC_IN_HOUR > 0) {
                result.append(averageSecondsCount / SEC_IN_HOUR).append("ч ");
                averageSecondsCount %= SEC_IN_HOUR;
            }
            if (averageSecondsCount / SEC_IN_MIN > 0) {
                result.append(averageSecondsCount / SEC_IN_MIN).append("м ");
                averageSecondsCount %= SEC_IN_MIN;
            }
            if (averageSecondsCount > 0) {
                result.append(averageSecondsCount).append("с ");
            }

            return result.substring(0, result.length() - 1);
        }
    }
}
