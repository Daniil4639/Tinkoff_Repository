package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final int x;
    private final int y;
    private boolean checked;
    private final List<Boolean> walls;

    public Cell(int x, int y) {
        checked = false;
        walls = new ArrayList<>(List.of(true, true, true, true));
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasWall(Direction side) {

        return switch (side) {
            case TOP -> walls.get(0);
            case BOTTOM -> walls.get(1);
            case LEFT -> walls.get(2);
            case RIGHT -> walls.get(walls.size() - 1);
            default -> true;
        };
    }

    public void makeChecked() {
        this.checked = true;
    }

    public boolean isNotChecked() {
        return !checked;
    }

    public void addWall(Direction side) {
        switch (side) {
            case TOP -> walls.set(0, true);
            case BOTTOM -> walls.set(1, true);
            case LEFT -> walls.set(2, true);
            case RIGHT -> walls.set(walls.size() - 1, true);
            default -> {
                return;
            }
        }
    }

    public void removeWall(Direction side) {
        switch (side) {
            case TOP -> walls.set(0, false);
            case BOTTOM -> walls.set(1, false);
            case LEFT -> walls.set(2, false);
            case RIGHT -> walls.set(walls.size() - 1, false);
            default -> {
                return;
            }
        }
    }
}
