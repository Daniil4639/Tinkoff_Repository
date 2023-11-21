package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CellTest {

    @Test
    @DisplayName("Проверка работы hasWall")
    void hasWallTest() {
        Cell cell = new Cell(0, 0);
        cell.removeWall(Direction.LEFT);
        cell.removeWall(Direction.BOTTOM);

        assertThat(cell.hasWall(Direction.TOP)).isTrue();
        assertThat(cell.hasWall(Direction.BOTTOM)).isFalse();
        assertThat(cell.hasWall(Direction.RIGHT)).isTrue();
        assertThat(cell.hasWall(Direction.LEFT)).isFalse();
    }

    @Test
    @DisplayName("Проверка работы remove- и addWall")
    void removeAddWallTest() {
        Cell cell = new Cell(0, 0);
        assertThat(cell.hasWall(Direction.TOP)).isTrue();

        cell.removeWall(Direction.TOP);
        assertThat(cell.hasWall(Direction.TOP)).isFalse();

        cell.addWall(Direction.TOP);
        assertThat(cell.hasWall(Direction.TOP)).isTrue();
    }
}
