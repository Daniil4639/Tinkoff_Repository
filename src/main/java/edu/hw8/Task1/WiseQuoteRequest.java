package edu.hw8.Task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WiseQuoteRequest implements Runnable {

    private final static Map<String, String> QUOTES = Map.ofEntries(
        Map.entry("личность", "Не переходи на личности там, где их нет"),
        Map.entry("оскорбления", "Если твои противники перешли на личные "
            + "оскорбления, будь уверена — твоя победа не за горами"),
        Map.entry("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
            + "Ты просто бог идиотизма."),
        Map.entry("интеллект", "Чем ниже интеллект, тем громче оскорбления")
    );

    private final static Logger LOGGER = LogManager.getLogger();
    private static Socket client;
    private static String message;

    public WiseQuoteRequest(Socket client, String message) {
        WiseQuoteRequest.client = client;
        WiseQuoteRequest.message = message;
    }

    @Override
    public void run() {
        try (PrintWriter output = new PrintWriter(client.getOutputStream())) {

            output.println(QUOTES.get(message));
            output.flush();
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }
}
