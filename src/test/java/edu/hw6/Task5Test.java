package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Проверка работы HackerNews")
    void HackerNewsTest() {
        long[] testArray = HackerNews.hackerNewsTopStories();

        assertThat(testArray).isNotEmpty();

        //проверим хотя бы пять идентификаторов
        for (int identifier = 0; identifier < 5 && identifier < testArray.length; identifier++) {
            assertThat(HackerNews.news(testArray[identifier])).isNotNull();
        }
    }
}
