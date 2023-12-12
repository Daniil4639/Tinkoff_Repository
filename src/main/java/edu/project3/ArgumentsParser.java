package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private ArgumentsParser() {}

    public static void parse(String[] arguments) {
        List<String> urlLinks = new ArrayList<>();
        List<String> localPath = new ArrayList<>();

        LocalDate dateFrom = null;
        LocalDate dateTo = null;

        String format = null;

        for (int index = 0; index < arguments.length;) {
            switch (arguments[index]) {
                case "--path":
                    index++;
                    while (index < arguments.length && arguments[index].charAt(0) != '-') {
                        if (arguments[index].contains("http")) {
                            urlLinks.add(arguments[index++]);
                        } else {
                            localPath.add(arguments[index++]);
                        }
                    }
                    break;
                case "--from":
                    index++;
                    while (index < arguments.length && arguments[index].charAt(0) != '-') {
                        dateFrom = LocalDate.parse(arguments[index++], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    }
                    break;
                case "--to":
                    index++;
                    while (index < arguments.length && arguments[index].charAt(0) != '-') {
                        dateTo = LocalDate.parse(arguments[index++], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    }
                    break;
                case "--format":
                    index++;
                    while (index < arguments.length && arguments[index].charAt(0) != '-') {
                        format = arguments[index++];
                    }
                    break;
                default:
                    index++;
            }
        }

        DataAnalyzer dataAnalyzer = new DataAnalyzer(dateFrom, dateTo, format);
        dataAnalyzer.makeLinksAnalysis(urlLinks, localPath);
    }
}
