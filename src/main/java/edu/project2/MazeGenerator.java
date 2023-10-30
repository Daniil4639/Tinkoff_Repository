package edu.project2;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeGenerator {

    public static final Map<Cell.DIRECTION, Cell.DIRECTION> OPPOSITE_DIRECTION = Map.ofEntries(
        Map.entry(Cell.DIRECTION.TOP, Cell.DIRECTION.BOTTOM),
        Map.entry(Cell.DIRECTION.BOTTOM, Cell.DIRECTION.TOP),
        Map.entry(Cell.DIRECTION.LEFT, Cell.DIRECTION.RIGHT),
        Map.entry(Cell.DIRECTION.RIGHT, Cell.DIRECTION.LEFT)
    );

    private Cell[][] cells;
    private int width;
    private int height;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];

        for (int externalIterator = 0; externalIterator < height; externalIterator++) {
            for (int internalIterator = 0; internalIterator < width; internalIterator++) {
                cells[externalIterator][internalIterator] = new Cell(internalIterator, externalIterator);
            }
        }
    }

    public Cell[][] getMaze() {
        return cells;
    }

    private Cell getCell(int x, int y) {

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }

        return cells[y][x];
    }

    public ArrayList<Pair<Cell, Cell.DIRECTION>> getCellUncheckedNeighbors(Cell cell) {
        ArrayList<Pair<Cell, Cell.DIRECTION>> neighbors = new ArrayList<>();

        if (cell.getY() > 0 && !cells[cell.getY() - 1][cell.getX()].isChecked()
            && cell.hasWall(Cell.DIRECTION.TOP)) {

            neighbors.add(new ImmutablePair<>(cells[cell.getY() - 1][cell.getX()], Cell.DIRECTION.TOP));
        }
        if (cell.getY() < height - 1 && !cells[cell.getY() + 1][cell.getX()].isChecked()
            && cell.hasWall(Cell.DIRECTION.BOTTOM)) {

            neighbors.add(new ImmutablePair<>(cells[cell.getY() + 1][cell.getX()], Cell.DIRECTION.BOTTOM));
        }
        if (cell.getX() > 0 && !cells[cell.getY()][cell.getX() - 1].isChecked()
            && cell.hasWall(Cell.DIRECTION.LEFT)) {

            neighbors.add(new ImmutablePair<>(cells[cell.getY()][cell.getX() - 1], Cell.DIRECTION.LEFT));
        }
        if (cell.getX() < width - 1 && !cells[cell.getY()][cell.getX() + 1].isChecked()
            && cell.hasWall(Cell.DIRECTION.RIGHT)) {

            neighbors.add(new ImmutablePair<>(cells[cell.getY()][cell.getX() + 1], Cell.DIRECTION.RIGHT));
        }

        return neighbors;
    }

    public void generate() {
        Stack<Cell> cellsInOrder = new Stack<>();
        cellsInOrder.add(cells[0][0]);
        cellsInOrder.peek().makeChecked();

        while (!cellsInOrder.empty()) {
            ArrayList<Pair<Cell, Cell.DIRECTION>> neighbors = getCellUncheckedNeighbors(cellsInOrder.peek());

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
