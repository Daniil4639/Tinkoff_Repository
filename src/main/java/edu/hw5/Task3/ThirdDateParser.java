package edu.hw5.Task3;

import java.time.LocalDate;

public class ThirdDateParser extends DateParser {
    public ThirdDateParser() {
        nextParser = new FourthDateParser();
    }

    @Override
    public LocalDate stringTransform(String string) {
        LocalDate result;
        if (string.equals("tomorrow")) {
            result = LocalDate.now().plusDays(1);
        } else if (string.equals("today")) {
            result = LocalDate.now();
        } else if (string.equals("yesterday")) {
            result = LocalDate.now().minusDays(1);
        } else if (nextParser != null) {
            result = nextParser.stringTransform(string);
        } else {
            result = null;
        }

        return result;
    }
}
