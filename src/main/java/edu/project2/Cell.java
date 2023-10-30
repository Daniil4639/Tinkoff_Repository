package edu.project2;

public class Cell {

    public enum DIRECTION {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private final int x;
    private final int y;
    private boolean checked;
    private final boolean[] walls;

    public Cell(int x, int y) {
        checked = false;
        walls = new boolean[] {true, true, true, true};
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasWall(DIRECTION side) {
        boolean answer;

        switch (side) {
            case TOP:
                answer = walls[0];
                break;
            case BOTTOM:
                answer = walls[1];
                break;
            case LEFT:
                answer = walls[2];
                break;
            case RIGHT:
                answer = walls[walls.length - 1];
                break;
            default:
                answer = true;
        }

        return answer;
    }

    public void makeChecked() {
        this.checked = true;
    }

    public void makeUnchecked() {
        this.checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void addWall(DIRECTION side) {
        switch (side) {
            case TOP:
                walls[0] = true;
                break;
            case BOTTOM:
                walls[1] = true;
                break;
            case LEFT:
                walls[2] = true;
                break;
            case RIGHT:
                walls[walls.length - 1] = true;
                break;
            default:
        }
    }

    public void removeWall(DIRECTION side) {
        switch (side) {
            case TOP:
                walls[0] = false;
                break;
            case BOTTOM:
                walls[1] = false;
                break;
            case LEFT:
                walls[2] = false;
                break;
            case RIGHT:
                walls[walls.length - 1] = false;
                break;
            default:
        }
    }
}
