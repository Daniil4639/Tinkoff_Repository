package edu.project2;

import java.util.Objects;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeDFSSearch {

    private MazeDFSSearch() {}

    public static Pair[][] findWay(Cell[][] maze, Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2) {

        Pair[][] previousCell = new Pair[maze.length][maze[0].length];
        boolean[][] wasChecked = new boolean[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                previousCell[i][j] = null;
            }
        }
        previousCell[point1.getRight()][point1.getLeft()] = new ImmutablePair(point1.getLeft(), point1.getRight());

        boolean[] ok = new boolean[1];
        ok[0] = true;

        reqSearch(maze, previousCell, wasChecked, point1, point2, Cell.DIRECTION.TOP, ok);

        return previousCell;
    }

    private static void reqSearch(Cell[][] maze, Pair[][] previousCell, boolean[][] wasChecked,
        Pair<Integer, Integer> currentPos, Pair<Integer, Integer> point2, Cell.DIRECTION direction, boolean[] ok) {
        if (!ok[0]) {
            return;
        }

        wasChecked[currentPos.getRight()][currentPos.getLeft()] = true;

        if (!(Objects.equals(currentPos.getLeft(), point2.getLeft())
            && Objects.equals(currentPos.getRight(), point2.getRight()))) {

            if (!maze[currentPos.getRight()][currentPos.getLeft()].hasWall(Cell.DIRECTION.TOP)
                && !wasChecked[currentPos.getRight() - 1][currentPos.getLeft()]) {
                previousCell[currentPos.getRight() - 1][currentPos.getLeft()] = new ImmutablePair(
                    currentPos.getLeft(), currentPos.getRight());

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft(),
                    currentPos.getRight() - 1), point2, Cell.DIRECTION.BOTTOM, ok);
            }
            if (!maze[currentPos.getRight()][currentPos.getLeft()].hasWall(Cell.DIRECTION.BOTTOM)
                && !wasChecked[currentPos.getRight() + 1][currentPos.getLeft()]) {
                previousCell[currentPos.getRight() + 1][currentPos.getLeft()] = new ImmutablePair(
                    currentPos.getLeft(), currentPos.getRight());

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft(),
                    currentPos.getRight() + 1), point2, Cell.DIRECTION.TOP, ok);
            }
            if (!maze[currentPos.getRight()][currentPos.getLeft()].hasWall(Cell.DIRECTION.LEFT)
                && !wasChecked[currentPos.getRight()][currentPos.getLeft() - 1]) {
                previousCell[currentPos.getRight()][currentPos.getLeft() - 1] = new ImmutablePair(
                    currentPos.getLeft(), currentPos.getRight());

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft() - 1,
                    currentPos.getRight()), point2, Cell.DIRECTION.RIGHT, ok);
            }
            if (!maze[currentPos.getRight()][currentPos.getLeft()].hasWall(Cell.DIRECTION.RIGHT)
                && !wasChecked[currentPos.getRight()][currentPos.getLeft() + 1]) {
                previousCell[currentPos.getRight()][currentPos.getLeft() + 1] = new ImmutablePair(
                    currentPos.getLeft(), currentPos.getRight());

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft() + 1,
                    currentPos.getRight()), point2, Cell.DIRECTION.LEFT, ok);
            }
        } else {
            ok[0] = false;
        }

    }
}
