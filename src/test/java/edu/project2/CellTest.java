package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CellTest {

    @Test
    @DisplayName("Проверка работы hasWall")
    void hasWallTest() {
        Cell cell = new Cell(0, 0);
        cell.removeWall(Cell.DIRECTION.LEFT);
        cell.removeWall(Cell.DIRECTION.BOTTOM);

        assertThat(cell.hasWall(Cell.DIRECTION.TOP)).isTrue();
        assertThat(cell.hasWall(Cell.DIRECTION.BOTTOM)).isFalse();
        assertThat(cell.hasWall(Cell.DIRECTION.RIGHT)).isTrue();
        assertThat(cell.hasWall(Cell.DIRECTION.LEFT)).isFalse();
    }

    @Test
    @DisplayName("Проверка работы remove- и addWall")
    void removeAddWallTest() {
        Cell cell = new Cell(0, 0);
        assertThat(cell.hasWall(Cell.DIRECTION.TOP)).isTrue();

        cell.removeWall(Cell.DIRECTION.TOP);
        assertThat(cell.hasWall(Cell.DIRECTION.TOP)).isFalse();

        cell.addWall(Cell.DIRECTION.TOP);
        assertThat(cell.hasWall(Cell.DIRECTION.TOP)).isTrue();
    }
}
