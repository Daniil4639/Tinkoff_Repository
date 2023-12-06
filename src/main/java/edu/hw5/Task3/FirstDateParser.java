package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FirstDateParser extends DateParser {

    private static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public FirstDateParser() {
        nextParser = new SecondDateParser();
    }

    @Override
    public LocalDate stringTransform(String string) {
        if (string.matches("\\d{4}-\\d{2}-\\d{2}")) {

            return LocalDate.parse(string, DATE_FORMATTER_1);
        } else if (string.matches("\\d{4}-\\d{2}-\\d")) {

            return LocalDate.parse(string, DATE_FORMATTER_2);
        } else if (nextParser != null) {
            return nextParser.stringTransform(string);
        }
        return null;
    }
}
