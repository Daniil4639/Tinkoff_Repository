package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        FourthDateParser fourthDateParser = new FourthDateParser(null);
        ThirdDateParser thirdDateParser = new ThirdDateParser(fourthDateParser);
        SecondDateParser secondDateParser = new SecondDateParser(thirdDateParser);
        return new FirstDateParser(secondDateParser);
    }

    private abstract static class DateParser {
        protected DateParser nextParser;

        private DateParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }

        public abstract LocalDate stringTransform(String string);
    }

    private static class FirstDateParser extends DateParser {
        private FirstDateParser(DateParser nextParser) {
            super(nextParser);
        }

        private static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-d");

        @Override
        public LocalDate stringTransform(String string) {
            if (string.matches("\\d{4}-\\d{2}-\\d{2}")) {

                return LocalDate.parse(string, DATE_FORMATTER_1);
            } else if (string.matches("\\d{4}-\\d{2}-\\d")) {

                return LocalDate.parse(string, DATE_FORMATTER_2);
            } else if (nextParser != null) {
                return nextParser.stringTransform(string);
            } else {
                return null;
            }
        }
    }

    private static class SecondDateParser extends DateParser {
        private SecondDateParser(DateParser nextParser) {
            super(nextParser);
        }

        private static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormatter.ofPattern("d/M/yyyy");
        private static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("d/M/yy");

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

    private static class ThirdDateParser extends DateParser {
        private ThirdDateParser(DateParser nextParser) {
            super(nextParser);
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

    private static class FourthDateParser extends DateParser {
        private FourthDateParser(DateParser nextParser) {
            super(nextParser);
        }

        private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d+) .* ago$");

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
}
