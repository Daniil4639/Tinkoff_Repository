package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Проверка работы isPalindromeDescendant()")
    void testIsPalindromeDescendant() {
        long example1 = 11211230, example2 = 432112, example3 = 23336014, example4 = 11, example5 = -12321, example6 = 5311028;

        boolean[] correctArr = {Task5.isPalindromeDescendant(example1),
            Task5.isPalindromeDescendant(example2),
            Task5.isPalindromeDescendant(example3),
            Task5.isPalindromeDescendant(example4),
            Task5.isPalindromeDescendant(example5),
            Task5.isPalindromeDescendant(example6)};

        assertThat(correctArr).containsExactly(true, false, true, true, false, true);
    }
}
