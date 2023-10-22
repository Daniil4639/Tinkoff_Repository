package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Проверяем работу TreeMap с компаратором")
    void tryTreeMapWithComparator() {
        TreeMap<String, String> tree = new TreeMap<>(Comparator.nullsFirst(Comparator.naturalOrder()));

        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
