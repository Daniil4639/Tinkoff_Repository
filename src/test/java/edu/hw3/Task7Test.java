package edu.hw3;

import edu.hw3.Task7.Task7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Проверяем работу TreeMap с компаратором")
    void tryTreeMapWithComparator() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.ComparatorWithNullFirst<>());

        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
