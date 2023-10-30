package edu.project2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeBFSSearchTest {

    @Test
    @DisplayName("Проверка работы findWay()")
    void findWayTest() {
        Pair<Integer, Integer> point1 = new ImmutablePair<>(0, 0);
        Pair<Integer, Integer> point2 = new ImmutablePair<>(9, 9);
        MazeGenerator maze = new MazeGenerator(10, 10);
        maze.generate();

        Pair[][] wayArray = MazeBFSSearch.findWay(maze.getMaze(), point1, point2);

        Pair position = new ImmutablePair<>(9, 9);
        boolean hasWay = false;

        while (wayArray[(int) position.getRight()][(int) position.getLeft()] != null) {
            if (Objects.equals(position.getLeft(), point1.getLeft())
                && Objects.equals(position.getRight(), point1.getRight())) {
                hasWay = true;
                break;
            }
            position = wayArray[(int) position.getRight()][(int) position.getLeft()];
        }

        assertThat(hasWay).isTrue();
    }
}
