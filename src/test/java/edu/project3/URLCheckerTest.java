package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class URLCheckerTest {

    @Test
    @DisplayName("Проверка работы URLChecker")
    void urlCheckerTest() {
        DataAnalyzer dataAnalyzer = new DataAnalyzer(LocalDate.of(2015, 6, 3),
            LocalDate.of(2015, 6, 3), null);

        URLChecker.checkLink(dataAnalyzer,
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        assertThat(dataAnalyzer.requestCount.intValue()).isEqualTo(2816);
        assertThat(dataAnalyzer.resources.keySet().stream().sorted().toArray()).containsExactly("product_1",
            "product_2", "product_3");
        assertThat(dataAnalyzer.resources.values().stream().sorted().toArray()).containsExactly(5,
            1138, 1673);

        assertThat(dataAnalyzer.codes.get(404)).isEqualTo(1891);
        assertThat(dataAnalyzer.fileNames.toArray()).containsExactly("/nginx_logs");
    }
}
