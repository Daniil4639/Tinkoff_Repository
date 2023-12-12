package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private Main() {}

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {

        List<String> urlLinks = new ArrayList<>();
        List<String> localPath = new ArrayList<>();

        LocalDate dateFrom = null;
        LocalDate dateTo = null;

        String format = null;

        for (int index = 0; index < args.length;) {
            switch (args[index]) {
                case "--path":
                    index++;
                    while (index < args.length && args[index].charAt(0) != '-') {
                        if (args[index].contains("http")) {
                            urlLinks.add(args[index++]);
                        } else {
                            localPath.add(args[index++]);
                        }
                    }
                    break;
                case "--from":
                    index++;
                    while (index < args.length && args[index].charAt(0) != '-') {
                        dateFrom = LocalDate.parse(args[index++], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    }
                    break;
                case "--to":
                    index++;
                    while (index < args.length && args[index].charAt(0) != '-') {
                        dateTo = LocalDate.parse(args[index++], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    }
                    break;
                case "--format":
                    index++;
                    while (index < args.length && args[index].charAt(0) != '-') {
                        format = args[index++];
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
