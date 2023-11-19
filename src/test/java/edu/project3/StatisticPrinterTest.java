package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticPrinterTest {

    private static final String RESULT_STRING = System.lineSeparator() +
        "### General Information" + System.lineSeparator() +
        System.lineSeparator() +
        "|        Metric         |     Value     |" + System.lineSeparator() +
        "|:---------------------:|--------------:|" + System.lineSeparator() +
        "|        File(s)        |   test.txt    |" + System.lineSeparator() +
        "|                       |   test2.txt   |" + System.lineSeparator() +
        "|                       |   test3.txt   |" + System.lineSeparator() +
        "|     Starting date     |       -       |" + System.lineSeparator() +
        "|      Final date       |       -       |" + System.lineSeparator() +
        "|  Number of requests   |       3       |" + System.lineSeparator() +
        "| Average request size  |     208b      |" + System.lineSeparator() +
        System.lineSeparator() +
        "### Requested resources" + System.lineSeparator() +
        System.lineSeparator() +
        "|       Resource        |    Number     |" + System.lineSeparator() +
        "|:---------------------:|--------------:|" + System.lineSeparator() +
        "|     '/product_2'      |       1       |" + System.lineSeparator() +
        "|     '/product_1'      |       2       |" + System.lineSeparator() +
        System.lineSeparator() +
        "### User-Agent" + System.lineSeparator() +
        System.lineSeparator() +
        "|      Agent name       |    Number     |" + System.lineSeparator() +
        "|:---------------------:|--------------:|" + System.lineSeparator() +
        "|         Maven         |       1       |" + System.lineSeparator() +
        "|        Debian         |       2       |" + System.lineSeparator() +
        System.lineSeparator() +
        "### Response codes" + System.lineSeparator() +
        System.lineSeparator() +
        "| Code |         Name          |    Number     |" + System.lineSeparator() +
        "|:----:|:---------------------:|--------------:|" + System.lineSeparator() +
        "| 304 |     Not Modified      |       1       |" + System.lineSeparator() +
        "| 404 |       Not Found       |       1       |" + System.lineSeparator() +
        "| 200 |          OK           |       1       |" + System.lineSeparator() +
        System.lineSeparator() +
        "### IP addresses of requests" + System.lineSeparator() +
        System.lineSeparator() +
        "|          IP           |    Number     |" + System.lineSeparator() +
        "|:---------------------:|--------------:|" + System.lineSeparator() +
        "|      93.180.71.3      |       2       |" + System.lineSeparator() +
        "|     80.91.33.133      |       1       |" + System.lineSeparator();

    @Test
    @DisplayName("Проверка работы StatisticPrinter")
    void statisticPrinterTest() {
        DataAnalyzer dataAnalyzer = new DataAnalyzer(null, null, "md");
        LocalPathChecker.checkPath(dataAnalyzer, "test*");

        StatisticPrinter statisticPrinter = new StatisticPrinter(dataAnalyzer);
        statisticPrinter.printData();
        File logFile = new File("Log_Statistic.md");
        assertThat(logFile.exists()).isTrue();
        logFile.delete();

        assertThat(statisticPrinter.getResultString()).isEqualTo(RESULT_STRING);
    }
}
