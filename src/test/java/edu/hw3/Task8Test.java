package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    @DisplayName("Проверка работы BackwardIterator")
    void backwardIteratorTest() {
        Task8.BackwardIterator<Integer> backwardIterator = new Task8.BackwardIterator<>(List.of(1, 2, 3));

        ArrayList<Integer> outputArray = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            outputArray.add(backwardIterator.next());
        }

        assertThat(outputArray.get(0)).isEqualTo(3);
        assertThat(outputArray.get(1)).isEqualTo(2);
        assertThat(outputArray.get(2)).isEqualTo(1);
    }
}
