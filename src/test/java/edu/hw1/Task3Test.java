package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка работы isNestable()")
    void testIsNestable() {
        long[] example1_1 = {12, 18, 33, 59, 102}, example1_2 = {144, 9},
            example2_1 = {14, 7}, example2_2 = {3, 15},
            example3_1 = {-111, 64, 312}, example3_2 = {312, -150},
            example4_1 = {3, 42, -204, 70}, example4_2 = {-105, 22, 5},
            example5_1 = {}, example5_2 = {2, 3, 17},
            example6_1 = {3, 5, 12}, example6_2 = null;

        boolean[] correctArr = {Task3.isNestable(example1_1, example1_2),
            Task3.isNestable(example2_1, example2_2),
            Task3.isNestable(example3_1, example3_2),
            Task3.isNestable(example4_1, example4_2),
            Task3.isNestable(example5_1, example5_2),
            Task3.isNestable(example6_1, example6_2)};

        assertThat(correctArr).containsExactly(true, true, false, false, false, false);
    }
}
