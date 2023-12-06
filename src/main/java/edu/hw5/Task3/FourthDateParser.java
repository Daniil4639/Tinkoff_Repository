package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FourthDateParser extends DateParser {

    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d+) .* ago$");

    public FourthDateParser() {
        nextParser = null;
    }

    @Override
    public LocalDate stringTransform(String string) {
        Matcher matcher = DATE_PATTERN.matcher(string);

        if (matcher.find()) {
            return LocalDate.now().minusDays(Long.parseLong(matcher.group(1)));
        } else if (nextParser != null) {
            return nextParser.stringTransform(string);
        } else {
            return null;
        }
    }
}
