package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private static final String TIME_FORMAT = "yyyy-MM-dd, HH:mm";

    private Task1() {}

    public static Duration averageTimeOfPlay(Collection<String> intervals) {
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

            return Duration.ofSeconds(secondsCount / intervalsCount);
        }
    }
}
