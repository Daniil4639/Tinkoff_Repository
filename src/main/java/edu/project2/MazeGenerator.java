package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeGenerator {

    public static final Map<Direction, Direction> OPPOSITE_DIRECTION = Map.ofEntries(
        Map.entry(Direction.TOP, Direction.BOTTOM),
        Map.entry(Direction.BOTTOM, Direction.TOP),
        Map.entry(Direction.LEFT, Direction.RIGHT),
        Map.entry(Direction.RIGHT, Direction.LEFT)
    );

    private final List<List<Cell>> cells;
    private final int width;
    private final int height;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>(height);

        for (int externalIterator = 0; externalIterator < height; externalIterator++) {
            List<Cell> raw = new ArrayList<>();

            for (int internalIterator = 0; internalIterator < width; internalIterator++) {
                raw.add(new Cell(internalIterator, externalIterator));
            }

            cells.add(raw);
        }


    }

    public List<List<Cell>> getMaze() {
        return cells;
    }

    public List<Pair<Cell, Direction>> getCellUncheckedNeighbors(Cell cell) {
        List<Pair<Cell, Direction>> neighbors = new ArrayList<>();

        if (cell.getY() > 0 && cells.get(cell.getY() - 1).get(cell.getX()).isNotChecked()
            && cell.hasWall(Direction.TOP)) {

            neighbors.add(new ImmutablePair<>(cells.get(cell.getY() - 1).get(cell.getX()), Direction.TOP));
        }
        if (cell.getY() < height - 1 && cells.get(cell.getY() + 1).get(cell.getX()).isNotChecked()
            && cell.hasWall(Direction.BOTTOM)) {

            neighbors.add(new ImmutablePair<>(cells.get(cell.getY() + 1).get(cell.getX()), Direction.BOTTOM));
        }
        if (cell.getX() > 0 && cells.get(cell.getY()).get(cell.getX() - 1).isNotChecked()
            && cell.hasWall(Direction.LEFT)) {

            neighbors.add(new ImmutablePair<>(cells.get(cell.getY()).get(cell.getX() - 1), Direction.LEFT));
        }
        if (cell.getX() < width - 1 && cells.get(cell.getY()).get(cell.getX() + 1).isNotChecked()
            && cell.hasWall(Direction.RIGHT)) {

            neighbors.add(new ImmutablePair<>(cells.get(cell.getY()).get(cell.getX() + 1), Direction.RIGHT));
        }

        return neighbors;
    }

    public void generate() {
        Stack<Cell> cellsInOrder = new Stack<>();
        cellsInOrder.add(cells.get(0).get(0));
        cellsInOrder.peek().makeChecked();

        while (!cellsInOrder.empty()) {
            List<Pair<Cell, Direction>> neighbors = getCellUncheckedNeighbors(cellsInOrder.peek());

            if (neighbors.isEmpty()) {
                cellsInOrder.peek().makeChecked();
                cellsInOrder.pop();
            } else {
                int directionChoice = (int) (Math.random() * neighbors.size());
                cellsInOrder.peek().removeWall(neighbors.get(directionChoice).getValue());

                cellsInOrder.add(neighbors.get(directionChoice).getKey());
                cellsInOrder.peek().makeChecked();
                cellsInOrder.peek().removeWall(OPPOSITE_DIRECTION.get(neighbors.get(directionChoice).getValue()));
            }
        }
    }
}
