package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class Task3 {

    private Task3() {}

    public static Optional<LocalDate> parseDate(String string) {
        DateParser dateParser = getParsersChain();
        LocalDate date = dateParser.stringTransform(string);

        if (date == null) {
            return Optional.empty();
        } else {
            return Optional.of(date);
        }
    }

    private static DateParser getParsersChain() {
        return new FirstDateParser();
    }
}
