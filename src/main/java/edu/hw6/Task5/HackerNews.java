package edu.hw6.Task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;

public class HackerNews {

    private static final String FILE_NEWS_NUMBERS_NAME = "file_numbers.json";
    private static final String FILE_NEWS_TEXT_NAME = "file_news.json";
    private static final Pattern ARTICLE_NAME_PATTERN = Pattern.compile("\"title\":\"(.+)\",\"type");

    private HackerNews() {}

    private static void makeRequest(HttpClient client, String path, String fileName)
        throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(path))
            .GET()
            .build();

        client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(fileName)));
    }

    public static long[] hackerNewsTopStories() {
        try (HttpClient client = HttpClient.newHttpClient()) {

            makeRequest(client, "https://hacker-news.firebaseio.com/v0/topstories.json", FILE_NEWS_NUMBERS_NAME);

            File newsNumbersFile = new File(FILE_NEWS_NUMBERS_NAME);
            String codes = null;

            try (BufferedReader newsReader = new BufferedReader(new FileReader(newsNumbersFile))) {
                codes = newsReader.readLine();
            } catch (IOException ignored) {
            }

            newsNumbersFile.delete();

            if (codes == null) {
                throw new IOException();
            } else {
                String[] newsNumbers = codes.substring(1, codes.length() - 1).split(",");

                return ArrayUtils.toPrimitive(Arrays.stream(newsNumbers).map(Long::parseLong).toArray(Long[]::new));
            }
        } catch (IOException | InterruptedException error) {
            return new long[0];
        }
    }

    public static String news(long id) {
        try (HttpClient client = HttpClient.newHttpClient()) {

            makeRequest(client, "https://hacker-news.firebaseio.com/v0/item/" + id + ".json", FILE_NEWS_TEXT_NAME);

            File newsFile = new File(FILE_NEWS_TEXT_NAME);
            String article = null;

            try (BufferedReader newsReader = new BufferedReader(new FileReader(newsFile))) {
                article = newsReader.readLine();
            } catch (IOException ignored) {
            }

            newsFile.delete();

            if (article == null) {
                throw new IOException();
            }

            Matcher nameMatcher = ARTICLE_NAME_PATTERN.matcher(article);

            if (nameMatcher.find()) {
                return nameMatcher.group(1);
            } else {
                throw new IOException();
            }
        } catch (IOException | InterruptedException error) {
            return null;
        }
    }
}
