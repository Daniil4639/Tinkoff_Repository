package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LocalPathCheckerTest {

    @Test
    @DisplayName("Проверка работы LocalPathCheckerTest")
    void localPathCheckerTest() {
        DataAnalyzer dataAnalyzer = new DataAnalyzer(null, null, null);

        LocalPathChecker.checkPath(dataAnalyzer, "test/*");

        assertThat(dataAnalyzer.fileNames.stream().sorted().toArray()).containsExactly("test.txt",
            "test2.txt", "test3.txt");

        assertThat(dataAnalyzer.requestCount.intValue()).isEqualTo(3);

        assertThat(dataAnalyzer.resources.values().stream().sorted().toArray()).containsExactly(1, 2);

        assertThat(dataAnalyzer.resources.keySet().stream().sorted().toArray()).containsExactly("product_1",
            "product_2");

        assertThat(dataAnalyzer.userAgent.keySet().stream().sorted().toArray()).containsExactly("Debian",
            "Maven");

        assertThat(dataAnalyzer.requestSize.intValue()).isEqualTo(624);
    }
}
