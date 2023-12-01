package edu.hw8;

import edu.hw8.Task1.WiseQuotesClient;
import edu.hw8.Task1.WiseQuotesServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка работы WiseQuotesServer")
    void wiseQuotesServerTest() {
        boolean testOk = true;

        List<String> questions = new ArrayList<>(List.of("глупый", "личность", "оскорбления", "интеллект"));

        try {
            Thread serverThread = new Thread(() -> {
                WiseQuotesServer server = new WiseQuotesServer();
                server.open();
            });

            serverThread.start();
            Thread.sleep(1000);

            List<Thread> requests = Stream.generate(() -> new Thread(() -> {
                WiseQuotesClient.makeRequest(questions.get(ThreadLocalRandom.current().nextInt(3)));

                WiseQuotesClient.makeRequest(questions.get(ThreadLocalRandom.current().nextInt(3)));
            })).limit(8).toList();

            for (var request: requests) {
                request.start();
                Thread.sleep(200);
            }

            for (var request: requests) {
                request.join();
            }

            WiseQuotesClient.closeClient();

            serverThread.join();

        } catch (RuntimeException | InterruptedException error) {
            testOk = false;
        }

        assertThat(testOk).isTrue();
    }
}
