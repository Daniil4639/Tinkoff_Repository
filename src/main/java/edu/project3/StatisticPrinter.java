package edu.project3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class StatisticPrinter {

    private final static String SEPARATOR = "|:---------------------:|--------------:|";
    private final static String SPECIFIC_OUTPUT_FORMAT = "|%-23s|%-15s|";
    private final static String COUNT_STRING = "Number";
    private final static String DATE_PATTERN = "dd.MM.yyyy";
    private final static int SPECIFIC_FIRST_FIELD_WIDTH = 23;
    private final static int SPECIFIC_SECOND_FIELD_WIDTH = 15;
    private final static String SPECIFIC_CODES_OUTPUT_FORMAT = "|%-6s|%-23s|%-15s|";
    private final static String CODES_SEPARATOR = "|:----:|:---------------------:|--------------:|";
    private final static String FILE_NAME = "Log_Statistic.";
    private final static String LINE_SWITCH = System.lineSeparator();
    private final static Map<Integer, String> CODE_NAMES = Map.ofEntries(
        Map.entry(200, "OK"),
        Map.entry(404, "Not Found"),
        Map.entry(500, "Internal Server Error"),
        Map.entry(201, "Created"),
        Map.entry(204, "No Content"),
        Map.entry(304, "Not Modified"),
        Map.entry(410, "Gone"),
        Map.entry(400, "Bad Request"),
        Map.entry(423, "Locked"),
        Map.entry(416, "Range Not Satisfiable"),
        Map.entry(206, "Partial Content")
    );

    private final String fileFormat;
    private final DataAnalyzer dataAnalyzer;
    private final StringBuilder result;

    public StatisticPrinter(DataAnalyzer dataAnalyzer) {
        this.dataAnalyzer = dataAnalyzer;
        this.fileFormat = dataAnalyzer.format;
        this.result = new StringBuilder();
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public void printData() {
        dataAnalyzer.fileNames.sort(Comparator.naturalOrder());
        printMetric();
        printResources();
        printAgent();
        printCodes();
        printIP();
        System.out.println(result);

        if (fileFormat != null) {
            printToFIle();
        }
    }

    public String getResultString() {
        return result.toString();
    }

    private void printToFIle() {
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(
            Paths.get(FILE_NAME + fileFormat),
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING))) {

            outputStream.write(result.toString().getBytes());
        } catch (IOException ignored) {
        }
    }

    private void printMetric() {
        result.append(LINE_SWITCH);
        result.append("### General Information").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
            centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Metric"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, "Value"))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "File(s)"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH,
                (dataAnalyzer.fileNames.isEmpty()) ? ("-") : (dataAnalyzer.fileNames.get(0))))).append(LINE_SWITCH);
        for (int nameIndex = 1; nameIndex < dataAnalyzer.fileNames.size(); nameIndex++) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT, "",
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, dataAnalyzer.fileNames.get(nameIndex)))).append(LINE_SWITCH);
        }
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Starting date"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, (dataAnalyzer.dateFrom == null) ? ("-")
                : (DateTimeFormatter.ofPattern(DATE_PATTERN).format(dataAnalyzer.dateFrom))))).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Final date"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, (dataAnalyzer.dateTo == null) ? ("-")
                : (DateTimeFormatter.ofPattern(DATE_PATTERN).format(dataAnalyzer.dateTo))))).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
            centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Number of requests"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, dataAnalyzer.requestCount.toString()))).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
            centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Average request size"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH,
                (Objects.equals(dataAnalyzer.requestCount, BigInteger.ZERO)) ? ("-")
                    : (dataAnalyzer.requestSize.divide(dataAnalyzer.requestCount)) + "b"))).append(LINE_SWITCH);
    }

    private void printResources() {
        result.append(LINE_SWITCH);
        result.append("### Requested resources").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Resource"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var resource: dataAnalyzer.resources.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "'/" + resource.getKey() + "'"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, resource.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printCodes() {
        result.append(LINE_SWITCH);
        result.append("### Response codes").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_CODES_OUTPUT_FORMAT, " Code ",
            centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Name"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(CODES_SEPARATOR).append(LINE_SWITCH);
        for (var code: dataAnalyzer.codes.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            result.append(String.format(SPECIFIC_CODES_OUTPUT_FORMAT, centerString(6, code.getKey().toString()),
                centerString(SPECIFIC_FIRST_FIELD_WIDTH,
                    (CODE_NAMES.get(code.getKey()) == null) ? ("?") : (CODE_NAMES.get(code.getKey()))),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, code.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printIP() {
        result.append(LINE_SWITCH);
        result.append("### IP addresses of requests").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "IP"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var ip: dataAnalyzer.ipCount.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, ip.getKey()),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, ip.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printAgent() {
        result.append(LINE_SWITCH);
        result.append("### User-Agent").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Agent name"),
            centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var name: dataAnalyzer.userAgent.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, name.getKey()),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, name.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private String centerString(int width, String s) {
        return String.format("%-" + width  + "s",
            String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}
