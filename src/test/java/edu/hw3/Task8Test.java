package edu.hw3;

import edu.hw3.Task8.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    @DisplayName("Проверка работы BackwardIterator")
    void backwardIteratorTest() {
        Task8.BackwardIterator<Integer> backwardIterator = new Task8.BackwardIterator<>(Arrays.asList(1, 2, 3, null));

        ArrayList<Integer> outputArray = new ArrayList<>();

        while (backwardIterator.hasNext()) {
            outputArray.add(backwardIterator.next());
        }

        assertThat(outputArray.get(0)).isEqualTo(null);
        assertThat(outputArray.get(1)).isEqualTo(3);
        assertThat(outputArray.get(2)).isEqualTo(2);
        assertThat(outputArray.get(3)).isEqualTo(1);
    }
}
