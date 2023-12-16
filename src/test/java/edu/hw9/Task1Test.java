package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Проверка работы StatsCollector")
    void statsCollectorTest() throws InterruptedException {
        StatsCollector collector = new StatsCollector();

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        var futures = Stream.generate(() -> CompletableFuture.runAsync(() -> {
            collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        }, executorService)).limit(100).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        executorService.close();

        Thread.sleep(1000);

        var metrics = collector.stats();
        for (var metric: metrics) {
            LOGGER.info(metric.getKey() + Math.round(metric.getValue() * 100) / 100.0);
        }

        assertThat(Math.round(metrics.get(0).getValue())).isEqualTo(695);
        assertThat(Math.round(metrics.get(1).getValue() * 100) / 100.0).isEqualTo(1.39);
        assertThat(metrics.get(2).getValue()).isEqualTo(0.05);
        assertThat(metrics.get(3).getValue()).isEqualTo(5.1);
    }
}
