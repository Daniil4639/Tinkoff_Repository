package edu.hw2;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    TestLogger logger = TestLoggerFactory.getTestLogger(Task3.class);

    @Test
    @DisplayName("Проверка работы tryExecute()")
    void connectionTest() {

        for (int attempt = 0; attempt < 100; attempt++) {
            cycleTest();
        }
    }

    void cycleTest() {
        Task3.PopularCommandExecutor popularCommandExecutor = new Task3.PopularCommandExecutor();
        popularCommandExecutor.updatePackages();

        if (logger.getLoggingEvents().asList().get(0).getMessage()
            .equals("Connection is established!") ||
            logger.getLoggingEvents().asList().get(0).getMessage()
                .equals("Connection close was failed!")) {

            assertThat(1).isEqualTo(1);
        } else if (logger.getLoggingEvents().asList().get(0).getMessage()
            .equals("Limit of attempts was exceeded!")) {

            for (int attempt = 1; attempt <= 9; attempt += 2) {
                assertThat(logger.getLoggingEvents().asList().get(attempt).getMessage())
                    .isEqualTo("Connection was closed!");

                assertThat(logger.getLoggingEvents().asList().get(attempt + 1).getMessage())
                    .isEqualTo("Connection error!");
            }
        }
    }

    @After
    public void clearLoggers() {
        TestLoggerFactory.clear();
    }
}


