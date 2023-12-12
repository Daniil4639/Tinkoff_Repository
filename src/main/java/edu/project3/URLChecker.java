package edu.project3;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class URLChecker {

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

    private URLChecker() {}

    private static Stream<String> makeRequest(HttpClient client, String path)
        throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(path))
            .GET()
            .build();

        return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
    }

    public static void checkLink(DataAnalyzer dataAnalyzer, String urlPath) {

        AtomicLong requestCount = new AtomicLong(0);
        AtomicLong requestSize = new AtomicLong(0);

        dataAnalyzer.fileNames.add(urlPath.substring(urlPath.lastIndexOf("/")));

        try {
            HttpClient client = HttpClient.newHttpClient();
            Stream<String> logStream = makeRequest(client, urlPath);

            logStream.forEach(log -> {
                try {
                    Matcher logMather = LOG_PATTERN.matcher(log);

                    if (logMather.find()) {

                        Date date = new SimpleDateFormat(TIME_PATTERN, Locale.US)
                            .parse(logMather.group(GROUPS.TIME.ordinal()));

                        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

                        if ((dataAnalyzer.dateFrom == null || !dataAnalyzer.dateFrom.isAfter(localDate))
                            && (dataAnalyzer.dateTo == null || dataAnalyzer.dateTo.isAfter(localDate)
                            || dataAnalyzer.dateTo.equals(localDate))) {

                            dataAnalyzer.ipCount.merge(logMather.group(GROUPS.IP.ordinal()), 1, Integer::sum);
                            dataAnalyzer.codes.merge(Integer.valueOf(logMather.group(GROUPS.CODE.ordinal())),
                                1, Integer::sum);
                            dataAnalyzer.userAgent.merge(logMather.group(GROUPS.AGENT.ordinal()),
                                1, Integer::sum);
                            dataAnalyzer.resources.merge(logMather.group(GROUPS.RESOURCE.ordinal()), 1, Integer::sum);
                            requestCount.getAndIncrement();
                            requestSize.updateAndGet(v -> v + Long.parseLong(logMather.group(GROUPS.SIZE.ordinal())));
                        }
                    }
                } catch (ParseException ignored) {
                }
            });
        } catch (IOException | InterruptedException ignored) {
        }

        dataAnalyzer.requestCount = dataAnalyzer.requestCount.add(BigInteger.valueOf(requestCount.get()));
        dataAnalyzer.requestSize = dataAnalyzer.requestSize.add(BigInteger.valueOf(requestSize.get()));
    }
}
