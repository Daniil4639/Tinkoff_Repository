package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка работы minutesToSeconds()")
    void testMinutesToSeconds() {
        String example1 = "12:00", example2 = "20:32", example3 = "00:26", example4 = "02:83", example5 = "12:43:14";

        Long[] correctArr = {Task1.minutesToSeconds(example1),
            Task1.minutesToSeconds(example2),
            Task1.minutesToSeconds(example3),
            Task1.minutesToSeconds(example4),
            Task1.minutesToSeconds(example5)};

        assertThat(correctArr).containsExactly(720L, 1232L, 26L, -1L, -1L);
    }
}
