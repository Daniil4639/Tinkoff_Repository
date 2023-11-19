package edu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalPathChecker {

    private static final String TIME_PATTERN = "dd/MMMM/yyyy:hh:mm:ss";
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "([0-9.]+) - - \\[(\\S+) \\S+\\] \"GET .*/(.+) .*\" (\\d+) (\\d+) \".+\" \"([0-9A-Za-z/.-]+) .+\"");

    private enum GROUPS {
        UNKNOWN,
        IP,
        TIME,
        RESOURCE,
        CODE,
        SIZE,
        AGENT
    }

    private LocalPathChecker() {}

    private static String getDirectory(String path) {
        int lastSlash = path.lastIndexOf("/", (!path.contains("*")) ? (path.length()) : (path.indexOf("*")));

        if (lastSlash < path.indexOf("*")) {
            lastSlash = path.indexOf("*");
        }

        return path.substring(0, (lastSlash == -1) ? (path.length()) : (lastSlash));
    }

    private static String getName(String path) {
        if (path.charAt(path.length() - 1) == '/' || path.charAt(path.length() - 1) == '*') {
            return null;
        } else {
            return path.substring(path.lastIndexOf("/") + 1);
        }
    }

    private static void checkLog(DataAnalyzer dataAnalyzer, String log) throws ParseException {
        Matcher logMather = LOG_PATTERN.matcher(log);
        if (logMather.find()) {

            Date date = new SimpleDateFormat(TIME_PATTERN, Locale.US)
                .parse(logMather.group(LocalPathChecker.GROUPS.TIME.ordinal()));

            LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

            if ((dataAnalyzer.dateFrom == null || !dataAnalyzer.dateFrom.isAfter(localDate))
                && (dataAnalyzer.dateTo == null || dataAnalyzer.dateTo.isAfter(localDate))) {

                dataAnalyzer.ipCount.merge(logMather.group(LocalPathChecker.GROUPS.IP.ordinal()),
                    1, Integer::sum
                );
                dataAnalyzer.codes.merge(Integer.valueOf(logMather.group(
                        LocalPathChecker.GROUPS.CODE.ordinal())),
                    1, Integer::sum
                );
                dataAnalyzer.userAgent.merge(logMather.group(LocalPathChecker.GROUPS.AGENT.ordinal()),
                    1, Integer::sum);
                dataAnalyzer.resources.merge(logMather.group(LocalPathChecker.GROUPS.RESOURCE.ordinal()),
                    1, Integer::sum
                );
                dataAnalyzer.requestCount = dataAnalyzer.requestCount.add(BigInteger.ONE);
                dataAnalyzer.requestSize = dataAnalyzer.requestSize.add(BigInteger.valueOf(Long.parseLong(
                    logMather.group(GROUPS.SIZE.ordinal()))));
            }
        }
    }

    private static void checkFile(DataAnalyzer dataAnalyzer, File file, String name) {
        if (!file.isDirectory()) {
            if (file.getName().equals(name) || name == null) {

                dataAnalyzer.fileNames.add(file.getName());

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    String log = bufferedReader.readLine();

                        checkLog(dataAnalyzer, log);
                } catch (IOException | ParseException ignored) {
                }
            }
        } else {
            for (File newFile: Objects.requireNonNull(file.listFiles())) {
                checkFile(dataAnalyzer, newFile, name);
            }
        }
    }

    public static void checkPath(DataAnalyzer dataAnalyzer, String path) {
        String directory = getDirectory(path);
        String name = getName(path);

        File file = new File(directory);

        checkFile(dataAnalyzer, file, name);
    }
}
