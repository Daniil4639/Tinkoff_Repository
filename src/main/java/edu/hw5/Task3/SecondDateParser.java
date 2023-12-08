package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SecondDateParser extends DateParser {

    private static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("d/M/yy");

    public SecondDateParser() {
        nextParser = new ThirdDateParser();
    }

    @Override
    public LocalDate stringTransform(String string) {
        if (string.matches("\\d/\\d/\\d{4}")) {

            return LocalDate.parse(string, DATE_FORMATTER_1);
        } else if (string.matches("\\d/\\d/\\d{2}")) {

            return LocalDate.parse(string, DATE_FORMATTER_2);
        } else if (nextParser != null) {
            return nextParser.stringTransform(string);
        } else {
            return null;
        }
    }
}
