package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Проверка работы rotateLeft() и rotateRight()")
    void testRotateLeftAndRight() {

        int[] correctArrLeft = {Task7.rotateLeft(56, 1),
            Task7.rotateLeft(102, 4),
            Task7.rotateLeft(0, 7),
            Task7.rotateLeft(23, -3),
            Task7.rotateLeft(-75, 9)};

        int[] correctArrRight = {Task7.rotateRight(56, 1),
            Task7.rotateRight(102, 4),
            Task7.rotateRight(0, 7),
            Task7.rotateRight(23, -3),
            Task7.rotateRight(-75, 9)};

        assertThat(correctArrLeft).containsExactly(49, 108, 0, 23, -75);
        assertThat(correctArrRight).containsExactly(28, 54, 0, 23, -75);
    }
}
