package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Проверка работы countDigits()")
    void testCountDigits() {
        long example1 = 8739, example2 = 198398719, example3 = 9, example4 = -123;

        int[] correctArr = {Task2.countDigits(example1),
            Task2.countDigits(example2),
            Task2.countDigits(example3),
            Task2.countDigits(example4)};

        assertThat(correctArr).containsExactly(4, 9, 1, 3);
    }
}
