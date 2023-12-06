package edu.hw9;

import edu.hw9.Task3.MazeDFSSearchThread;
import edu.project2.MazeGenerator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка работы MazeDFSSearchThread")
    void mazeDFSSearchThreadTest() {
        Pair<Integer, Integer> point1 = new ImmutablePair<>(0, 0);
        Pair<Integer, Integer> point2 = new ImmutablePair<>(99, 99);
        MazeGenerator maze = new MazeGenerator(100, 100);
        maze.generate();

        List<List<Pair<Integer, Integer>>> wayArray = MazeDFSSearchThread.findWay(maze.getMaze(), point1, point2);

        Pair<Integer, Integer> position = new ImmutablePair<>(99, 99);
        boolean hasWay = false;

        while (wayArray.get(position.getRight()).get(position.getLeft()) != null) {
            if (Objects.equals(position.getLeft(), point1.getLeft())
                && Objects.equals(position.getRight(), point1.getRight())) {
                hasWay = true;
                break;
            }
            position = wayArray.get(position.getRight()).get(position.getLeft());
        }

        assertThat(hasWay).isTrue();
    }
}
