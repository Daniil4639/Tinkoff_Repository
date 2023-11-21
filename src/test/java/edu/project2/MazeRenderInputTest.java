package edu.project2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeRenderInputTest {


    @Test
    @DisplayName("Проверка работы InputMazeSize() и InputPoints()")
    void inputTest() {
        String inputData = "fa" + System.lineSeparator() + "5" + System.lineSeparator() + "12" + System.lineSeparator()
            + "fa" + System.lineSeparator() + "5" + System.lineSeparator() + "-1" + System.lineSeparator() + "12" +
            System.lineSeparator() + "3";

        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        Pair<Integer, Integer> resSize = MazeRender.inputMazeSize();

        assertThat(new ImmutablePair<>(5, 12)).isEqualTo(resSize);

        List<List<Cell>> testMaze = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            List<Cell> raw = new ArrayList<>();
            for (int j = 0; j < 12; j++) {
                raw.add(null);
            }
            testMaze.add(raw);
        }

        Pair<Integer, Integer> resPoint = MazeRender.inputPoints(testMaze, "A");

        assertThat(new ImmutablePair<>(5, 3)).isEqualTo(resPoint);
    }
}
