package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Проверка работы fixString()")
    void testFixString() {
        String example1 = "9471387", example2 = " Iendem ro eopew!r", example3 = "oevsdgvernoisgj", example4 = "", example5 = null;

        String[] correctArr = {Task4.fixString(example1),
            Task4.fixString(example2),
            Task4.fixString(example3),
            Task4.fixString(example4),
            Task4.fixString(example5)};

        assertThat(correctArr).containsExactly("4917837", "I need more power!", "eosvgdevnriogsj", "", null);
    }
}
