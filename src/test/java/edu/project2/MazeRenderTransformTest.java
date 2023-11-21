package edu.project2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeRenderTransformTest {

    @Test
    @DisplayName("Проверка работы transform()")
    void transformTest() {
        List<List<Cell>> testMaze = new ArrayList<>();

        List<Cell> raw1 = new ArrayList<>(List.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0)));
        List<Cell> raw2 = new ArrayList<>(List.of(new Cell(0, 1), new Cell(1, 1), new Cell(2, 1)));
        List<Cell> raw3 = new ArrayList<>(List.of(new Cell(0, 2), new Cell(1, 2), new Cell(2, 2)));
        raw1.get(0).removeWall(Direction.BOTTOM); raw2.get(0).removeWall(Direction.TOP);
        raw2.get(0).removeWall(Direction.BOTTOM); raw3.get(0).removeWall(Direction.TOP);
        raw3.get(0).removeWall(Direction.RIGHT); raw3.get(1).removeWall(Direction.LEFT);
        raw3.get(1).removeWall(Direction.TOP); raw2.get(1).removeWall(Direction.BOTTOM);
        raw2.get(1).removeWall(Direction.TOP); raw1.get(1).removeWall(Direction.BOTTOM);
        raw1.get(1).removeWall(Direction.RIGHT); raw1.get(2).removeWall(Direction.LEFT);
        raw1.get(2).removeWall(Direction.BOTTOM); raw2.get(2).removeWall(Direction.TOP);
        raw2.get(2).removeWall(Direction.BOTTOM); raw3.get(2).removeWall(Direction.TOP);

        testMaze.add(raw1); testMaze.add(raw2); testMaze.add(raw3);

        int[][] resultRight = {{1, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        Pair<Integer, Integer> point1 = new ImmutablePair<>(0, 0);
        Pair<Integer, Integer> point2 = new ImmutablePair<>(2, 0);

        List<List<Pair<Integer, Integer>>> previousTest = MazeBFSSearch.findWay(testMaze,
            point1, point2);

        int[][] pathResult = MazeRender.transform(previousTest, point1, point2);

        assertThat(pathResult).isEqualTo(resultRight);
    }
}
