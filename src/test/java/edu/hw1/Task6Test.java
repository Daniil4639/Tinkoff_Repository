package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Проверка работы countK()")
    void testCountK() {
        int example1 = 3524, example2 = 6621, example3 = 6554, example4 = 1234, example5 = 934, example6 = -4829;

        int[] correctArr = {Task6.countK(example1), Task6.countK(example2), Task6.countK(example3),
            Task6.countK(example4), Task6.countK(example5), Task6.countK(example6)};

        assertThat(correctArr).containsExactly(3, 5, 4, 3, -1, -1);
    }
}
